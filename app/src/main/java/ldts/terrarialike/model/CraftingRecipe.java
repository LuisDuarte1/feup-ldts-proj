package ldts.terrarialike.model;

import java.util.List;

public class CraftingRecipe {


    //this assumes that every item in the itemStack is unique
    //TODO: maybe make a reduce then we assume that itemStacks are not unique (because they are limited in quantity)
    public List<ItemStack> inputItems;

    public List<ItemStack> outputItems;

    public CraftingRecipe(List<ItemStack> inputItems, List<ItemStack> outputItems) {
        this.inputItems = inputItems;
        this.outputItems = outputItems;
    }

    public boolean isPossible(Inventory inventory){
        for (ItemStack i :
                inputItems) {
            Integer pog = inventory.getInventory().stream().mapToInt((itemStack) -> {
                if(itemStack.getItem() == i.getItem()) return itemStack.getQuantity();
                return 0;
            }).reduce(0, Integer::sum);
            if(pog < i.getQuantity()){
                return false;
            }
        }
        return true;
    }
}
