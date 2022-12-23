package ldts.terrarialike.controller.Generators;

import ldts.terrarialike.controller.generators.EnemyGeneratorFactory;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.exceptions.InvalidSizeException;
import ldts.terrarialike.model.*;
import ldts.terrarialike.utils.WorldUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class EnemyGeneratorFactoryTest {

    World world;
    WorldUtils worldUtils;

    @BeforeEach

    public void setUp() {
         world = Mockito.mock(World.class);
         Block testBlock = Mockito.mock(Block.class);
        try {
            Mockito.when(testBlock.getPosition()).thenReturn(new Position(2,3));
        } catch (InvalidPositionException e) {
            throw new RuntimeException(e);
        }

        Chunk c0 = Mockito.mock(Chunk.class);
         Mockito.when(c0.getChunkID()).thenReturn(0);
         Mockito.when(c0.getBlocks()).thenReturn(List.of(testBlock));
         Chunk c1 = Mockito.mock(Chunk.class);
         Mockito.when(c1.getChunkID()).thenReturn(1);
         Mockito.when(c1.getBlocks()).thenReturn(List.of(testBlock));

         Chunk c2 = Mockito.mock(Chunk.class);
         Mockito.when(c2.getChunkID()).thenReturn(2);
         Mockito.when(c2.getBlocks()).thenReturn(List.of(testBlock));

         Chunk c3 = Mockito.mock(Chunk.class);
         Mockito.when(c3.getChunkID()).thenReturn(3);
         Mockito.when(c3.getBlocks()).thenReturn(List.of(testBlock));

         Chunk c4 = Mockito.mock(Chunk.class);
         Mockito.when(c4.getChunkID()).thenReturn(4);
         Mockito.when(c4.getBlocks()).thenReturn(List.of(testBlock));


         Mockito.when(world.getChunks()).thenReturn(List.of(c0,c1,c2,c3,c4));

         Mockito.when(world.getSeed()).thenReturn(0);
         worldUtils = Mockito.mock(WorldUtils.class);
        Mockito.when(worldUtils.getBlock(Mockito.any(), Mockito.eq(world))).thenReturn(testBlock);
        Mockito.when(worldUtils.findMaxHeightOfXPos(Mockito.any(), Mockito.eq(world))).thenReturn(20);

    }



    @Test

    public void generateEnemyTest() throws InvalidSizeException, InvalidPositionException {

            EnemyGeneratorFactory enemyGeneratorFactory = new EnemyGeneratorFactory(world,worldUtils);

            Block block = Mockito.mock(Block.class);
            Mockito.when(block.getPosition()).thenReturn(new Position(2,3));

            List<Enemy> enemies = new ArrayList<>();

            for(int i = 0; i < 5; i++){
                enemies.add(enemyGeneratorFactory.generateEnemy(i));
            }

            Assertions.assertTrue(enemies.get(0) instanceof Zombie);
            Assertions.assertTrue(enemies.get(1) instanceof Skeleton);
            Assertions.assertTrue(enemies.get(2) instanceof Zombie);
            Assertions.assertTrue(enemies.get(3) instanceof Zombie);
            Assertions.assertTrue(enemies.get(4) instanceof Zombie);

            Assertions.assertEquals(new Position(2,21),enemies.get(0).getPosition());

    }



    @Test

    public void generateInicialEnemiesTest() throws InvalidSizeException, InvalidPositionException {

        EnemyGeneratorFactory enemyGeneratorFactory = new EnemyGeneratorFactory(world,worldUtils);

        enemyGeneratorFactory.generateInitialEnemies();

        ArgumentCaptor<Enemy> enemies = ArgumentCaptor.forClass(Enemy.class);

        Mockito.verify(world, Mockito.times(2)).addEnemy(enemies.capture());

        List<Enemy> enemyList = enemies.getAllValues();
        Assertions.assertTrue(enemyList.get(0) instanceof Zombie);
        Assertions.assertTrue(enemyList.get(1) instanceof Zombie);
        Assertions.assertEquals(new Position(2,21),enemyList.get(0).getPosition());


    }












}

