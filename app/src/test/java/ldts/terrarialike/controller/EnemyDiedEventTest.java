package ldts.terrarialike.controller;

import jdk.jfr.Event;
import ldts.terrarialike.controller.events.EnemyDiedEvent;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.exceptions.InvalidSizeException;
import ldts.terrarialike.model.Enemy;
import ldts.terrarialike.model.World;
import ldts.terrarialike.utils.WorldUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class EnemyDiedEventTest {

    @Test

    public void testExecute() throws InvalidSizeException, InvalidPositionException {

        Enemy enemy = Mockito.mock(Enemy.class);

        EnemyDiedEvent enemyDiedEvent = new EnemyDiedEvent(enemy);

        World world = new World();
        WorldUtils worldUtils = Mockito.mock(WorldUtils.class);

        List<Enemy> enemies = new ArrayList<Enemy>();

        enemies.add(enemy);

        world.setEnemiesList(enemies);

        List<GameEvent> list = enemyDiedEvent.execute(world,worldUtils);

        Assertions.assertEquals(0, world.getEnemiesList().size());

    }

}
