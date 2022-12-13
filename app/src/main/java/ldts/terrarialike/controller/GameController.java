package ldts.terrarialike.controller;

import ldts.terrarialike.GUI.GUILanterna;
import ldts.terrarialike.controller.generators.EnemyGeneratorFactory;
import ldts.terrarialike.controller.generators.WorldGenerator;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.Enemy;
import ldts.terrarialike.model.Position;
import ldts.terrarialike.model.World;
import ldts.terrarialike.statemanager.StateManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameController extends AbstractStateController{

    private WorldGenerator worldGenerator;
    private PlayerController playerController;
    private World world;

    private HashMap<AIController,AIController> aiControllerHashMap;

    private InputHandler inputHandler;

    private GameEventManager gameEventManager;

    private EnemyGeneratorFactory enemyGeneratorFactory;

    public GameController(StateManager stateManager, World world, GUILanterna gui) {
        super(stateManager);
        this.world = world;
        this.gameEventManager = new GameEventManager();
        this.inputHandler = new InputHandler(gui,stateManager,world.getPlayer());
        this.worldGenerator = new WorldGenerator(world.getSeed());
        this.playerController = new PlayerController(world.getPlayer());
        this.enemyGeneratorFactory = new EnemyGeneratorFactory(world);
        this.aiControllerHashMap = new HashMap<>();
        for(int i = -30; i <= 30; i++){
            world.tryAddChunk(worldGenerator.generateChunk(i));
        }
        Integer yPos = this.world.findMaxHeightOfXPos(0);
        try {
            this.world.getPlayer().setPosition(new Position(0, yPos+1));
        } catch (InvalidPositionException e) {
            e.printStackTrace();
            //if this happens we should crash the game because the generation shouldn't let this happen
            System.exit(1);
        }
        enemyGeneratorFactory.generateInitialEnemies();



    }



    @Override
    public void tick() {
        gameEventManager.addMultipleGameEvents(inputHandler.handleInput());
        gameEventManager.executeAllEvents(world);
        playerController.tick(world);
        List<Enemy> enemyToRemoveList = new ArrayList<>();
        for(Enemy e : world.getEnemiesList()){
            AIController aiController = new AIController(e);
            AIController aiControllerHash = aiControllerHashMap.get(aiController);
            if(e.getHp() == 0) {
                aiControllerHashMap.remove(aiControllerHash);
                enemyToRemoveList.add(e);
            }

            if(aiControllerHash == null){
                aiControllerHashMap.put(aiController,aiController);
                aiControllerHash = aiController;
            }
            aiController.tick(world);
        }
        if(world.getPlayer().getHp() == 0){
            System.out.println("Player died :( - exiting game...");
        }
        world.getEnemiesList().removeAll(enemyToRemoveList);

    }
}
