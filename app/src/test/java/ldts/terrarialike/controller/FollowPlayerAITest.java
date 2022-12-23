package ldts.terrarialike.controller;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.Enemy;
import ldts.terrarialike.model.Player;
import ldts.terrarialike.model.Position;
import ldts.terrarialike.model.World;
import ldts.terrarialike.utils.WorldUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class FollowPlayerAITest {

    @Test
    public void shouldFollowPlayerRightForward() throws InvalidPositionException {
        Position blockAbove = Mockito.mock(Position.class);
        Mockito.when(blockAbove.getX()).thenReturn(2);
        Mockito.when(blockAbove.getY()).thenReturn(3);

        Position blockRight = Mockito.mock(Position.class);
        Mockito.when(blockRight.getX()).thenReturn(3);
        Mockito.when(blockRight.getY()).thenReturn(2);

        Position playerPos = Mockito.mock(Position.class);
        Mockito.when(playerPos.getX()).thenReturn(4);
        Mockito.when(playerPos.getY()).thenReturn(2);

        Position enemyPos = Mockito.mock(Position.class);
        Mockito.when(enemyPos.getX()).thenReturn(2);
        Mockito.when(enemyPos.getY()).thenReturn(2);


        World world = Mockito.mock(World.class);
        Player player = Mockito.mock(Player.class);
        Mockito.when(player.getPosition()).thenReturn(playerPos);

        Enemy enemy = Mockito.mock(Enemy.class);
        Mockito.when(enemy.getPosition()).thenReturn(enemyPos);

        Mockito.when(world.getPlayer()).thenReturn(player);


        WorldUtils worldUtils = Mockito.mock(WorldUtils.class);
        Mockito.when(worldUtils.getBlock(blockRight,world)).thenReturn(null);
        Mockito.when(worldUtils.getEntity(blockRight,world)).thenReturn(null);

        FollowPlayerAI followPlayerAI = new FollowPlayerAI(player,1, worldUtils);

        followPlayerAI.tick(enemy,world);
        Mockito.verify(enemy, Mockito.never()).setPosition(Mockito.any());

        followPlayerAI.tick(enemy,world);
        Mockito.verify(enemy, Mockito.times(1)).setPosition(new Position(3,2));



    }

}
