package ldts.terrarialike.controller.actions;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.controller.events.ItemEventExecutorEvent;
import ldts.terrarialike.controller.itemInteractions.direction.DefaultAttackItem;
import ldts.terrarialike.controller.itemInteractions.direction.SwordItemInteraction;
import ldts.terrarialike.controller.itemInteractions.position.DefaultDestroyItemInteraction;
import ldts.terrarialike.controller.itemInteractions.position.PickaxeItemInteraction;
import ldts.terrarialike.exceptions.InvalidIndexException;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.*;
import ldts.terrarialike.utils.InputUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class DestroyActionTest { //FIXME: 1.0.0: testDestroyAction fazer para todos os possiveis inputs

    @Test

    public void testProcessAction() throws InvalidPositionException, InvalidIndexException {
        Player player = Mockito.mock(Player.class);
        Inventory inventory = Mockito.mock(Inventory.class);

        Mockito.when(player.getInventory()).thenReturn(inventory);

        InputUtils inputUtils = Mockito.mock(InputUtils.class);

        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(1);
        Mockito.when(position.getY()).thenReturn(1);

        DestroyAction destroyAction = new DestroyAction(inputUtils);

        List<KeyStroke> arrowKeys = new ArrayList<>();

        Mockito.when(inputUtils.getDesiredPosition(arrowKeys,player)).thenReturn(null);
// casos em que nao tem nada na desired position
        arrowKeys.add(new KeyStroke(KeyType.ArrowUp));

        List<GameEvent> gameEvents = destroyAction.processAction(arrowKeys, player);

        Assertions.assertEquals(new ArrayList<>(),gameEvents);

        arrowKeys.clear();
// casos em que tem algo na desired position mas nao e um item


        Mockito.when(inputUtils.getDesiredPosition(arrowKeys,player)).thenReturn(position);

        arrowKeys.add(new KeyStroke(KeyType.ArrowDown));

        gameEvents = destroyAction.processAction(arrowKeys, player);

        Assertions.assertEquals(1, gameEvents.size());
        Assertions.assertTrue(gameEvents.get(0) instanceof ItemEventExecutorEvent);
        Assertions.assertEquals(InteractionType.DESTROY, ((ItemEventExecutorEvent) gameEvents.get(0)).getInteractionType());
        Assertions.assertTrue(((ItemEventExecutorEvent) gameEvents.get(0)).getItem().getInteraction() instanceof DefaultDestroyItemInteraction);


        arrowKeys.clear();



// casos em que tem algo na desired position e e um item

        Item item = Mockito.mock(Item.class);
        Mockito.when(inventory.getItem(Mockito.anyInt())).thenReturn(item);
        PickaxeItemInteraction pickaxeItemInteraction = Mockito.mock(PickaxeItemInteraction.class);
        Mockito.when(item.getInteraction()).thenReturn(pickaxeItemInteraction);
        Mockito.when(inventory.getSelectedItem()).thenReturn(item);

        arrowKeys.add(new KeyStroke(KeyType.ArrowRight));

        gameEvents = destroyAction.processAction(arrowKeys, player);

        // Assertions.assertEquals(new ArrayList<>(),gameEvents);

        Assertions.assertEquals(1, gameEvents.size());
        Assertions.assertTrue(gameEvents.get(0) instanceof ItemEventExecutorEvent);
        Assertions.assertEquals(InteractionType.DESTROY, ((ItemEventExecutorEvent) gameEvents.get(0)).getInteractionType());
        Assertions.assertTrue(((ItemEventExecutorEvent) gameEvents.get(0)).getItem().getInteraction() instanceof PickaxeItemInteraction);


        arrowKeys.clear();







    }

}
