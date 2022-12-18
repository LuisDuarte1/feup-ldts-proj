package ldts.terrarialike.controller.actions;

import com.googlecode.lanterna.input.KeyStroke;
import ldts.terrarialike.GUI.GUILanterna;
import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.exceptions.NotInitializedStateException;
import ldts.terrarialike.model.Player;
import ldts.terrarialike.statemanager.State;
import ldts.terrarialike.statemanager.StateManager;
import ldts.terrarialike.view.menus.InventoryMenuView;

import java.util.ArrayList;
import java.util.List;

public class InventoryAction extends AbstractAction {
    private StateManager stateManager;
    private GUILanterna guiLanterna;

    public InventoryAction(StateManager stateManager, GUILanterna guiLanterna) {
        super(false);
        this.stateManager = stateManager;
        this.guiLanterna = guiLanterna;
    }

    @Override
    public List<GameEvent> processAction(List<KeyStroke> arrowKeys, Player player) {
        State inventoryManager = new State(Object.class, InventoryMenuView.class, Object.class);
        if(stateManager.getStates().contains(inventoryManager)){
            stateManager.removeState(inventoryManager);
        }
        inventoryManager.initializeDataClass();
        inventoryManager.initializeControllerClass();
        inventoryManager.initializeViewClass(player.getInventory(),stateManager,guiLanterna);
        try {
            stateManager.addState(inventoryManager);
            stateManager.selectState(inventoryManager);
        } catch (NotInitializedStateException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<>();
    }
}
