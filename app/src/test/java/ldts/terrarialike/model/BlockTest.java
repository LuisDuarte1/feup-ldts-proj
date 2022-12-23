package ldts.terrarialike.model;

import ldts.terrarialike.exceptions.InvalidPositionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.fail;

public class BlockTest {

    @Test
    public void blockTest() throws InvalidPositionException {
        BlockInfo blockInfo1 = Mockito.mock(BlockInfo.class);
        BlockInfo blockInfo2 = Mockito.mock(BlockInfo.class);

        Block b1 = new Block(new Position(1,2), blockInfo1);
        Block b2 = new Block(new Position(1,2),true, blockInfo2);

        Assertions.assertNotSame(b1, b2);
        Assertions.assertEquals(b1, b2);
        Assertions.assertEquals(b1, b1);

        if((b1.equals(null) || b1.equals(Mockito.mock(Inventory.class))))
            fail("Block equals() doesn't work properly");

    }
}
