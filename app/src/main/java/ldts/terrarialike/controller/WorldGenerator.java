package ldts.terrarialike.controller;

import java.util.ArrayList;
import java.util.List;

import ldts.terrarialike.exceptions.InvalidColorStringException;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.Block;
import ldts.terrarialike.model.Chunk;
import ldts.terrarialike.model.Position;
import ldts.terrarialike.utils.Pair;
import ldts.terrarialike.utils.SimpleNoise1D;

public class WorldGenerator {

    private SimpleNoise1D noise;

    public static double[] interpolate(double start, double end, int count){

        if(count < 2){
            throw new IllegalArgumentException("Interpolation requires at least 2 points");
        }
        double[] result = new double[count+1];

        for(int i = 0; i <=count;++i){
            result[i] = start + i * (end-start)/count;
        }

        
        return result;

    }
    

    public WorldGenerator(long worldSeed){
        this.noise = new SimpleNoise1D(worldSeed);
    }

    public Chunk generateChunk(int chunk_id){
        Chunk result = new Chunk(chunk_id);
        Pair<Integer,Float> heightPrevious = noise.getChunkHeight(chunk_id-1);
        Pair<Integer,Float> height = noise.getChunkHeight(chunk_id);
        Pair<Integer,Float> heightNext = noise.getChunkHeight(chunk_id+1);
        
        double[] prevHalf = interpolate(heightPrevious.second, height.second, Chunk.CHUNK_SIZE);
        double[] nextHalf = interpolate(height.second, heightNext.second, Chunk.CHUNK_SIZE);

        List<Integer> finalHeights = new ArrayList<>();
        for(int i = Chunk.CHUNK_SIZE/2; i < Chunk.CHUNK_SIZE; i++){
            finalHeights.add((int) prevHalf[i]);
        }

        for(int i = 0; i < Chunk.CHUNK_SIZE/2;i++){
            finalHeights.add((int) nextHalf[i]);

        }

        //TODO: generate diferent types of blocks 
        for(int i = 0; i < Chunk.CHUNK_SIZE; i++){
            for(int e = Position.Y_MIN; e < finalHeights.get(i); e++){
                try {
                    Block newBlock = null;
                    int baseXPos = chunk_id*Chunk.CHUNK_SIZE;

                    if(chunk_id > 0){

                        newBlock = new Block(new Position(baseXPos - i, e), "#FF0000", 'B');
                    } else if(chunk_id == 0){
                        newBlock = new Block(new Position(Chunk.CHUNK_SIZE - i, e), "#FF0000", 'B');
                        
                    } else {
                        newBlock = new Block(new Position(baseXPos + i, e), "#FF0000", 'B');
                    }
                    result.addBlock(newBlock);
                } catch (InvalidColorStringException | InvalidPositionException e1) {
                    //FIXME do this correctly
                }
                

            }
        }
        


        
        return result;
    }

    
}
