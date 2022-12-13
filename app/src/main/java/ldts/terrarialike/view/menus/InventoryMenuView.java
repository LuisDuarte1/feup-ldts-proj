package ldts.terrarialike.view.menus;

import com.googlecode.lanterna.gui2.*;
import ldts.terrarialike.GUI.GUILanterna;
import ldts.terrarialike.GUI.MenuWindowBuilder;
import ldts.terrarialike.controller.GameController;
import ldts.terrarialike.exceptions.InvalidIndexException;
import ldts.terrarialike.model.Inventory;
import ldts.terrarialike.model.ItemStack;
import ldts.terrarialike.model.World;
import ldts.terrarialike.statemanager.State;
import ldts.terrarialike.statemanager.StateManager;
import ldts.terrarialike.view.GameView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class InventoryMenuView extends AbstractMenuView{
    private final Inventory inventory;

    public InventoryMenuView(Inventory inventory, StateManager stateManager, GUILanterna gui) {
        super(stateManager, gui, "TerrariaLike - Player Inventory Manager");
        this.inventory = inventory;
        build();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void draw() {
        Panel component = (Panel) mainWindow.getComponent();
        for (Component c :
                component.getChildren()) {
            if(c instanceof RadioBoxList<?>){
                RadioBoxList<String> pog = (RadioBoxList<String>) c;
                updateLabels(pog);
            }
        }
    }

    private Window createItemActionsWindow(int index){
        ItemStack itemStack = null;
        try {
            itemStack = inventory.getItemStack(index);
        } catch (InvalidIndexException e) {
            throw new RuntimeException("This should never happen in InventoryMenuView while creating the item actions"+
                " Good luck :)");
        }
        List<Component> componentList = new ArrayList<>();
        Integer finalIndex = index;
        componentList.add(new Label("Swap for item index (empty does nothing):"));
        TextBox indexBox = new TextBox();
        indexBox.setValidationPattern(Pattern.compile("[0-9]*"));
        componentList.add(indexBox);
        componentList.add(new Button("Done...", () -> {
            if(indexBox.getText().length() > 0){
                int wanted_index = Integer.parseInt(indexBox.getText()) - 1;
                if(wanted_index < inventory.getSize()){
                    List<ItemStack> itemStacks = inventory.getInventory();
                    Collections.swap(itemStacks,index,wanted_index);
                } else{
                    System.out.println("User tried to input an index that is out of range...");
                }
            }
            this.gui.removeCurrentWindowFromStack();
        }));
    
        return MenuWindowBuilder.build(getItemStackString(index, itemStack), componentList);
    }

    @Override
    protected List<Component> mainWindowComponentBuilder() {
        List<Component> components = new ArrayList<>();
        RadioBoxList<String> inventoryLabels = new RadioBoxList<>();
        updateLabels(inventoryLabels);
        components.add(inventoryLabels);
        components.add(new Button("Swap...", ()->{
            this.gui.addWindowToStack(createItemActionsWindow(inventoryLabels.getCheckedItemIndex()));
        }));
        components.add(new Button("Select...", ()->{
            try {
                this.inventory.setSelecteditem(inventoryLabels.getCheckedItemIndex());
            } catch (InvalidIndexException e) {
                throw new RuntimeException("This shouldn't never happen because we guarrentee it :) java moment. Rust > java");
            }
        }));
        components.add(new Button("Done...", ()->{
            State worldState = new State(World.class, GameView.class, GameController.class);
            stateManager.selectState(worldState);
            this.gui.removeCurrentWindowsStack();
        }));
        return components;
    }

    private void updateLabels(RadioBoxList<String> inventoryLabels) {
        List<String> labels = new ArrayList<>();
        for (int i = 0; i < inventory.getSize(); i++) {
            try {
                labels.add(getItemStackString(i, inventory.getItemStack(i)));
            } catch (InvalidIndexException ignored) {

            }
        }
        if(!labels.equals(inventoryLabels.getItems())) {

            inventoryLabels.clearItems();
            for (int i = 0; i < inventory.getSize(); i++) {
                try {
                    ItemStack itemStack = inventory.getItemStack(i);
                    inventoryLabels.addItem(getItemStackString(i, itemStack));
                } catch (InvalidIndexException e) {
                    //do nothing simply skip to assure that the program is stable even if it's wrong
                }
            }
        }
    }

    private static String getItemStackString(int i, ItemStack itemStack) {
        return String.format("%d: %c - %s Count: %d", i + 1,
                itemStack.getItem().getRepresentation(), itemStack.getItem().getName(), itemStack.getQuantity());
    }
}
