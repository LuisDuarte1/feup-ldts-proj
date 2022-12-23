package ldts.terrarialike.controller.Generators;

import ldts.terrarialike.controller.generators.EnemyGeneratorFactory;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.exceptions.InvalidSizeException;
import ldts.terrarialike.model.Chunk;
import ldts.terrarialike.model.Skeleton;
import ldts.terrarialike.model.World;
import ldts.terrarialike.utils.WorldUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class EnemyGeneratorFactoryTest {

    World world;
    WorldUtils worldUtils;

    @BeforeEach

    public void setUp() {
        try {
             world = new World();
        } catch (InvalidSizeException | InvalidPositionException e) {
            throw new RuntimeException(e);
        }
    }



    @Test

    public void generateEnemyTest() throws InvalidSizeException, InvalidPositionException {

            worldUtils = Mockito.mock(WorldUtils.class);
            EnemyGeneratorFactory enemyGeneratorFactory = new EnemyGeneratorFactory(world,worldUtils);

            List<Chunk> chunks = world.getChunks();

            Chunk chunk = chunks.get(0);

            Chunk sus = Mockito.mock(chunk.getClass());

            Mockito.when(sus.getChunkID()).thenReturn(-1);

            enemyGeneratorFactory.generateEnemy(chunk.getChunkID());

            Assertions.assertEquals(1, world.getEnemiesList().size());
            //como fazer para testar se o inimigo é do tipo certo?

          //  Assertions.assertTrue(world.getEnemiesList().get(0).getClass().isInstance(Zombie , Skeleton));
        try {
            enemyGeneratorFactory.generateEnemy(sus.getChunkID());

            Assertions.fail("Should have thrown an exception");

        }catch (RuntimeException e) {

        Assertions.assertEquals("Couldn't find chunkID", e.getMessage());
    }

    }



    @Test

    public void generateInicialEnemiesTest() throws InvalidSizeException, InvalidPositionException {

        worldUtils = Mockito.mock(WorldUtils.class);
        EnemyGeneratorFactory enemyGeneratorFactory = new EnemyGeneratorFactory(world,worldUtils);

        enemyGeneratorFactory.generateInitialEnemies();

        Assertions.assertEquals(3, world.getEnemiesList().size());

    }

    @Test

    public void getMobsNearPlayerTest(){



    }











}

