package ldts.terrarialike.view;

import com.googlecode.lanterna.graphics.TextGraphics;
import ldts.terrarialike.GUI.GUILanterna;
import ldts.terrarialike.model.Block;
import ldts.terrarialike.model.BoundlessPosition;
import ldts.terrarialike.model.Chunk;
import ldts.terrarialike.model.World;

public class GameView implements StateView{
    
    private GUILanterna gui;
    private World world;

    //keep some views ready to use
    private PlayerStatsView playerStatsView;

    private Camera camera;


    public GameView(GUILanterna gui, World world){
        this.gui = gui;
        this.world = world;
        this.playerStatsView = new PlayerStatsView(world.getPlayer());
        this.camera = new Camera(world.getPlayer().getPosition(), new BoundlessPosition(gui.getTerminalSize().getColumns(), gui.getTerminalSize().getRows()));

    }

    public void draw(){
        playerStatsView.draw(gui.getPercentageOfScreenVertical(0.82, 0, true));
        for (Chunk chunk : world.getChunks()) {
            if(camera.isChunkVisible(chunk.getChunkID())){
                for (Block block : chunk.getBlocks()) {
                    
                    BlockView blockView = new BlockView(block, camera) ;
                    blockView.draw(gui.getTextGraphics(null, null));
                    
                }
            }
            
        }

    }
}
