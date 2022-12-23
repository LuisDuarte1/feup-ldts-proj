package ldts.terrarialike.view.menus;

import ldts.terrarialike.GUI.GUILanterna;
import ldts.terrarialike.model.Inventory;
import ldts.terrarialike.statemanager.StateManager;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CraftingMenuViewTest {
    @Test
    void craftingUpdateViewTest(){
        StateManager stateManager = Mockito.mock(StateManager.class);
        GUILanterna guiLanterna = Mockito.mock(GUILanterna.class);
        Inventory inventory = Mockito.mock(Inventory.class);

        CraftingMenuView craftingMenuView = new CraftingMenuView(stateManager,guiLanterna,inventory);

        craftingMenuView.build();
        Mockito.verify(guiLanterna, Mockito.times(2)).addWindowToStack(Mockito.any());

        craftingMenuView.draw();

    }
}
