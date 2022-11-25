package ldts.terrarialike.model;

import ldts.terrarialike.exceptions.InvalidSizeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlayerTest {

    @Test
    public void gettersTest() throws InvalidSizeException {
        Position position = Mockito.mock(Position.class);

        Player p = new Player(position, 10);

        Assertions.assertFalse(p.getInventory()==null);
        Assertions.assertEquals(10,p.getHp());
        Assertions.assertEquals(position, p.getPosition());
        

    }

    @Test
    public void settersTest() throws InvalidSizeException{
        Position position = Mockito.mock(Position.class);
        Position position2 = Mockito.mock(Position.class);

        Player p = new Player(position, 10);

        p.setPosition(position);
        p.setHp(20);
        Assertions.assertEquals(position2, p.getPosition());
        Assertions.assertEquals(20, p.getHp());

    }
}
