package ldts.terrarialike.view;

import ldts.terrarialike.GUI.GUILanterna;
import ldts.terrarialike.model.BoundlessPosition;
import ldts.terrarialike.model.World;
import org.checkerframework.checker.units.qual.C;

public class GameView {
    
    private GUILanterna gui;
    private World world;

    //keep some views ready to use
    private PlayerStatsView playerStatsView;

    private Camera camera;

    public GameView(GUILanterna gui, World world){
        this.gui = gui;
        this.world = world;
        this.playerStatsView = new PlayerStatsView(); //TODO: complete constructor
        this.camera = new Camera(world.getPlayer().getPosition(), new BoundlessPosition(gui.getTerminalSize().getColumns(), gui.getTerminalSize().getRows()));

    }

    public void drawGame(){

    }
}
