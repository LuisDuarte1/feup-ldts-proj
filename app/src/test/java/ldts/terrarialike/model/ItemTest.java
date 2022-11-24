package ldts.terrarialike.model;

import ldts.terrarialike.controller.ItemInteraction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ItemTest {

    private ItemInteraction itemInteraction;

    @BeforeEach

    public void setUp() throws Exception{
         this.itemInteraction = Mockito.mock(ItemInteraction.class);
    }
    @Test

    public void testItem(){
        Item item = new Item('a', "test", this.itemInteraction);

        Assertions.assertEquals('a', item.getRepresentation());
        Assertions.assertEquals("test", item.getName());
        Assertions.assertEquals(this.itemInteraction, item.getInteraction());
    }

}
