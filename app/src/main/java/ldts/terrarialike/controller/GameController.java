package ldts.terrarialike.controller;

import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.Block;
import ldts.terrarialike.model.Chunk;
import ldts.terrarialike.model.Position;
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
        //FIXME: make this boiler plate part of world?
        int maxZeroPosition = -1;
        Chunk zero_chunk = null;
        for(Chunk c: world.getChunks()){
            if(c.getChunkID() == 0){
                zero_chunk = c;
            }
        }
        for (Block a :
                zero_chunk.getBlocks()) {
            if(a.getPosition().getX() == 0 && a.getPosition().getY() > maxZeroPosition) {
                maxZeroPosition = a.getPosition().getY();
            }
        }
        try {
            this.world.getPlayer().setPosition(new Position(0,maxZeroPosition));
        } catch (InvalidPositionException e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    public void tick() {

    }
}
