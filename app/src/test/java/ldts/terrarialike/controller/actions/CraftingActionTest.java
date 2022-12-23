package ldts.terrarialike.controller.actions;

import ldts.terrarialike.GUI.GUILanterna;
import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.exceptions.NotInitializedStateException;
import ldts.terrarialike.model.Inventory;
import ldts.terrarialike.model.Player;
import ldts.terrarialike.statemanager.State;
import ldts.terrarialike.statemanager.StateManager;
import ldts.terrarialike.utils.InputUtils;
import ldts.terrarialike.view.menus.CraftingMenuView;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CraftingActionTest {

    @Test

    public void testproccessAction() throws NotInitializedStateException {

        InputUtils inputUtils = Mockito.mock(InputUtils.class);
        List<com.googlecode.lanterna.input.KeyStroke> arrowKeys = new ArrayList<>();
        List<State> stateManagerlist = new ArrayList<>();

        State craftingManager = Mockito.mock(State.class);
        stateManagerlist.add(craftingManager);

        Inventory inventory = Mockito.mock(Inventory.class);





        StateManager stateManager = Mockito.mock(StateManager.class);
        GUILanterna guiLanterna = Mockito.mock(GUILanterna.class);
        Player player = Mockito.mock(Player.class);
        Mockito.when(stateManager.getStates()).thenReturn(stateManagerlist);
        Mockito.when(player.getInventory()).thenReturn(inventory);


        CraftingAction craftingAction = new CraftingAction(stateManager, guiLanterna,inputUtils); // ver se remove o state, se adiciona o state e se seleciona o state)
        List<GameEvent> gameEvents = craftingAction.processAction(arrowKeys, player);


        Mockito.verify(stateManager, Mockito.times(1)).addState(Mockito.any());
        Mockito.verify(stateManager, Mockito.times(1)).selectState(Mockito.any());

    }
}
