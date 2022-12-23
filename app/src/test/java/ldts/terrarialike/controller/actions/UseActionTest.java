package ldts.terrarialike.controller.actions;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.controller.events.ItemEventExecutorEvent;
import ldts.terrarialike.controller.itemInteractions.position.DefaultDestroyItemInteraction;
import ldts.terrarialike.controller.itemInteractions.position.PickaxeItemInteraction;
import ldts.terrarialike.controller.itemInteractions.position.UseBlockItemInteraction;
import ldts.terrarialike.exceptions.InvalidIndexException;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.*;
import ldts.terrarialike.utils.InputUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class UseActionTest {

    @Test

    public void testProcessAction() throws InvalidPositionException, InvalidIndexException {

        Player player = Mockito.mock(Player.class);
        Inventory inventory = Mockito.mock(Inventory.class);

        Mockito.when(player.getInventory()).thenReturn(inventory);

        InputUtils inputUtils = Mockito.mock(InputUtils.class);

        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(1);
        Mockito.when(position.getY()).thenReturn(1);

        UseAction useAction = new UseAction(inputUtils);

        List<KeyStroke> arrowKeys = new ArrayList<>();

        // nao temos a desire position
        Mockito.when(inputUtils.getDesiredPosition(arrowKeys,player)).thenReturn(null);

        arrowKeys.add(new KeyStroke(KeyType.ArrowUp));

        List<GameEvent> gameEvents = useAction.processAction(arrowKeys, player);

        Assertions.assertEquals(new ArrayList<>(),gameEvents);

        arrowKeys.clear();

        // temos a desire position mas n tem item

        Mockito.when(inputUtils.getDesiredPosition(arrowKeys,player)).thenReturn(position);

        arrowKeys.add(new KeyStroke(KeyType.ArrowDown));

        gameEvents = useAction.processAction(arrowKeys, player);

        Assertions.assertEquals(0, gameEvents.size());

        arrowKeys.clear();

        //temos a desire position e o item

        Item item = Mockito.mock(Item.class);
        Mockito.when(inventory.getItem(Mockito.anyInt())).thenReturn(item);
        UseBlockItemInteraction useBlockItemInteraction = Mockito.mock(UseBlockItemInteraction.class);
        Mockito.when(item.getInteraction()).thenReturn(useBlockItemInteraction);
        Mockito.when(inventory.getSelectedItem()).thenReturn(item);

        arrowKeys.add(new KeyStroke(KeyType.ArrowDown));

        gameEvents = useAction.processAction(arrowKeys, player);

        Assertions.assertEquals(1, gameEvents.size());
        Assertions.assertTrue(gameEvents.get(0) instanceof ItemEventExecutorEvent);
        Assertions.assertEquals(InteractionType.USE, ((ItemEventExecutorEvent) gameEvents.get(0)).getInteractionType());
        Assertions.assertTrue(((ItemEventExecutorEvent) gameEvents.get(0)).getItem().getInteraction() instanceof UseBlockItemInteraction);

        arrowKeys.clear();





    }


}
