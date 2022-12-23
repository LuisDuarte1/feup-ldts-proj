package ldts.terrarialike.controller.generators;

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

    private BlockFactory blockFactory;

    private TreeFactory treeFactory;

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

    public WorldGenerator(SimpleNoise1D noise, BlockFactory blockFactory, TreeFactory treeFactory) {
        this.noise = noise;
        this.blockFactory = blockFactory;
        this.treeFactory = treeFactory;
    }


    public WorldGenerator(long worldSeed){
        this.noise = new SimpleNoise1D(worldSeed);
        this.blockFactory = new BlockFactory(worldSeed);
        this.treeFactory = new TreeFactory((int) worldSeed);
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

        for(int i = 0; i < Chunk.CHUNK_SIZE; i++){
            for(int e = Position.Y_MIN; e <= finalHeights.get(i); e++){
                try {
                    Block newBlock = null;
                    int baseXPos = chunk_id*Chunk.CHUNK_SIZE;
                    if(chunk_id != 0){
                        newBlock = blockFactory.generateBlock(new Position(baseXPos + i, e), finalHeights.get(i));
                    } else {
                        newBlock = blockFactory.generateBlock(new Position(Chunk.CHUNK_SIZE - 1 - i, e), finalHeights.get(i));
                    }
                    result.addBlock(newBlock);
                } catch (InvalidColorStringException | InvalidPositionException e1) {
                    //FIXME do this correctly
                    System.out.println(e1.getMessage());
                }

            }
        }
        List<Pair<Integer, Integer>> pairList = new ArrayList<>();
        for(int i = 0; i < Chunk.CHUNK_SIZE; i++){
            int baseXPos = chunk_id * Chunk.CHUNK_SIZE;
            Pair<Integer, Integer> integerPair = new Pair<>(0,0);
            if(chunk_id != 0){
                integerPair.first = baseXPos + i;
            } else {
                integerPair.first = Chunk.CHUNK_SIZE - 1 - i;
            }
            integerPair.second = finalHeights.get(i);
            pairList.add(integerPair);
        }
        List<Block> treeBlocks = treeFactory.generateTrees(pairList);
        for(Block tBlock : treeBlocks){
            try {
                result.addBlock(tBlock);
            } catch (InvalidPositionException e) {
                System.err.println(e.getMessage());

            }
        }


        
        return result;
    }

    
}
