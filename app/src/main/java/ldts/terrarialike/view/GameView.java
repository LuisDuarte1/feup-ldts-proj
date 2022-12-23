package ldts.terrarialike.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import ldts.terrarialike.GUI.GUILanterna;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.*;
import ldts.terrarialike.view.statsViews.ElementViewManager;
import ldts.terrarialike.view.statsViews.PlayerLogsView;
import ldts.terrarialike.view.statsViews.PlayerStatsView;
import ldts.terrarialike.view.statsViews.SelectedInventorySlotView;

public class GameView implements StateView{
    
    private GUILanterna gui;
    private World world;


    private PlayerView playerView;

    private Camera camera;

    private TextGraphics gameScreenTextGraphics;

    private TextGraphics statsScreenTextGraphics;

    private ElementViewManager elementViewManager;


    public GameView(GUILanterna gui, World world){
        this.gui = gui;
        this.world = world;
        gameScreenTextGraphics = gui.getPercentageOfScreenVertical(0.82, 0, false);
        statsScreenTextGraphics = gui.getPercentageOfScreenVertical(0.82, 0, true);
        this.elementViewManager = new ElementViewManager(statsScreenTextGraphics);

        this.elementViewManager.addElementViewPercentage(new PlayerStatsView(world.getPlayer(), world.getSeed()), 1);
        this.elementViewManager.addElementViewPercentage(new PlayerLogsView(world.getPlayer().getPlayerLogs()), 0.60);
        this.elementViewManager.addElementView(new SelectedInventorySlotView(world.getPlayer().getInventory()),
                new TerminalPosition(30, 0), new TerminalSize(5,5));


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

        elementViewManager.draw();

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
        for(Enemy e: world.getEnemiesList()){
            if(e instanceof Skeleton){
                SkeletonView v = new SkeletonView(camera);
                v.draw(gameScreenTextGraphics, e);
            } else if (e instanceof Zombie) {
                ZombieView v = new ZombieView(camera);
                v.draw(gameScreenTextGraphics, e);
            }
        }

    }
}
