package ldts.terrarialike.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import ldts.terrarialike.exceptions.InvalidSizeException;
import org.junit.jupiter.api.Test;

import ldts.terrarialike.exceptions.InvalidPositionException;
import org.mockito.Mockito;

public class WorldTest {


    @Test
    public void seedGetterTest() throws InvalidSizeException,InvalidPositionException{
        World w = new World(100);

        assertEquals(100, w.getSeed());
    }

    @Test
    public void chunkAddOnceTest() throws InvalidSizeException,InvalidPositionException{
        World w = new World();

        Chunk c = Mockito.mock(Chunk.class);

        assertEquals(true,w.tryAddChunk(c));
    }


    @Test
    public void chunkAddDuplicateTest() throws InvalidSizeException,InvalidPositionException{
        World w = new World();

        Chunk c = Mockito.mock(Chunk.class);


        assertEquals(true,w.tryAddChunk(c));
        assertEquals(false,w.tryAddChunk(c));

    }

    @Test
    public void addEnemyTest() throws InvalidSizeException,InvalidPositionException{
        World w = new World();

        Enemy e1 = Mockito.mock(Enemy.class);

        w.addEnemy(e1);

        assertEquals(true, w.getEnemiesList().contains(e1));
    }

    @Test
    public void removeEnemyInList() throws InvalidSizeException,InvalidPositionException{
        World w = new World();
        Enemy e1 = Mockito.mock(Enemy.class);


        w.addEnemy(e1);

        w.removeEnemy(e1);

        assertEquals(0, w.getEnemiesList().size());
    }

    @Test
    public void removeEnemyNotInListTest() throws InvalidSizeException,InvalidPositionException{
        World w = new World();


        Enemy e2 = Mockito.mock(Enemy.class);



        w.removeEnemy(e2);

        assertEquals(0, w.getEnemiesList().size());
    }
    
}
