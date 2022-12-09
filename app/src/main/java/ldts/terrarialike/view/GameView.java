package ldts.terrarialike.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import ldts.terrarialike.GUI.GUILanterna;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.Block;
import ldts.terrarialike.model.BoundlessPosition;
import ldts.terrarialike.model.Chunk;
import ldts.terrarialike.model.World;

public class GameView implements StateView{
    
    private GUILanterna gui;
    private World world;

    //keep some views ready to use
    private PlayerStatsView playerStatsView;
    private PlayerView playerView;

    private Camera camera;

    private TextGraphics gameScreenTextGraphics;

    private TextGraphics statsScreenTextGraphics;



    public GameView(GUILanterna gui, World world){
        this.gui = gui;
        this.world = world;
        this.playerStatsView = new PlayerStatsView(world.getPlayer());
        gameScreenTextGraphics = gui.getPercentageOfScreenVertical(0.82, 0, false);
        statsScreenTextGraphics = gui.getPercentageOfScreenVertical(0.82, 0, true);
        this.camera = new Camera(world.getPlayer().getPosition(), new BoundlessPosition(gameScreenTextGraphics.getSize().getColumns(),
                gameScreenTextGraphics.getSize().getRows()));
        this.playerView = new PlayerView(camera);

    }

    public void draw(){
        try {
            camera.setNewPositionRelativeToPosition(world.getPlayer().getPosition());
        } catch (InvalidPositionException e) {
            throw new RuntimeException(e);
        }
        playerStatsView.draw(statsScreenTextGraphics);
        gameScreenTextGraphics.setBackgroundColor(TextColor.Factory.fromString("#07c0ed"));
        gameScreenTextGraphics.fill(' ');
        for (Chunk chunk : world.getChunks()) {
            if(camera.isChunkVisible(chunk.getChunkID())){
                for (Block block : chunk.getBlocks()) {
                    BlockView blockView = new BlockView(block, camera);
                    blockView.draw(gameScreenTextGraphics);
                    
                }
            }   
        }
        gameScreenTextGraphics.setBackgroundColor(TextColor.Factory.fromString("#07c0ed"));
        playerView.draw(gameScreenTextGraphics, world.getPlayer());

    }
}
