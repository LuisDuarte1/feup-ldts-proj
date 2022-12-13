package ldts.terrarialike.view.menus;

import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.Window;
import ldts.terrarialike.GUI.GUILanterna;
import ldts.terrarialike.GUI.MenuWindowBuilder;
import ldts.terrarialike.statemanager.StateManager;
import ldts.terrarialike.view.StateView;

import java.util.List;

public abstract class AbstractMenuView implements StateView {



    protected StateManager stateManager;
    protected GUILanterna gui;

    private String title;

    protected Window mainWindow;

    protected abstract List<Component> mainWindowComponentBuilder();

    public AbstractMenuView(StateManager stateManager, GUILanterna gui, String title) {
        this.stateManager = stateManager;
        this.gui = gui;
        this.title = title;
    }

    protected void build(){
        this.mainWindow = MenuWindowBuilder.build(title, mainWindowComponentBuilder());
        gui.addWindowToStack(mainWindow);
    }

}
