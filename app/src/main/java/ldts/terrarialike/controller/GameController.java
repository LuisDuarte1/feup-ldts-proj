package ldts.terrarialike.controller;

import ldts.terrarialike.GUI.GUILanterna;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.Position;
import ldts.terrarialike.model.World;
import ldts.terrarialike.statemanager.StateManager;

public class GameController extends AbstractStateController{

    private WorldGenerator worldGenerator;
    private PlayerController playerController;
    private World world;

    private InputHandler inputHandler;

    private GameEventManager gameEventManager;


    public GameController(StateManager stateManager, World world, GUILanterna gui) {
        super(stateManager);
        this.world = world;
        this.gameEventManager = new GameEventManager();
        this.inputHandler = new InputHandler(gui,stateManager,world.getPlayer());
        this.worldGenerator = new WorldGenerator(world.getSeed());
        this.playerController = new PlayerController(world.getPlayer());
        for(int i = -30; i <= 30; i++){
            world.tryAddChunk(worldGenerator.generateChunk(i));
        }
        Integer yPos = this.world.findMaxHeightOfXPos(0);
        try {
            this.world.getPlayer().setPosition(new Position(0, yPos+20));
        } catch (InvalidPositionException e) {
            e.printStackTrace();
            //if this happens we should crash the game because the generation shouldn't let this happen
            System.exit(1);
        }



    }



    @Override
    public void tick() {
        gameEventManager.addMultipleGameEvents(inputHandler.handleInput());
        gameEventManager.executeAllEvents(world);
        playerController.tick(world);

    }
}
