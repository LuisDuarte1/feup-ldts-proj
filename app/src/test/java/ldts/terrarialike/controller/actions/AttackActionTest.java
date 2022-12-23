package ldts.terrarialike.controller.actions;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.controller.events.ItemEventExecutorEvent;
import ldts.terrarialike.controller.itemInteractions.DirectionItemInteraction;
import ldts.terrarialike.controller.itemInteractions.ItemInteraction;
import ldts.terrarialike.controller.itemInteractions.direction.DefaultAttackItem;
import ldts.terrarialike.controller.itemInteractions.direction.SwordItemInteraction;
import ldts.terrarialike.model.InteractionType;
import ldts.terrarialike.model.Inventory;
import ldts.terrarialike.model.Item;
import ldts.terrarialike.model.Player;
import ldts.terrarialike.utils.InputUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.swing.*;
import javax.swing.plaf.InputMapUIResource;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mockStatic;

public class AttackActionTest {

    @Test

    public void testAttackAction() {

        InputUtils inputUtils = Mockito.mock(InputUtils.class);
        AttackAction attackAction = new AttackAction(inputUtils);

        List<com.googlecode.lanterna.input.KeyStroke> arrowKeys = new ArrayList<>();

        Player player = Mockito.mock(Player.class);
        Inventory inventory = Mockito.mock(Inventory.class);
        Mockito.when(player.getInventory()).thenReturn(inventory);

        arrowKeys.add(new com.googlecode.lanterna.input.KeyStroke(KeyType.ArrowUp));
        arrowKeys.add(new com.googlecode.lanterna.input.KeyStroke(KeyType.ArrowDown));

        Assertions.assertEquals(new ArrayList<>(),attackAction.processAction(arrowKeys, player));

        arrowKeys.clear();



        Mockito.when(inputUtils.getDirection(arrowKeys)).thenReturn(null);

        arrowKeys.add(new com.googlecode.lanterna.input.KeyStroke(KeyType.ArrowUp));
        Assertions.assertEquals(new ArrayList<>(),attackAction.processAction(arrowKeys, player));

        arrowKeys.clear();

        arrowKeys.add(new com.googlecode.lanterna.input.KeyStroke(KeyType.ArrowDown));
        Assertions.assertEquals(new ArrayList<>(),attackAction.processAction(arrowKeys, player));

        arrowKeys.clear();


        Mockito.when(inputUtils.getDirection(arrowKeys)).thenReturn(true);

        arrowKeys.add(new com.googlecode.lanterna.input.KeyStroke(KeyType.ArrowRight));

        Mockito.when(inventory.getSelectedItem()).thenReturn(null);
        List<GameEvent> coringa = attackAction.processAction(arrowKeys, player);
        Assertions.assertEquals(1, coringa.size());
        Assertions.assertTrue(coringa.get(0) instanceof ItemEventExecutorEvent);
        Assertions.assertEquals(InteractionType.ATTACK, ((ItemEventExecutorEvent) coringa.get(0)).getInteractionType());
        Assertions.assertTrue(((ItemEventExecutorEvent) coringa.get(0)).getItem().getInteraction() instanceof DefaultAttackItem);

        arrowKeys.clear();

        Mockito.when(inputUtils.getDirection(arrowKeys)).thenReturn(false);

        arrowKeys.add(new com.googlecode.lanterna.input.KeyStroke(KeyType.ArrowLeft));

        coringa = attackAction.processAction(arrowKeys, player);
        Assertions.assertEquals(1, coringa.size());
        Assertions.assertTrue(coringa.get(0) instanceof ItemEventExecutorEvent);
        Assertions.assertEquals(InteractionType.ATTACK, ((ItemEventExecutorEvent) coringa.get(0)).getInteractionType());
        Assertions.assertTrue(((ItemEventExecutorEvent) coringa.get(0)).getItem().getInteraction() instanceof DefaultAttackItem);

        arrowKeys.clear();


        Item item = Mockito.mock(Item.class);
        SwordItemInteraction swordItemInteraction = Mockito.mock(SwordItemInteraction.class);
        Mockito.when(item.getInteraction()).thenReturn(swordItemInteraction);
        Mockito.when(inventory.getSelectedItem()).thenReturn(item);

        Mockito.when(inputUtils.getDirection(arrowKeys)).thenReturn(true);

        arrowKeys.add(new com.googlecode.lanterna.input.KeyStroke(KeyType.ArrowRight));

        coringa = attackAction.processAction(arrowKeys, player);
        Assertions.assertEquals(1, coringa.size());
        Assertions.assertTrue(coringa.get(0) instanceof ItemEventExecutorEvent);
        Assertions.assertEquals(InteractionType.ATTACK, ((ItemEventExecutorEvent) coringa.get(0)).getInteractionType());
        Assertions.assertTrue(((ItemEventExecutorEvent) coringa.get(0)).getItem().getInteraction() instanceof SwordItemInteraction);

        arrowKeys.clear();


        Mockito.when(inputUtils.getDirection(arrowKeys)).thenReturn(false);

        arrowKeys.add(new com.googlecode.lanterna.input.KeyStroke(KeyType.ArrowLeft));

        coringa = attackAction.processAction(arrowKeys, player);
        Assertions.assertEquals(1, coringa.size());
        Assertions.assertTrue(coringa.get(0) instanceof ItemEventExecutorEvent);
        Assertions.assertEquals(InteractionType.ATTACK, ((ItemEventExecutorEvent) coringa.get(0)).getInteractionType());
        Assertions.assertTrue(((ItemEventExecutorEvent) coringa.get(0)).getItem().getInteraction() instanceof SwordItemInteraction);










    }



}
