package ldts.terrarialike.view.menus;

import ldts.terrarialike.GUI.GUILanterna;
import ldts.terrarialike.statemanager.StateManager;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MainMenuViewTest {
    @Test
    public void mainMenuViewTest(){
        StateManager stateManager = Mockito.mock(StateManager.class);
        GUILanterna guiLanterna = Mockito.mock(GUILanterna.class);

        MainMenuView mainMenuView = new MainMenuView(stateManager,guiLanterna);

        mainMenuView.build();
        Mockito.verify(guiLanterna, Mockito.times(2)).addWindowToStack(Mockito.any());

        mainMenuView.draw();
    }
}
