package ldts.terrarialike.utils;

import ldts.terrarialike.model.*;

public class WorldUtils {


    public static Integer getChunkID(Integer xPos){
        Integer desiredChunkID = Integer.MIN_VALUE;
        if(xPos >= 0 && xPos <= Chunk.CHUNK_SIZE-1){
            desiredChunkID = 0;
        } else if(xPos >= Chunk.CHUNK_SIZE){
            desiredChunkID = xPos / Chunk.CHUNK_SIZE;
        } else if (xPos < 0 && xPos % Chunk.CHUNK_SIZE == 0) {
            //on the negative size the edges of chunks are a bit different -1 to -16 for example is contained in -1
            desiredChunkID = xPos / Chunk.CHUNK_SIZE;
        } else{
            desiredChunkID = (xPos / Chunk.CHUNK_SIZE)-1;
        }
        return desiredChunkID;
    }

    public static Block getBlock(Position blockPosition, World world){
        Integer desiredChunkID = getChunkID(blockPosition.getX());
        Chunk desiredChunk = null;
        //we have to do it this way because we can't guarentee that chunks will always be
        //sequential
        for (Chunk chunk : world.getChunks()) {
            if(chunk.getChunkID() == desiredChunkID){
                desiredChunk = chunk;
            }
        }
        //block doesnt exist because chunk doesnt exist either lmao
        if(desiredChunk == null) return null;
        for (Block block : desiredChunk.getBlocks()) {
            if(block.getPosition().equals(blockPosition)){
                return block;
            }
        }
        //if we get here block doesnt exist
        return null;
    }

    public static Entity getEntity(Position entityPosition, World world) {
        if (world.getPlayer().getPosition().equals(entityPosition)) {
            return world.getPlayer();
        }
        for (Enemy enemy : world.getEnemiesList()) {
            if (enemy.getPosition().equals(entityPosition)) {
                return enemy;
            }
        }
        return null;
    }

    public static Integer findMaxHeightOfXPos(Integer xPos, World world) {
        int maxZeroPosition = -1;
        Integer chunkID = getChunkID(xPos);
        Chunk desiredChunk = null;
        for(Chunk c: world.getChunks()){
            if(c.getChunkID() == chunkID){
                desiredChunk = c;
            }
        }
        for (Block a :
                desiredChunk.getBlocks()) {
            if(a.getPosition().getX() == xPos && a.getPosition().getY() > maxZeroPosition) {
                maxZeroPosition = a.getPosition().getY();
            }
        }
        return maxZeroPosition;
    }
}
