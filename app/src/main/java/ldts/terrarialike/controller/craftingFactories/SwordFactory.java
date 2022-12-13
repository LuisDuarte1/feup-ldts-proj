package ldts.terrarialike.controller.craftingFactories;

import ldts.terrarialike.controller.itemInteractions.SwordItemInteraction;
import ldts.terrarialike.exceptions.InvalidQuantityException;
import ldts.terrarialike.model.CraftingRecipe;
import ldts.terrarialike.model.Item;
import ldts.terrarialike.model.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static ldts.terrarialike.controller.craftingFactories.OreIngotFactory.ironIngot;

public class SwordFactory implements  CraftingFactoryInteface{

    public static Item woodenSword = new Item('S', "Wooden Sword",
            new SwordItemInteraction(4,10));

    public static Item stoneSword = new Item('S', "Stone Sword",
            new SwordItemInteraction(5,15));

    public static Item ironSword = new Item('S', "Iron Sword",
            new SwordItemInteraction(5, 30));

    @Override
    public List<CraftingRecipe> build() throws InvalidQuantityException {
        List<CraftingRecipe> craftingRecipes = new ArrayList<>();

        List<ItemStack> woodenSwordInputs = new ArrayList<>();
        woodenSwordInputs.add(new ItemStack(WoodenItemsFactory.stickItem, 1));
        woodenSwordInputs.add(new ItemStack(new Item('W', "Wood block", null), 2));

        craftingRecipes.add(new CraftingRecipe(woodenSwordInputs, List.of(new ItemStack(woodenSword,1))));

        List<ItemStack> stonePickaxeInputs = new ArrayList<>();
        stonePickaxeInputs.add(new ItemStack(WoodenItemsFactory.stickItem, 1));
        stonePickaxeInputs.add(new ItemStack(new Item('S', "Stone block", null), 2));
        craftingRecipes.add(new CraftingRecipe(stonePickaxeInputs, List.of(new ItemStack(stoneSword,1))));

        List<ItemStack> ironPickaxeInputs = new ArrayList<>();
        ironPickaxeInputs.add(new ItemStack(WoodenItemsFactory.stickItem, 1));
        ironPickaxeInputs.add(new ItemStack(ironIngot, 2));
        craftingRecipes.add(new CraftingRecipe(ironPickaxeInputs, List.of(new ItemStack(ironSword,1))));


        return craftingRecipes;

    }
}
