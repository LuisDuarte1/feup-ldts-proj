package ldts.terrarialike.controller.ItemInteraction.direction;

import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.controller.itemInteractions.DirectionItemInteraction;
import ldts.terrarialike.controller.itemInteractions.direction.DefaultAttackItem;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.exceptions.InvalidSizeException;
import ldts.terrarialike.model.*;
import ldts.terrarialike.utils.WorldUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class DefaultAttackItemTest {




    @Test

    public void findNearEnemiesTest() throws InvalidSizeException, InvalidPositionException {

        World world = Mockito.mock(World.class);
        WorldUtils worldUtils = Mockito.mock(WorldUtils.class);

        List<Enemy> enemies = new ArrayList<>();

        Enemy enemy1 = Mockito.mock(Enemy.class);
        Enemy enemy2 = Mockito.mock(Enemy.class);
        enemies.add(enemy1);
        enemies.add(enemy2);

        Position position = Mockito.mock(Position.class);
        Position position1 = Mockito.mock(Position.class);
        Position position2 = Mockito.mock(Position.class);

        Mockito.when(position1.getX()).thenReturn(2);
        Mockito.when(position1.getY()).thenReturn(2);
        Mockito.when(position2.getX()).thenReturn(3);
        Mockito.when(position2.getY()).thenReturn(3);


        Player player = Mockito.mock(Player.class);
        PlayerLogs playerLogs = Mockito.mock(PlayerLogs.class);
        Mockito.when(player.getPlayerLogs()).thenReturn(playerLogs);

        Mockito.when(position.getX()).thenReturn(0);
        Mockito.when(position.getY()).thenReturn(0);
        Mockito.when(world.getPlayer()).thenReturn(player);
        Mockito.when(world.getPlayer().getPosition()).thenReturn(position);

        Item item = Mockito.mock(Item.class);
        Inventory inventory = Mockito.mock(Inventory.class);
        Mockito.when(player.getInventory()).thenReturn(inventory);
        Mockito.when(player.getInventory().getSelectedItem()).thenReturn(item);

        Mockito.when(enemy1.getPosition()).thenReturn(position1);
        Mockito.when(enemy2.getPosition()).thenReturn(position2);
        Mockito.when(world.getEnemiesList()).thenReturn(enemies);


        DefaultAttackItem defaultAttackItem = new DefaultAttackItem();

        List<GameEvent> gameEvents = defaultAttackItem.execute(world, InteractionType.ATTACK, item, worldUtils);

        Assertions.assertEquals(new ArrayList<>(), gameEvents);


        defaultAttackItem.setDirection(true);

        gameEvents = defaultAttackItem.execute(world, InteractionType.ATTACK, item, worldUtils);

        Mockito.verify(enemy1, Mockito.times(1)).setHp(Mockito.anyInt());
        Mockito.verify(enemy2, Mockito.times(0)).setHp(Mockito.anyInt());


        enemies = new ArrayList<>();

        Enemy enemy3 = Mockito.mock(Enemy.class);
        Enemy enemy4 = Mockito.mock(Enemy.class);
        enemies.add(enemy3);
        enemies.add(enemy4);


        Mockito.when(enemy3.getPosition()).thenReturn(position1);
        Mockito.when(enemy4.getPosition()).thenReturn(position2);

        defaultAttackItem.setDirection(false);

        gameEvents = defaultAttackItem.execute(world, InteractionType.ATTACK, item, worldUtils);

        Mockito.verify(enemy3, Mockito.times(0)).setHp(Mockito.anyInt());
        Mockito.verify(enemy4, Mockito.times(0)).setHp(Mockito.anyInt());

    }
}
