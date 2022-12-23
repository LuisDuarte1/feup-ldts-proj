package ldts.terrarialike.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ldts.terrarialike.exceptions.InvalidSizeException;
import org.junit.jupiter.api.Assertions;
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

    @Test
    public void worldChunksSortTest() throws InvalidSizeException, InvalidPositionException {
        World w = new World();

        Chunk c1 = Mockito.mock(Chunk.class);
        Chunk c2 = Mockito.mock(Chunk.class);
        Chunk c3 = Mockito.mock(Chunk.class);

        Mockito.when(c1.getChunkID()).thenReturn(0);
        Mockito.when(c2.getChunkID()).thenReturn(1);
        Mockito.when(c3.getChunkID()).thenReturn(-1);

        w.tryAddChunk(c1);
        w.tryAddChunk(c2);
        w.tryAddChunk(c3);

        Collections.swap(w.getChunks(),0,2);

        w.sortChunks();

        Assertions.assertEquals(List.of(c3,c2,c1),w.getChunks());




    }
}
