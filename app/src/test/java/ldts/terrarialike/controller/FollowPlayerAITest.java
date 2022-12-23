package ldts.terrarialike.controller;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.*;
import ldts.terrarialike.utils.WorldUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class FollowPlayerAITest {

    @Test
    public void shouldFollowPlayerRightForward() throws InvalidPositionException {


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
        Mockito.when(worldUtils.getBlock(new Position(3,2),world)).thenReturn(null);
        Mockito.when(worldUtils.getEntity(new Position(3,2),world)).thenReturn(null);

        FollowPlayerAI followPlayerAI = new FollowPlayerAI(player,10, worldUtils);

        for(int i = 0; i < FollowPlayerAI.WAIT_FRAMES-1; i++){

            followPlayerAI.tick(enemy,world);
            Mockito.verify(enemy, Mockito.never()).setPosition(Mockito.any());
        }

        followPlayerAI.tick(enemy,world);
        Mockito.verify(enemy, Mockito.times(1)).setPosition(new Position(3,2));
    }

    @Test
    public void shouldFollowPlayerRightUp() throws InvalidPositionException {

        Position blockRightPos = Mockito.mock(Position.class);
        Mockito.when(blockRightPos.getX()).thenReturn(3);
        Mockito.when(blockRightPos.getY()).thenReturn(2);

        Position playerPos = Mockito.mock(Position.class);
        Mockito.when(playerPos.getX()).thenReturn(4);
        Mockito.when(playerPos.getY()).thenReturn(3);

        Position enemyPos = Mockito.mock(Position.class);
        Mockito.when(enemyPos.getX()).thenReturn(2);
        Mockito.when(enemyPos.getY()).thenReturn(2);


        World world = Mockito.mock(World.class);
        Player player = Mockito.mock(Player.class);
        Mockito.when(player.getPosition()).thenReturn(playerPos);

        Enemy enemy = Mockito.mock(Enemy.class);
        Mockito.when(enemy.getPosition()).thenReturn(enemyPos);

        Mockito.when(world.getPlayer()).thenReturn(player);

        Block blockRight = Mockito.mock(Block.class);
        Mockito.when(blockRight.getPosition()).thenReturn(blockRightPos);


        WorldUtils worldUtils = Mockito.mock(WorldUtils.class);
        Mockito.when(worldUtils.getBlock(new Position(2,3),world)).thenReturn(null);

        Mockito.when(worldUtils.getBlock(new Position(3,2),world)).thenReturn(blockRight);
        Mockito.when(worldUtils.getEntity(new Position(3,2),world)).thenReturn(null);

        FollowPlayerAI followPlayerAI = new FollowPlayerAI(player,10, worldUtils);

        for(int i = 0; i < FollowPlayerAI.WAIT_FRAMES-1; i++){

            followPlayerAI.tick(enemy,world);
            Mockito.verify(enemy, Mockito.never()).setPosition(Mockito.any());
        }

        followPlayerAI.tick(enemy,world);
        Mockito.verify(enemy, Mockito.times(1)).setPosition(new Position(3,3));
    }

    @Test
    public void cantFollowPlayerRightUp() throws InvalidPositionException {

        Position blockRightPos = Mockito.mock(Position.class);
        Mockito.when(blockRightPos.getX()).thenReturn(3);
        Mockito.when(blockRightPos.getY()).thenReturn(2);

        Position playerPos = Mockito.mock(Position.class);
        Mockito.when(playerPos.getX()).thenReturn(4);
        Mockito.when(playerPos.getY()).thenReturn(3);

        Position enemyPos = Mockito.mock(Position.class);
        Mockito.when(enemyPos.getX()).thenReturn(2);
        Mockito.when(enemyPos.getY()).thenReturn(2);


        World world = Mockito.mock(World.class);
        Player player = Mockito.mock(Player.class);
        Mockito.when(player.getPosition()).thenReturn(playerPos);

        Enemy enemy = Mockito.mock(Enemy.class);
        Mockito.when(enemy.getPosition()).thenReturn(enemyPos);

        Mockito.when(world.getPlayer()).thenReturn(player);

        Block blockRight = Mockito.mock(Block.class);
        Mockito.when(blockRight.getPosition()).thenReturn(blockRightPos);


        WorldUtils worldUtils = Mockito.mock(WorldUtils.class);
        Mockito.when(worldUtils.getBlock(new Position(2,3),world)).thenReturn(blockRight);

        Mockito.when(worldUtils.getBlock(new Position(3,2),world)).thenReturn(blockRight);
        Mockito.when(worldUtils.getEntity(new Position(3,2),world)).thenReturn(null);

        FollowPlayerAI followPlayerAI = new FollowPlayerAI(player,10, worldUtils);

        for(int i = 0; i < FollowPlayerAI.WAIT_FRAMES; i++){

            followPlayerAI.tick(enemy,world);
            Mockito.verify(enemy, Mockito.never()).setPosition(Mockito.any());
        }

    }

    @Test
    public void shouldFollowPlayerLeftForward() throws InvalidPositionException {


        Position playerPos = Mockito.mock(Position.class);
        Mockito.when(playerPos.getX()).thenReturn(0);
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
        Mockito.when(worldUtils.getBlock(new Position(1,2),world)).thenReturn(null);
        Mockito.when(worldUtils.getEntity(new Position(1,2),world)).thenReturn(null);

        FollowPlayerAI followPlayerAI = new FollowPlayerAI(player,10, worldUtils);

        for(int i = 0; i < FollowPlayerAI.WAIT_FRAMES-1; i++){

            followPlayerAI.tick(enemy,world);
            Mockito.verify(enemy, Mockito.never()).setPosition(Mockito.any());
        }

        followPlayerAI.tick(enemy,world);
        Mockito.verify(enemy, Mockito.times(1)).setPosition(new Position(1,2));
    }

    @Test
    public void shouldFollowPlayerLeftUp() throws InvalidPositionException {

        Position blockRightPos = Mockito.mock(Position.class);
        Mockito.when(blockRightPos.getX()).thenReturn(3);
        Mockito.when(blockRightPos.getY()).thenReturn(2);

        Position playerPos = Mockito.mock(Position.class);
        Mockito.when(playerPos.getX()).thenReturn(0);
        Mockito.when(playerPos.getY()).thenReturn(3);

        Position enemyPos = Mockito.mock(Position.class);
        Mockito.when(enemyPos.getX()).thenReturn(2);
        Mockito.when(enemyPos.getY()).thenReturn(2);


        World world = Mockito.mock(World.class);
        Player player = Mockito.mock(Player.class);
        Mockito.when(player.getPosition()).thenReturn(playerPos);

        Enemy enemy = Mockito.mock(Enemy.class);
        Mockito.when(enemy.getPosition()).thenReturn(enemyPos);

        Mockito.when(world.getPlayer()).thenReturn(player);

        Block blockRight = Mockito.mock(Block.class);
        Mockito.when(blockRight.getPosition()).thenReturn(blockRightPos);


        WorldUtils worldUtils = Mockito.mock(WorldUtils.class);
        Mockito.when(worldUtils.getBlock(new Position(2,3),world)).thenReturn(null);

        Mockito.when(worldUtils.getBlock(new Position(1,2),world)).thenReturn(blockRight);
        Mockito.when(worldUtils.getEntity(new Position(1,2),world)).thenReturn(null);

        FollowPlayerAI followPlayerAI = new FollowPlayerAI(player,10, worldUtils);

        for(int i = 0; i < FollowPlayerAI.WAIT_FRAMES-1; i++){

            followPlayerAI.tick(enemy,world);
            Mockito.verify(enemy, Mockito.never()).setPosition(Mockito.any());
        }

        followPlayerAI.tick(enemy,world);
        Mockito.verify(enemy, Mockito.times(1)).setPosition(new Position(1,3));
    }

    @Test
    public void cantFollowPlayerLeftUp() throws InvalidPositionException {

        Position blockRightPos = Mockito.mock(Position.class);
        Mockito.when(blockRightPos.getX()).thenReturn(3);
        Mockito.when(blockRightPos.getY()).thenReturn(2);

        Position playerPos = Mockito.mock(Position.class);
        Mockito.when(playerPos.getX()).thenReturn(0);
        Mockito.when(playerPos.getY()).thenReturn(3);

        Position enemyPos = Mockito.mock(Position.class);
        Mockito.when(enemyPos.getX()).thenReturn(2);
        Mockito.when(enemyPos.getY()).thenReturn(2);


        World world = Mockito.mock(World.class);
        Player player = Mockito.mock(Player.class);
        Mockito.when(player.getPosition()).thenReturn(playerPos);

        Enemy enemy = Mockito.mock(Enemy.class);
        Mockito.when(enemy.getPosition()).thenReturn(enemyPos);

        Mockito.when(world.getPlayer()).thenReturn(player);

        Block blockRight = Mockito.mock(Block.class);
        Mockito.when(blockRight.getPosition()).thenReturn(blockRightPos);


        WorldUtils worldUtils = Mockito.mock(WorldUtils.class);
        Mockito.when(worldUtils.getBlock(new Position(2,3),world)).thenReturn(blockRight);

        Mockito.when(worldUtils.getBlock(new Position(1,2),world)).thenReturn(blockRight);
        Mockito.when(worldUtils.getEntity(new Position(1,2),world)).thenReturn(null);

        FollowPlayerAI followPlayerAI = new FollowPlayerAI(player,10, worldUtils);

        for(int i = 0; i < FollowPlayerAI.WAIT_FRAMES; i++){

            followPlayerAI.tick(enemy,world);
            Mockito.verify(enemy, Mockito.never()).setPosition(Mockito.any());
        }

    }

    @Test
    public void shouldAttackPlayer() throws InterruptedException {
        Position playerPos = Mockito.mock(Position.class);
        Mockito.when(playerPos.getX()).thenReturn(1);
        Mockito.when(playerPos.getY()).thenReturn(2);

        Position enemyPos = Mockito.mock(Position.class);
        Mockito.when(enemyPos.getX()).thenReturn(2);
        Mockito.when(enemyPos.getY()).thenReturn(2);


        World world = Mockito.mock(World.class);
        Player player = Mockito.mock(Player.class);
        PlayerLogs playerLogs = Mockito.mock(PlayerLogs.class);

        Mockito.when(player.getPlayerLogs()).thenReturn(playerLogs);

        Mockito.when(player.getPosition()).thenReturn(playerPos);

        Enemy enemy = Mockito.mock(Enemy.class);
        Mockito.when(enemy.getPosition()).thenReturn(enemyPos);

        Mockito.when(world.getPlayer()).thenReturn(player);

        WorldUtils worldUtils = Mockito.mock(WorldUtils.class);

        FollowPlayerAI followPlayerAI = new FollowPlayerAI(player,10, worldUtils);
        for(int i = 0; i < FollowPlayerAI.WAIT_FRAMES-1; i++){

            followPlayerAI.tick(enemy,world);
            Mockito.verify(enemy, Mockito.never()).setPosition(Mockito.any());
        }
        //shouldn't really do this
        Thread.sleep(5*1000);
        followPlayerAI.tick(enemy,world);
        Mockito.verify(enemy, Mockito.never()).setPosition(Mockito.any());
        Mockito.verify(player,Mockito.times(1)).setHp(Mockito.anyInt());
        Mockito.verify(playerLogs,Mockito.times(1)).addLogString(Mockito.anyString());

    }

    @Test
    public void enemyOutOfRangeCantMove(){
        Position playerPos = Mockito.mock(Position.class);
        Mockito.when(playerPos.getX()).thenReturn(10);
        Mockito.when(playerPos.getY()).thenReturn(2);

        Position enemyPos = Mockito.mock(Position.class);
        Mockito.when(enemyPos.getX()).thenReturn(0);
        Mockito.when(enemyPos.getY()).thenReturn(2);


        World world = Mockito.mock(World.class);
        Player player = Mockito.mock(Player.class);
        PlayerLogs playerLogs = Mockito.mock(PlayerLogs.class);

        Mockito.when(player.getPlayerLogs()).thenReturn(playerLogs);

        Mockito.when(player.getPosition()).thenReturn(playerPos);

        Enemy enemy = Mockito.mock(Enemy.class);
        Mockito.when(enemy.getPosition()).thenReturn(enemyPos);

        Mockito.when(world.getPlayer()).thenReturn(player);

        WorldUtils worldUtils = Mockito.mock(WorldUtils.class);

        FollowPlayerAI followPlayerAI = new FollowPlayerAI(player, 5, worldUtils);
        for(int i = 0; i < FollowPlayerAI.WAIT_FRAMES; i++){

            followPlayerAI.tick(enemy,world);
            Mockito.verify(enemy, Mockito.never()).setPosition(Mockito.any());
        }
    }
}
