package ldts.terrarialike.controller.itemInteractions.position;


import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.controller.itemInteractions.PositionItemInteraction;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.exceptions.InvalidQuantityException;
import ldts.terrarialike.exceptions.ItemNotFoundException;
import ldts.terrarialike.model.Block;
import ldts.terrarialike.model.BlockInfo;
import ldts.terrarialike.model.Chunk;
import ldts.terrarialike.model.InteractionType;
import ldts.terrarialike.model.Item;
import ldts.terrarialike.model.World;

import java.util.ArrayList;
import java.util.List;

import static ldts.terrarialike.utils.WorldUtils.getBlock;
import static ldts.terrarialike.utils.WorldUtils.getChunkID;

public class UseBlockItemInteraction extends PositionItemInteraction {

    private BlockInfo blockInfo;



    public UseBlockItemInteraction(BlockInfo blockInfo) {
        this.blockInfo = blockInfo;
    }



    public List<GameEvent> execute(World one, InteractionType interactionType, Item item) {
        if(desiredPosition == null){
            System.err.println("BlockItemInteractionError: block has not desiredPosition... skipping event.");
            return new ArrayList<>();
        }
        if(interactionType == InteractionType.USE && getBlock(desiredPosition, one) == null){
            int chunkID = getChunkID(desiredPosition.getX());
            Chunk desiredChunk = null;
            for (Chunk chunk : one.getChunks()) {
                if(chunk.getChunkID() == chunkID){
                    desiredChunk = chunk;
                    break;
                }
            }
            if(desiredChunk == null) return new ArrayList<>();
            try {
                desiredChunk.addBlock(new Block(desiredPosition, blockInfo));
            } catch (InvalidPositionException e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
            try {
                one.getPlayer().getInventory().remove(item, 1);
            } catch (InvalidQuantityException e) {
                throw new RuntimeException(e);
            } catch (ItemNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return new ArrayList<>();
    }

}
