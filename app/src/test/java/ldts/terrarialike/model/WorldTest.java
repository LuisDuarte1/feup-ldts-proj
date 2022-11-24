package ldts.terrarialike.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class WorldTest {


    @Test
    public void seedGetterTest(){
        World w = new World(100);

        assertEquals(100, w.getSeed());
    }

    @Test
    public void chunkAddOnceTest(){
        World w = new World();

        Chunk c = new Chunk();

        assertEquals(true,w.tryAddChunk(c));
    }


    @Test
    public void chunkAddDuplicateTest(){
        World w = new World();

        Chunk c = new Chunk();

        assertEquals(true,w.tryAddChunk(c));
        assertEquals(false,w.tryAddChunk(c));

    }

    @Test
    public void addEnemyTest(){
        World w = new World();

        Enemy e1 = new Enemy();

        w.addEnemy(e1);

        assertEquals(true, w.getEnemiesList().contains(e1));
    }

    @Test
    public void removeEnemyInList(){
        World w = new World();

        Enemy e1 = new Enemy();

        w.addEnemy(e1);

        w.removeEnemy(e1);

        assertEquals(0, w.getEnemiesList().size());
    }

    @Test
    public void removeEnemyNotInListTest(){
        World w = new World();


        Enemy e2 = new Enemy();


        w.removeEnemy(e2);

        assertEquals(0, w.getEnemiesList().size());
    }
    
}
