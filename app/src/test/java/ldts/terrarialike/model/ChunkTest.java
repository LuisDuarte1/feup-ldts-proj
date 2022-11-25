package ldts.terrarialike.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;

import ldts.terrarialike.exceptions.InvalidColorStringException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ldts.terrarialike.exceptions.BlockNotFoundException;
import ldts.terrarialike.exceptions.InvalidPositionException;
import org.mockito.Mockito;

public class ChunkTest {

    @Test
    public void addValidBlockTest() throws InvalidPositionException, InvalidColorStringException {
        try {
            Chunk c = new Chunk(0);
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
    }
}
