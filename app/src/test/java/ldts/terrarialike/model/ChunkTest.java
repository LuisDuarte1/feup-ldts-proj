package ldts.terrarialike.model;

import ldts.terrarialike.exceptions.BlockNotFoundException;
import ldts.terrarialike.exceptions.InvalidPositionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class ChunkTest {


    Block block = Mockito.mock(Block.class);
    Block block2 = Mockito.mock(Block.class);


    @Test

    public void getBlockstest() {

        Block block = Mockito.mock(Block.class);
        Block block1 = Mockito.mock(Block.class);
        Block block2 = Mockito.mock(Block.class);
        List<Block> list = new ArrayList<>();
        list.add(block);
        list.add(block1);
        list.add(block2);
        Chunk chunk = Mockito.mock(Chunk.class);
        Mockito.when(chunk.getBlocks()).thenReturn(list);

        Assertions.assertEquals(list, chunk.getBlocks());
    }

    public void setBlocktest(){

        Chunk chunk = new Chunk(0);
        Block block = Mockito.mock(Block.class);
        Block block1 = Mockito.mock(Block.class);
        Block block2 = Mockito.mock(Block.class);
        List<Block> list = new ArrayList<>();
        list.add(block);
        list.add(block1);
        list.add(block2);

        chunk.setBlocks(list);

        Assertions.assertEquals(list, chunk.getBlocks());

    }
    @Test
    public void getPositiontest() {

        Chunk chunk = new Chunk(2);
        Assertions.assertEquals(2, chunk.getPosition());


    }

    @Test
    public void addBlocktest() throws InvalidPositionException {

        Chunk chunk = new Chunk(2);
        Block block = Mockito.mock(Block.class);
        Block block1 = Mockito.mock(Block.class);
        Block block2 = Mockito.mock(Block.class);
        Mockito.when(block.getPosition()).thenReturn(new Position(32, 0));
        Mockito.when(block1.getPosition()).thenReturn(new Position(48, 32));
        Mockito.when(block2.getPosition()).thenReturn(new Position(47, 32));
        //testing
        try {
            chunk.addBlock(block);
            chunk.addBlock(block2);


        } catch (InvalidPositionException e) {
            Assertions.fail(e.getStackTrace().toString());
        }


        try {
            chunk.addBlock(block1);
            Assertions.fail("InvalidPositionException not thrown");

        } catch (InvalidPositionException e) {

        }
    }

    @Test

    public void removeBlocktest() {
        Position pos = Mockito.mock(Position.class);
        Position pos1 = Mockito.mock(Position.class);
        Position pos2 = Mockito.mock(Position.class);
        Block block = Mockito.mock(Block.class);
        Mockito.when(block.getPosition()).thenReturn(pos2);
        Block block1 = Mockito.mock(Block.class);
        Mockito.when(block1.getPosition()).thenReturn( pos1);
        Block block2 = Mockito.mock(Block.class);
        Mockito.when(block2.getPosition()).thenReturn(pos);
        Chunk chunk =new Chunk(5);

        List<Block> list = new ArrayList<>();
        list.add(block);
        list.add(block1);
        list.add(block2);

        chunk.setBlocks(list);

        try {
            chunk.removeBlock(pos);
            Assertions.assertEquals(2, chunk.getBlocks().size());
            Assertions.assertEquals(false, chunk.getBlocks().contains(block2));
            Assertions.assertEquals(block, chunk.getBlocks().get(0));
            Assertions.assertEquals(block1, chunk.getBlocks().get(1));
        } catch (BlockNotFoundException e) {
            Assertions.fail(e.getStackTrace().toString());
        }
    }


    @Test

    public void notIntest() {
        Block block = Mockito.mock(Block.class);
        Block block1 = Mockito.mock(Block.class);
        Block block2 = Mockito.mock(Block.class);
        Block block3 = Mockito.mock(Block.class);
        Block block4 = Mockito.mock(Block.class);

        Chunk chunk = new Chunk(2);

        List<Block> list = new ArrayList<>();
        list.add(block);
        list.add(block1);
        list.add(block2);

        chunk.setBlocks(list);

        Assertions.assertTrue(chunk.notIn(block3));
        Assertions.assertTrue(chunk.notIn(block4));
        Assertions.assertFalse(chunk.notIn(block2));
        Assertions.assertFalse(chunk.notIn(block1));
        Assertions.assertFalse(chunk.notIn(block));
    }

    @Test

    public void validCoordstest() throws InvalidPositionException {
        Chunk chunk = new Chunk(1);
        Block block1 = Mockito.mock(Block.class);
        Mockito.when(block1.getPosition()).thenReturn(new Position(16, 0));
        Block block2 = Mockito.mock(Block.class);
        Mockito.when(block2.getPosition()).thenReturn(new Position(16, 200));
        Block block3 = Mockito.mock(Block.class);
        Mockito.when(block3.getPosition()).thenReturn(new Position(32, 0));
        Block block4 = Mockito.mock(Block.class);
        Mockito.when(block4.getPosition()).thenReturn(new Position(32, 200));
        Block block5 = Mockito.mock(Block.class);
        Mockito.when(block5.getPosition()).thenReturn(new Position(33, 0));
        Block block6 = Mockito.mock(Block.class);
        Mockito.when(block6.getPosition()).thenReturn(new Position(15, 200));
        Assertions.assertTrue(chunk.validCoords(block1));
        Assertions.assertTrue(chunk.validCoords(block2));
        Assertions.assertFalse(chunk.validCoords(block3));
        Assertions.assertFalse(chunk.validCoords(block4));
        Assertions.assertFalse(chunk.validCoords(block5));
        Assertions.assertFalse(chunk.validCoords(block6));


    }
}

           /*     Chunk c = new Chunk(0);
                Position p1 = new Position(0, 0);
                String s = mock(String.class);
                char ch = mock(char.class);
                boolean bool = mock(boolean.class);
                Block b1 = new Block(p1, bool, s, ch);
                c.addBlock(b1);
                Assertions.assertEquals(1, c.getBlocks().size());
            }
            catch (InvalidPositionException e){
                fail(e.getMessage());
            }
        }*/




