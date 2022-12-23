package ldts.terrarialike.controller.ItemInteraction.direction;

import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.exceptions.InvalidSizeException;
import ldts.terrarialike.model.Enemy;
import ldts.terrarialike.model.Player;
import ldts.terrarialike.model.Position;
import ldts.terrarialike.model.World;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class DefaultAttackItemTest {




    @Test

    public void findNearEnemiesTest() throws InvalidSizeException, InvalidPositionException {

        World world = new World();

        List<Enemy> enemies = new ArrayList<>();

        Enemy enemy1 = Mockito.mock(Enemy.class);
        Enemy enemy2 = Mockito.mock(Enemy.class);
        enemies.add(enemy1);
        enemies.add(enemy2);

        Position position = Mockito.mock(Position.class);
        Position position1 = Mockito.mock(Position.class);
        Position position2 = Mockito.mock(Position.class);

        Mockito.when(position1.getX()).thenReturn(1);
        Mockito.when(position1.getY()).thenReturn(1);
        Mockito.when(position2.getX()).thenReturn(2);
        Mockito.when(position2.getY()).thenReturn(2);


        Player player = Mockito.mock(Player.class);

        Mockito.when(position.getX()).thenReturn(1);
        Mockito.when(position.getY()).thenReturn(1);
        Mockito.when(world.getPlayer()).thenReturn(player);
        Mockito.when(world.getPlayer().getPosition()).thenReturn(position);









    }

    @Test

    public void testExecute() {



    }
}
