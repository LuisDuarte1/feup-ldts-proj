package ldts.terrarialike.controller.actions;

import com.googlecode.lanterna.input.KeyStroke;
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
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class InventoryActionTest {

    @Test

        public void inventoryActionTest() throws NotInitializedStateException {

    InputUtils inputUtils = Mockito.mock(InputUtils.class);
    List<KeyStroke> arrowKeys = new ArrayList<>();
    List<State> stateManagerlist = new ArrayList<>();




    State inventoryManager = new State(Object.class, CraftingMenuView.class, Object.class);
        stateManagerlist.add(inventoryManager);


    StateManager stateManager = Mockito.mock(StateManager.class);
    GUILanterna guiLanterna = Mockito.mock(GUILanterna.class);
    Player player = Mockito.mock(Player.class);
    Inventory inventory = Mockito.mock(Inventory.class);
    Mockito.when(player.getInventory()).thenReturn(inventory);
        Mockito.when(stateManager.getStates()).thenReturn(stateManagerlist);


    InventoryAction inventoryAction = new InventoryAction (stateManager, guiLanterna,inputUtils); // ver se remove o state, se adiciona o state e se seleciona o state)
    List<GameEvent> gameEvents = inventoryAction.processAction(arrowKeys, player);

        Mockito.verify(stateManager, Mockito.times(1)).addState(Mockito.any());
        Mockito.verify(stateManager, Mockito.times(1)).selectState(Mockito.any());



}}
