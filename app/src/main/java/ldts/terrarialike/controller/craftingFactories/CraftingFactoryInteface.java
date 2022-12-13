package ldts.terrarialike.controller.craftingFactories;

import ldts.terrarialike.exceptions.InvalidQuantityException;
import ldts.terrarialike.model.CraftingRecipe;

import java.util.List;

public interface CraftingFactoryInteface {

    List<CraftingRecipe> build() throws InvalidQuantityException;
}
