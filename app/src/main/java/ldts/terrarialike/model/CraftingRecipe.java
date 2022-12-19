package ldts.terrarialike.model;

import java.util.List;
import java.util.Objects;

public class CraftingRecipe {




    //this assumes that every item in the itemStack is unique
    //TODO: maybe make a reduce then we assume that itemStacks are not unique (because they are limited in quantity)
    private List<ItemStack> inputItems;

    private List<ItemStack> outputItems;

    public CraftingRecipe(List<ItemStack> inputItems, List<ItemStack> outputItems) {
        this.inputItems = inputItems;
        this.outputItems = outputItems;
    }

    public boolean isPossible(Inventory inventory){
        for (ItemStack i :
                inputItems) {
            int pog = inventory.getInventory().stream().mapToInt((itemStack) -> {
                if(itemStack.getItem().equals(i.getItem())) return itemStack.getQuantity();
                return 0;
            }).reduce(0, Integer::sum);
            if(pog < i.getQuantity()){
                return false;
            }
        }
        return true;
    }

    public List<ItemStack> getInputItems() {
        return inputItems;
    }

    public List<ItemStack> getOutputItems() {
        return outputItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CraftingRecipe that = (CraftingRecipe) o;
        return Objects.equals(inputItems, that.inputItems) && Objects.equals(outputItems, that.outputItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inputItems, outputItems);
    }

    @Override
    public String toString() {
        StringBuilder inputString = new StringBuilder(new String());
        for(ItemStack input: inputItems){
            inputString.append(String.format(" %s:%d", input.getItem().getName(), input.getQuantity()));
        }
        StringBuilder outputString = new StringBuilder(new String());
        for(ItemStack output: outputItems){
            outputString.append(String.format(" %s:%d", output.getItem().getName(), output.getQuantity()));
        }
        return inputString + "-> " + outputString;
    }
}
