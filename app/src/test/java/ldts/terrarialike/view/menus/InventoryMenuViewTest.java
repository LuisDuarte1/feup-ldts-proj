package ldts.terrarialike.view.menus;

import ldts.terrarialike.GUI.GUILanterna;
import ldts.terrarialike.model.Inventory;
import ldts.terrarialike.statemanager.StateManager;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class InventoryMenuViewTest {
    @Test
    public  void inventoryMenuViewTest(){
        StateManager stateManager = Mockito.mock(StateManager.class);
        GUILanterna guiLanterna = Mockito.mock(GUILanterna.class);
        Inventory inventory = Mockito.mock(Inventory.class);

        InventoryMenuView inventoryMenuView = new InventoryMenuView(inventory,stateManager,guiLanterna);

        inventoryMenuView.build();
        Mockito.verify(guiLanterna, Mockito.times(2)).addWindowToStack(Mockito.any());

        inventoryMenuView.draw();
    }

}
