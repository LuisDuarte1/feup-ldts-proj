package ldts.terrarialike.controller;

import jdk.jfr.Event;
import ldts.terrarialike.controller.events.EnemyDiedEvent;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.exceptions.InvalidSizeException;
import ldts.terrarialike.model.Enemy;
import ldts.terrarialike.model.World;
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

        List<Enemy> enemies = new ArrayList<Enemy>();

        enemies.add(enemy);

        world.setEnemiesList(enemies);

        List<GameEvent> list = enemyDiedEvent.execute(world);

        Assertions.assertEquals(0, world.getEnemiesList().size());

    }

}
