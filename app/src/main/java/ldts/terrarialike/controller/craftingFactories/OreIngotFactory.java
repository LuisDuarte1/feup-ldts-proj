package ldts.terrarialike.controller.craftingFactories;

import ldts.terrarialike.exceptions.InvalidQuantityException;
import ldts.terrarialike.model.CraftingRecipe;
import ldts.terrarialike.model.Item;
import ldts.terrarialike.model.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class OreIngotFactory implements CraftingFactoryInteface{

    public static Item ironIngot = new Item('I', "Iron Ingot", null);

    @Override
    public List<CraftingRecipe> build() throws InvalidQuantityException {
        List<CraftingRecipe> oreCraftingRecipes = new ArrayList<>();
        List<ItemStack> ironOreInputs = new ArrayList<>();
        ironOreInputs.add(new ItemStack(new Item('i', "Iron ore", null), 1));
        ironOreInputs.add(new ItemStack(new Item('c', "Coal", null), 2));
        oreCraftingRecipes.add(new CraftingRecipe(ironOreInputs, List.of(new ItemStack(ironIngot, 1))));

        return oreCraftingRecipes;
    }
}
