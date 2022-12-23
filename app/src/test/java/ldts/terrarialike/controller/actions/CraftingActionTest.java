package ldts.terrarialike.controller.actions;

import ldts.terrarialike.GUI.GUILanterna;
import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.exceptions.NotInitializedStateException;
import ldts.terrarialike.model.Player;
import ldts.terrarialike.statemanager.State;
import ldts.terrarialike.statemanager.StateManager;
import ldts.terrarialike.view.menus.CraftingMenuView;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CraftingActionTest {

    @Test

    public void testproccessAction() throws NotInitializedStateException {


        List<com.googlecode.lanterna.input.KeyStroke> arrowKeys = new ArrayList<>();
        List<State> stateManagerlist = new ArrayList<>();

        State craftingManager = new State(Object.class, CraftingMenuView.class, Object.class);
        stateManagerlist.add(craftingManager);


        StateManager stateManager = Mockito.mock(StateManager.class);
        GUILanterna guiLanterna = Mockito.mock(GUILanterna.class);
        Player player = Mockito.mock(Player.class);
        Mockito.when(stateManager.getStates()).thenReturn(stateManagerlist);


        CraftingAction craftingAction = new CraftingAction(stateManager, guiLanterna); // ver se remove o state, se adiciona o state e se seleciona o state)
        List<GameEvent> gameEvents = craftingAction.processAction(arrowKeys, player);

        Mockito.verify(stateManager, Mockito.times(1)).removeState(craftingManager);
        Mockito.verify(stateManager, Mockito.times(1)).addState(craftingManager);
        Mockito.verify(stateManager, Mockito.times(1)).selectState(craftingManager);

    }
}
