package ldts.terrarialike.controller.actions;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.model.Inventory;
import ldts.terrarialike.model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class DestroyActionTest {

    @Test

    public void testProcessAction() {
        DestroyAction destroyAction = new DestroyAction();



        List<com.googlecode.lanterna.input.KeyStroke> arrowKeys = new ArrayList<>();
        Player player = Mockito.mock(Player.class);
        Inventory inventory = Mockito.mock(Inventory.class);
        Mockito.when(player.getInventory()).thenReturn(inventory);
// casos em que nao tem nada na desired position
        arrowKeys.add(new com.googlecode.lanterna.input.KeyStroke(KeyType.ArrowUp));

        List<GameEvent> gameEvents = destroyAction.processAction(arrowKeys, player);

        Assertions.assertEquals(new ArrayList<>(),gameEvents);

        arrowKeys.clear();

        arrowKeys.add(new com.googlecode.lanterna.input.KeyStroke(KeyType.ArrowDown));

        gameEvents = destroyAction.processAction(arrowKeys, player);

        Assertions.assertEquals(new ArrayList<>(),gameEvents);

        arrowKeys.clear();


        arrowKeys.add(new com.googlecode.lanterna.input.KeyStroke(KeyType.ArrowRight));

        gameEvents = destroyAction.processAction(arrowKeys, player);

        Assertions.assertEquals(new ArrayList<>(),gameEvents);

        arrowKeys.clear();

        arrowKeys.add(new com.googlecode.lanterna.input.KeyStroke(KeyType.ArrowLeft));

        gameEvents = destroyAction.processAction(arrowKeys, player);

        Assertions.assertEquals(new ArrayList<>(),gameEvents);

        arrowKeys.clear();

        arrowKeys.add(new com.googlecode.lanterna.input.KeyStroke(KeyType.ArrowLeft));
        arrowKeys.add(new com.googlecode.lanterna.input.KeyStroke(KeyType.ArrowUp));


        gameEvents = destroyAction.processAction(arrowKeys, player);

        Assertions.assertEquals(new ArrayList<>(),gameEvents);

        arrowKeys.clear();
        // .............................................................

        // casos em que tem algo na desired position
        arrowKeys.add(new com.googlecode.lanterna.input.KeyStroke(KeyType.ArrowUp));








    }

}
