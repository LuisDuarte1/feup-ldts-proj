package ldts.terrarialike.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import ldts.terrarialike.exceptions.InvalidColorStringException;

public class BlockInfoTest {
    @Test
   public void validBlockColorConstructor(){
        Position p = mock(Position.class);
        try {
            BlockInfo b = new BlockInfo(1, BlockType.DIRT, "#ffffff", 'a');
            assertEquals("#ffffff", b.getBackgroundcolor());
            assertEquals("#ffffff", b.getForegroundColor());
            assertEquals('a', b.getRepresentation_char());
        } catch (InvalidColorStringException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void doesntStartWithHastasgColorTest(){
        try {
            BlockInfo b = new BlockInfo(1, BlockType.DIRT, "fff#fff", 'a');
            fail("InvalidColorStringException not thrown...");
        } catch (InvalidColorStringException e) {

        }
    }

    @Test
    public void incorretLengthColorTest(){
        Position p = mock(Position.class);
        try {
            BlockInfo b = new BlockInfo(1, BlockType.DIRT, "#fffffff", 'a');

            fail("InvalidColorStringException not thrown...");
        } catch (InvalidColorStringException e) {

        }
    }
}