package ldts.terrarialike.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import ldts.terrarialike.exceptions.InvalidColorStringException;

public class BlockTest {
    //FIXME: add tests for Block
    /*
    @Test
   public void validBlockColorConstructor(){
        Position p = mock(Position.class);
        try {
            Block b = new Block(p, "#ffffff", 'a');
            assertEquals(p, b.getPosition());
            assertEquals("#ffffff", b.getBackgroundColor());
            assertEquals('a', b.getRepresentation_char());
        } catch (InvalidColorStringException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void doesntStartWithHastasgColorTest(){
        Position p = mock(Position.class);
        try {
            Block b = new Block(p, "fff#fff", 'a');
            fail("InvalidColorStringException not thrown...");
        } catch (InvalidColorStringException e) {

        }
    }

    @Test
    public void incorretLengthColorTest(){
        Position p = mock(Position.class);
        try {
            Block b = new Block(p, "#fffffff", 'a');
            fail("InvalidColorStringException not thrown...");
        } catch (InvalidColorStringException e) {
            
        }
    }*/
}
