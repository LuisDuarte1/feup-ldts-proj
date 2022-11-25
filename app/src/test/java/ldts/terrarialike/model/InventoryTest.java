package ldts.terrarialike.model;
import ldts.terrarialike.controller.ItemInteraction;
import ldts.terrarialike.exceptions.InvalidIndexException;
import ldts.terrarialike.exceptions.InvalidQuantityException;
import ldts.terrarialike.exceptions.InvalidSizeException;
import ldts.terrarialike.exceptions.InventoryFullException;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class InventoryTest {

    private Inventory inventorytest;
    static private ItemInteraction itemInteraction;

    @BeforeAll

    public static void setUp(){

        itemInteraction = Mockito.mock(ItemInteraction.class);

    }




    @Test

    public void testInventory() {
        try{
            this.inventorytest  = new Inventory(1);
            Assertions.assertEquals(1, this.inventorytest.getSize());
            this.inventorytest = new Inventory(0);
            Assertions.assertEquals(0, this.inventorytest.getSize());
            this.inventorytest = new Inventory(100);
            Assertions.assertEquals(Inventory.MAX_SIZE, this.inventorytest.getSize());}
        catch(InvalidSizeException e) {
            Assertions.fail(e.getStackTrace().toString());
        }
    }
    @Test
    public void testInventoryInvalidSize() {
        try{
            this.inventorytest = new Inventory(Inventory.MAX_SIZE + 1);
            Assertions.fail("InvalidSizeException not thrown");
        } catch (InvalidSizeException e) {
            Assertions.assertEquals("Invalid size", e.getMessage());
        }
    }

    @Test

    public void testGetSize() {
        try {
            this.inventorytest = new Inventory(1);
            Assertions.assertEquals(1, inventorytest.getSize());
        }
        catch(InvalidSizeException e){
            Assertions.fail(e.getStackTrace().toString());
        }}






    @Test

    public void testGetSelectedItem(){ // ver se pode completar
            try{
                this.inventorytest = new Inventory(1);
                Assertions.assertEquals(0,inventorytest.getSelecteditemindex());
                this.inventorytest = new Inventory(3);
                Item i = Mockito.mock(Item.class);
                Item i2 = Mockito.mock(Item.class);
                Item i3 = Mockito.mock(Item.class);
                inventorytest.add(i,1);
                inventorytest.add(i2,4);
                inventorytest.add(i3,ItemStack.MAXQUANTITY);
                inventorytest.setSelecteditem(2);
                Assertions.assertEquals(2,inventorytest.getSelecteditemindex());
            } catch (InventoryFullException | InvalidQuantityException | InvalidIndexException | InvalidSizeException e) {
                Assertions.fail(e.getStackTrace().toString());
            }

    }

    @Test

    public void testGetSelectedItemInvalidIndex()  {
        try {
            this.inventorytest = new Inventory(1);
            inventorytest.setSelecteditem(1);
            Assertions.fail("InvalidIndexException not thrown");
        }catch(InvalidSizeException | InvalidIndexException e ){
            Assertions.assertEquals("Index not valid", e.getMessage());
        }
    }



    @Test
    public void doesNotContainItemTest() throws InvalidSizeException{
        this.inventorytest = new Inventory(1);
        Item i1 = Mockito.mock(Item.class);
        ItemStack s =  inventorytest.contains(i1);
        Assertions.assertEquals(null, s);

    }

    @Test
    public void containsItemTest() throws InvalidSizeException{
        this.inventorytest = new Inventory(1);
        Item i1 = Mockito.mock(Item.class);
        try {
            inventorytest.add(i1, ItemStack.MAXQUANTITY-1);
            ItemStack is = inventorytest.contains(i1);
            Assertions.assertEquals(i1, is.getItem());
            Assertions.assertEquals(i1, is.getItem());
            Assertions.assertEquals(ItemStack.MAXQUANTITY-1, is.getQuantity());
        } catch (InventoryFullException | InvalidQuantityException e) {
            fail(e.getMessage());
        }

    }



    }



