package ldts.terrarialike.view.menus;

import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.RadioBoxList;
import ldts.terrarialike.GUI.GUILanterna;
import ldts.terrarialike.controller.GameController;
import ldts.terrarialike.controller.craftingFactories.CraftingFactories;
import ldts.terrarialike.controller.craftingFactories.CraftingFactoryInteface;
import ldts.terrarialike.exceptions.InvalidQuantityException;
import ldts.terrarialike.exceptions.InventoryFullException;
import ldts.terrarialike.exceptions.ItemNotFoundException;
import ldts.terrarialike.model.CraftingRecipe;
import ldts.terrarialike.model.Inventory;
import ldts.terrarialike.model.ItemStack;
import ldts.terrarialike.model.World;
import ldts.terrarialike.statemanager.State;
import ldts.terrarialike.statemanager.StateManager;
import ldts.terrarialike.view.GameView;

import java.util.ArrayList;
import java.util.List;

public class CraftingMenuView extends AbstractMenuView{

    private Inventory inventory;
    private List<CraftingRecipe> craftingRecipesList;

    private List<CraftingRecipe> availableCraftingRecipes;

    private RadioBoxList<String> availableCraftingComponent;

    public CraftingMenuView(StateManager stateManager, GUILanterna gui, Inventory inventory) {
        super(stateManager, gui, "TerrariaLike - Crafting Menu");
        this.inventory = inventory;
        this.craftingRecipesList =new ArrayList<>();
        for(CraftingFactoryInteface craftingFactoryInteface : CraftingFactories.craftingFactoryInterfaces){
            try {
                craftingRecipesList.addAll(craftingFactoryInteface.build());
            } catch (InvalidQuantityException ignored) {
            }
        }
        this.availableCraftingRecipes = new ArrayList<>();
        build();
    }

    @Override
    public void draw() {
        updateLabels(availableCraftingComponent);
    }

    @Override
    protected List<Component> mainWindowComponentBuilder() {
        List<Component> componentList = new ArrayList<>();
        availableCraftingComponent = new RadioBoxList<>();
        updateLabels(availableCraftingComponent);

        componentList.add(availableCraftingComponent);

        componentList.add(new Button("Craft...", () -> {
            int index = availableCraftingComponent.getCheckedItemIndex();
            if(index == -1) return;
            CraftingRecipe craftingRecipe = availableCraftingRecipes.get(index);
            for(ItemStack input : craftingRecipe.getInputItems()){
                try {
                    inventory.remove(input.getItem(), input.getQuantity());
                } catch (InvalidQuantityException | ItemNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            for(ItemStack output: craftingRecipe.getOutputItems()){
                try {
                    inventory.add(output.getItem(), output.getQuantity());
                } catch (InventoryFullException ignored) { //cope
                } catch (InvalidQuantityException e) {
                    throw new RuntimeException(e);
                }

            }

        }));
        componentList.add(new Button("Done...", () -> {
            State worldState = new State(World.class, GameView.class, GameController.class);
            stateManager.selectState(worldState);
            this.gui.removeCurrentWindowsStack();
        }));
        return componentList;

    }


    private void updateLabels(RadioBoxList<String> inventoryLabels){
        List<CraftingRecipe> newAvailableCraftingRecipes = new ArrayList<>();
        for(CraftingRecipe recipe : craftingRecipesList){
            if(recipe.isPossible(inventory)){
                newAvailableCraftingRecipes.add(recipe);
            }
        }
        if(!newAvailableCraftingRecipes.equals(availableCraftingRecipes)){
            availableCraftingRecipes = newAvailableCraftingRecipes;
            availableCraftingComponent.clearItems();
            for(int i = 0; i < availableCraftingRecipes.size(); i++){
                availableCraftingComponent.addItem(String.format("%d: %s",i+1,availableCraftingRecipes.get(i).toString()));
            }
        }
    }
}
