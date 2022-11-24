package ldts.terrarialike.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import ldts.terrarialike.exceptions.InvalidPositionException;

public class PostitionTest {

    @Test
    void validPositionTest(){
        try{
            Position p = new Position(3, (Position.Y_MIN + Position.Y_MAX)/2);

            assertEquals(3, p.getX());
            assertEquals((Position.Y_MIN + Position.Y_MAX)/2, p.getY());

            Position p1 = new Position(3, Position.Y_MAX);
            Position p2 = new Position(3, Position.Y_MIN);



        } catch(InvalidPositionException e){
            fail(e.getStackTrace().toString());
        }
    }

    @Test
    void positionYTooLowTest(){
        assertThrows(InvalidPositionException.class,
         () -> {
            Position p = new Position(0, Position.Y_MIN - 1);
         });
         assertThrows(InvalidPositionException.class,
         () -> {
            Position p = new Position(0, Position.Y_MAX +  1);
         });
    }

    @Test
    void compareEqualTest() throws InvalidPositionException{
        Position p = new Position(2, Position.Y_MAX);
        Position peq = new Position(2, Position.Y_MAX);

        assertEquals(true, peq.equals(p));
    }

    @Test
    void compareDifferentTest() throws InvalidPositionException{
        Position p = new Position(2, Position.Y_MAX);
        Position peq = new Position(3, Position.Y_MAX);   

        assertEquals(false, peq.equals(p));

    }
    
}
