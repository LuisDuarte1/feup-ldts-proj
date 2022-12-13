package ldts.terrarialike.controller.craftingFactories;

import ldts.terrarialike.exceptions.InvalidQuantityException;
import ldts.terrarialike.model.CraftingRecipe;
import ldts.terrarialike.model.Item;
import ldts.terrarialike.model.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class WoodenItemsFactory implements CraftingFactoryInteface{

    public static final Item stickItem = new Item('/', "Wooden stick", null);

    @Override
    public List<CraftingRecipe> build() throws InvalidQuantityException {
        ItemStack stickOutputCrafting = new ItemStack(stickItem, 4);
        ItemStack stickInputCrafting = new ItemStack(new Item('W', "Wood block", null), 1);
        CraftingRecipe craftingRecipe = new CraftingRecipe(List.of(stickInputCrafting), List.of(stickOutputCrafting));
        return List.of(craftingRecipe);
    }
}
