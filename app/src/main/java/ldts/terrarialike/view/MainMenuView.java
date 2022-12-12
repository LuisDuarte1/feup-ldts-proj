package ldts.terrarialike.view;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Window;
import ldts.terrarialike.GUI.GUILanterna;
import ldts.terrarialike.GUI.MenuWindowBuilder;
import ldts.terrarialike.controller.GameController;
import ldts.terrarialike.exceptions.NotInitializedStateException;
import ldts.terrarialike.model.World;
import ldts.terrarialike.statemanager.State;
import ldts.terrarialike.statemanager.StateManager;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainMenuView implements StateView {
    private GUILanterna gui;

    private Window mainWindow;

    private StateManager stateManager;

    private String world_seed;

    private List<Component> mainWindowComponentBuilder(){
        List<Component> componentList = new ArrayList<>();

        componentList.add(new Button("Create World!", () -> {
            State worldState = new State(World.class, GameView.class, GameController.class);
            if(world_seed != null){
                Integer seedint = Integer.parseInt(world_seed);
                worldState.initializeDataClass(seedint.intValue());
            } else{
                worldState.initializeDataClass();
            }
            worldState.initializeControllerClass(stateManager, worldState.getDataObject(World.class), gui);
            worldState.initializeViewClass(this.gui, worldState.getDataObject(World.class));

            try {
                this.stateManager.addState(worldState);
            } catch (NotInitializedStateException e) {
                System.err.println("World state not initialized for some reason...");
            }
            this.stateManager.selectState(worldState);
            this.gui.removeCurrentWindowsStack();
        }));

        componentList.add(new Button("World settings...", () -> {
            List<Component> worldSettingsComponents = new ArrayList<>();
            worldSettingsComponents.add(new Label("World seed (empty for random): "));
            TextBox seedBox = new TextBox();
            seedBox.setValidationPattern(Pattern.compile("[0-9]*"));
            worldSettingsComponents.add(seedBox);
            worldSettingsComponents.add(new Button("Done...", () -> {
                this.world_seed = seedBox.getText();
                this.gui.removeCurrentWindowFromStack();
            }));

            Window worldSettingsWindow = MenuWindowBuilder.build("TerrariaLike - World Settings", worldSettingsComponents);
            this.gui.addWindowToStack(worldSettingsWindow);
        }));

        return componentList;

    }

    public MainMenuView(GUILanterna gui, StateManager stateManager){
        this.gui = gui;
        this.stateManager = stateManager;
        this.mainWindow = MenuWindowBuilder.build("TerrariaLike - Main Menu", mainWindowComponentBuilder());
        this.gui.addWindowToStack(mainWindow);
    }

    @Override
    public void draw() {

    }
}
