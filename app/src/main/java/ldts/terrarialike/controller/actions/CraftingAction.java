package ldts.terrarialike.controller.actions;

import com.googlecode.lanterna.input.KeyStroke;
import ldts.terrarialike.GUI.GUILanterna;
import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.exceptions.NotInitializedStateException;
import ldts.terrarialike.model.Player;
import ldts.terrarialike.statemanager.State;
import ldts.terrarialike.statemanager.StateManager;
import ldts.terrarialike.utils.InputUtils;
import ldts.terrarialike.view.menus.CraftingMenuView;

import java.util.ArrayList;
import java.util.List;

public class CraftingAction extends AbstractAction {
    private StateManager stateManager;
    private GUILanterna guiLanterna;

    public CraftingAction(StateManager stateManager, GUILanterna guiLanterna, InputUtils inputUtils) {
        super(false, inputUtils);
        this.stateManager = stateManager;
        this.guiLanterna = guiLanterna;
    }

    @Override
    public List<GameEvent> processAction(List<KeyStroke> arrowKeys, Player player) {
        State craftingManager = new State(Object.class, CraftingMenuView.class, Object.class);
        if(stateManager.getStates().contains(craftingManager)){
            stateManager.removeState(craftingManager);
        }
        craftingManager.initializeDataClass();
        craftingManager.initializeControllerClass();
        craftingManager.initializeViewClass(stateManager,guiLanterna, player.getInventory());
        try {
            stateManager.addState(craftingManager);
            stateManager.selectState(craftingManager);
        } catch (NotInitializedStateException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<>();
    }
}
