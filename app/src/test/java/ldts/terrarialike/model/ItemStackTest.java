package ldts.terrarialike.model;

import ldts.terrarialike.exceptions.InvalidQuantityException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ItemStackTest {

    Item item;

    @BeforeEach

    public void setUp() throws Exception {

        item = Mockito.mock(Item.class);

    }

    @Test
    public void testItemStack() {
        try {
            ItemStack itemStack = new ItemStack(item, 1);
            Assertions.assertEquals(item, itemStack.getItem());
            Assertions.assertEquals(1, itemStack.getQuantity());

            ItemStack itemStack2 = new ItemStack(item, ItemStack.MAXQUANTITY);

        } catch (InvalidQuantityException e) {
            Assertions.fail(e.getStackTrace().toString());
        }
    }


    @Test
    public void testItemStackInvalidQuantity() {
        try {
            ItemStack itemStack = new ItemStack(item, ItemStack.MAXQUANTITY + 1);
            Assertions.fail("InvalidQuantityException not thrown");
        } catch (InvalidQuantityException e) {
            Assertions.assertEquals("Invalid quantity", e.getMessage());
        }
    }

    @Test

    public void testAddQuantity() {
        try {
            ItemStack itemStack = new ItemStack(item, 1);
            itemStack.add(1);
            Assertions.assertEquals(2, itemStack.getQuantity());
            itemStack.add(ItemStack.MAXQUANTITY-2);
            Assertions.assertEquals(ItemStack.MAXQUANTITY, itemStack.getQuantity());
        } catch (InvalidQuantityException e) {
            Assertions.fail(e.getStackTrace().toString());
        }
    }


    @Test
    public void testAddQuantityInvalidQuantity() {
        try {
            ItemStack itemStack = new ItemStack(item, 1);
            itemStack.add(ItemStack.MAXQUANTITY + 1);
            Assertions.fail("InvalidQuantityException not thrown");
        } catch (InvalidQuantityException e) {
            Assertions.assertEquals("Invalid quantity", e.getMessage());
        }
    }

    @Test
    public void testRemoveQuantity() {
        try {
            ItemStack itemStack = new ItemStack(item, 1);
            itemStack.remove(1);
            Assertions.assertEquals(0, itemStack.getQuantity());
            ItemStack itemStack2 = new ItemStack(item, ItemStack.MAXQUANTITY);
            Assertions.assertEquals(0, itemStack.getQuantity());
        } catch (InvalidQuantityException e) {
            Assertions.fail(e.getStackTrace().toString());
        }
    }

    @Test
    public void testRemoveQuantityInvalidQuantity() {
        try {
            ItemStack itemStack = new ItemStack(item, 1);
            itemStack.remove(ItemStack.MAXQUANTITY + 1);
            Assertions.fail("InvalidQuantityException not thrown");
        } catch (InvalidQuantityException e) {
            Assertions.assertEquals("Invalid quantity", e.getMessage());
        }
    }

    @Test

    public void testgetItem() {
        try {
            ItemStack itemStack = new ItemStack(item, 1);
            Assertions.assertEquals(item, itemStack.getItem());
        } catch (InvalidQuantityException e) {
            Assertions.fail(e.getStackTrace().toString());
        }
    }

    @Test

    public void testgetQuantity(){
        try{
            ItemStack itemStack = new ItemStack(item, 1);
            Assertions.assertEquals(1, itemStack.getQuantity());
        } catch (InvalidQuantityException e) {
            Assertions.fail(e.getStackTrace().toString());
        }
    }
}



