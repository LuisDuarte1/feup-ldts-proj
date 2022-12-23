package ldts.terrarialike.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class CraftingRecipeTest {

    @Test
    public void isPossibleTest(){
        Item i1 = Mockito.mock(Item.class);
        Item i2 = Mockito.mock(Item.class);
        ItemStack it1Input = Mockito.mock(ItemStack.class);
        Mockito.when(it1Input.getItem()).thenReturn(i1);
        Mockito.when(it1Input.getQuantity()).thenReturn(5);

        ItemStack it2Input = Mockito.mock(ItemStack.class);
        Mockito.when(it2Input.getItem()).thenReturn(i2);
        Mockito.when(it2Input.getQuantity()).thenReturn(1);

        Inventory inventory = Mockito.mock(Inventory.class);

        ItemStack it1False = Mockito.mock(ItemStack.class);
        Mockito.when(it1False.getItem()).thenReturn(i1);
        Mockito.when(it1False.getQuantity()).thenReturn(4);

        ItemStack it2False = Mockito.mock(ItemStack.class);
        Mockito.when(it2False.getItem()).thenReturn(i2);
        Mockito.when(it2False.getQuantity()).thenReturn(1);

        Mockito.when(inventory.getInventory()).thenReturn(List.of(it1False,it2False));

        CraftingRecipe craftingRecipe = new CraftingRecipe(List.of(it1Input, it2Input), List.of());
        Assertions.assertFalse(craftingRecipe.isPossible(inventory));

        ItemStack it1True = Mockito.mock(ItemStack.class);
        Mockito.when(it1True.getItem()).thenReturn(i1);
        Mockito.when(it1True.getQuantity()).thenReturn(5);


        Mockito.when(inventory.getInventory()).thenReturn(List.of(it1True,it2False));
        Assertions.assertTrue(craftingRecipe.isPossible(inventory));

        CraftingRecipe craftingRecipe2 = new CraftingRecipe(List.of(it1Input, it2Input), List.of());
        Assertions.assertEquals(craftingRecipe2,craftingRecipe);
        Assertions.assertEquals(craftingRecipe,craftingRecipe);
        if((craftingRecipe.equals(null) || craftingRecipe.equals(Mockito.mock(Inventory.class))))
            fail("Crafting recipe equals doens't work");
    }

    @Test
    public void toStringTest(){
        Item i1 = Mockito.mock(Item.class);
        Mockito.when(i1.getRepresentation()).thenReturn('a');
        Mockito.when(i1.getName()).thenReturn("Pog");

        Item i2 = Mockito.mock(Item.class);
        Mockito.when(i2.getRepresentation()).thenReturn('b');
        Mockito.when(i2.getName()).thenReturn("Pog1");


        ItemStack it1Input = Mockito.mock(ItemStack.class);
        Mockito.when(it1Input.getItem()).thenReturn(i1);
        Mockito.when(it1Input.getQuantity()).thenReturn(5);

        ItemStack it2Input = Mockito.mock(ItemStack.class);
        Mockito.when(it2Input.getItem()).thenReturn(i2);
        Mockito.when(it2Input.getQuantity()).thenReturn(1);


        Item i3 = Mockito.mock(Item.class);
        Mockito.when(i2.getRepresentation()).thenReturn('c');
        Mockito.when(i3.getName()).thenReturn("Pog2");

        ItemStack it3Output = Mockito.mock(ItemStack.class);
        Mockito.when(it3Output.getItem()).thenReturn(i3);
        Mockito.when(it3Output.getQuantity()).thenReturn(1);

        CraftingRecipe craftingRecipe = new CraftingRecipe(List.of(it1Input,it2Input), List.of(it3Output));
        Assertions.assertEquals(" Pog:5 Pog1:1->  Pog2:1", craftingRecipe.toString());

    }

}
