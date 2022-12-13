package ldts.terrarialike.controller.craftingFactories;

import ldts.terrarialike.controller.itemInteractions.PickaxeItemInteraction;
import ldts.terrarialike.exceptions.InvalidQuantityException;
import ldts.terrarialike.model.CraftingRecipe;
import ldts.terrarialike.model.Item;
import ldts.terrarialike.model.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static ldts.terrarialike.controller.craftingFactories.OreIngotFactory.ironIngot;

public class PickaxeFactory implements CraftingFactoryInteface{

    public static final Item woodenPickaxe
            = new Item('P', "Wooden pickaxe", new PickaxeItemInteraction(2));

    public static final Item stonePickaxe
            = new Item('P', "Stone pickaxe", new PickaxeItemInteraction(3));

    public static final Item ironPickaxe
            = new Item('P', "Iron pickaxe", new PickaxeItemInteraction(4));
    @Override
    public List<CraftingRecipe> build() throws InvalidQuantityException {
        List<CraftingRecipe> craftingRecipes = new ArrayList<>();

        List<ItemStack> woodenPickaxeInputs = new ArrayList<>();
        woodenPickaxeInputs.add(new ItemStack(WoodenItemsFactory.stickItem, 2));
        woodenPickaxeInputs.add(new ItemStack(new Item('W', "Wood block", null), 3));

        craftingRecipes.add(new CraftingRecipe(woodenPickaxeInputs, List.of(new ItemStack(woodenPickaxe,1))));

        List<ItemStack> stonePickaxeInputs = new ArrayList<>();
        stonePickaxeInputs.add(new ItemStack(WoodenItemsFactory.stickItem, 2));
        stonePickaxeInputs.add(new ItemStack(new Item('S', "Stone block", null), 3));
        craftingRecipes.add(new CraftingRecipe(stonePickaxeInputs, List.of(new ItemStack(stonePickaxe,1))));

        List<ItemStack> ironPickaxeInputs = new ArrayList<>();
        ironPickaxeInputs.add(new ItemStack(WoodenItemsFactory.stickItem, 2));
        ironPickaxeInputs.add(new ItemStack(ironIngot, 3));
        craftingRecipes.add(new CraftingRecipe(ironPickaxeInputs, List.of(new ItemStack(ironPickaxe,1))));




        return craftingRecipes;
    }
}
