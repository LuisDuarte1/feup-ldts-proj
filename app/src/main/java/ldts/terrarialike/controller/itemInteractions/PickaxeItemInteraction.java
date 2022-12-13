package ldts.terrarialike.controller.itemInteractions;

import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.controller.ItemInteraction;
import ldts.terrarialike.exceptions.BlockNotFoundException;
import ldts.terrarialike.exceptions.InvalidQuantityException;
import ldts.terrarialike.exceptions.InventoryFullException;
import ldts.terrarialike.model.*;

import java.util.ArrayList;
import java.util.List;

public class PickaxeItemInteraction extends ItemInteraction {
    private Integer durability;

    public PickaxeItemInteraction(Integer durability) {
        this.durability = durability;
    }

    @Override
    public List<GameEvent> execute(World one, InteractionType interactionType, Item item) {        if(interactionType == InteractionType.DESTROY){
        if(desiredPosition == null){
            System.err.println("DefaultItemInteraction Error: no desiredPosition set.");
            return new ArrayList<>();
        }
        Integer chunkID = one.getChunkID(desiredPosition.getX());
        Chunk desiredChunk = null;
        for (Chunk chunk : one.getChunks()) {
            if(chunk.getChunkID() == chunkID){
                desiredChunk = chunk;
                break;
            }
        }
        if(desiredChunk == null){
            throw new RuntimeException("DefaultDestroyItemInteraction: Chunk should exist but it doesnt.");
        }

        try {
            Block desiredBlock = one.getBlock(desiredPosition);
            Item droppedItem = desiredBlock.getBlockInfo().getToDropItem();
            if(desiredBlock.getBlockInfo().getDurability() > durability){
                one.getPlayer().getPlayerLogs()
                        .addLogString(String.format("Couldn't break %s because it's too hard...",
                                droppedItem.getName()));
                return new ArrayList<>();
            }
            desiredChunk.removeBlock(desiredPosition);
            if(droppedItem != null){
                one.getPlayer().getInventory().add(droppedItem, 1);
                one.getPlayer().getPlayerLogs()
                        .addLogString(String.format("Added 1 %s to your inventory",
                                droppedItem.getName()));

            }
        } catch (BlockNotFoundException | NullPointerException e) {
            return new ArrayList<>();
        } catch (InvalidQuantityException e) {
            throw new RuntimeException(e);
        } catch (InventoryFullException e) {
            one.getPlayer().getPlayerLogs()
                    .addLogString("Couldn't add block to inventory because inventory is full...");
            return new ArrayList<>();
        }

    }
        return new ArrayList<>();
    }
}