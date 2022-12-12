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

public class UseBlockItemInteraction extends ItemInteraction{

    private BlockInfo blockInfo;



    public UseBlockItemInteraction(BlockInfo blockInfo) {
        this.blockInfo = blockInfo;
    }



    public List<GameEvent> execute(World one, InteractionType interactionType, Item item) {
        if(desiredPosition == null){
            System.err.println("BlockItemInteractionError: block has not desiredPosition... skipping event.");
            return new ArrayList<>();
        }
        if(interactionType == InteractionType.USE && one.getBlock(desiredPosition) == null){
            Integer chunkID = one.getChunkID(desiredPosition.getX());
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
                e.printStackTrace();
                return new ArrayList<>();
            }
        }
        return new ArrayList<>();
    }

}
