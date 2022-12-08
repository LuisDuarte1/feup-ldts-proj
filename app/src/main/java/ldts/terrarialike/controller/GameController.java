package ldts.terrarialike.controller;

import ldts.terrarialike.model.World;
import ldts.terrarialike.statemanager.StateManager;

public class GameController extends AbstractStateController{

    private WorldGenerator worldGenerator;
    private World world;

    public GameController(StateManager stateManager,World world) {
        super(stateManager);
        this.world = world;
        this.worldGenerator = new WorldGenerator(world.getSeed());
        for(int i = -10; i <= 10; i++){
            world.tryAddChunk(worldGenerator.generateChunk(i));
        }
        
        
    }

    @Override
    public void tick() {

    }
}
