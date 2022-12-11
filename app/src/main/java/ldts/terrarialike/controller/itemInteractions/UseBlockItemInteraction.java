package ldts.terrarialike.controller.itemInteractions;


import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.controller.ItemInteraction;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.Block;
import ldts.terrarialike.model.BlockInfo;
import ldts.terrarialike.model.Chunk;
import ldts.terrarialike.model.InteractionType;
import ldts.terrarialike.model.Item;
import ldts.terrarialike.model.Position;
import ldts.terrarialike.model.World;

import java.util.ArrayList;
import java.util.List;

public class UseBlockItemInteraction implements ItemInteraction{

    private BlockInfo blockInfo;


    private Position desiredPosition;



    public UseBlockItemInteraction(BlockInfo blockInfo) {
        this.blockInfo = blockInfo;
        this.desiredPosition = null;
    }



    public List<GameEvent> execute(World one, InteractionType interactionType, Item item) {
        if(desiredPosition == null){
            System.err.println("BlockItemInteractionError: block has not desiredPosition... skipping event.");
            return new ArrayList<>();
        }
        if(interactionType == InteractionType.USE && one.getBlock(desiredPosition) == null){
            Integer chunkID = desiredPosition.getX() % Chunk.CHUNK_SIZE;
            Chunk desiredChunk = null;
            for (Chunk chunk : one.getChunks()) {
                if(chunk.getChunkID() == chunkID){
                    desiredChunk = chunk;
                    break;
                }
            }
            if(desiredChunk == null) return null;
            try {
                desiredChunk.addBlock(new Block(desiredPosition, blockInfo));
            } catch (InvalidPositionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return new ArrayList<>();
            }
        }
        return new ArrayList<>();
    }


    public void setDesiredPosition(Position desiredPosition) {
        this.desiredPosition = desiredPosition;
    }
    
}
