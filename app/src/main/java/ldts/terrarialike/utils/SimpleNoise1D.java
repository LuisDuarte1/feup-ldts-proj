package ldts.terrarialike.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;


import ldts.terrarialike.model.Position;

public class SimpleNoise1D {

    private static final int MAX_HEIGHT_DIFF = 30;
    
    private static final int POINTS_EACH_DIRECTION = 1000;

    private List<Pair<Integer,Float>> points;

    private Random random;

    private void createInitialPoints(){
        float height = Math.max(random.nextFloat() * Position.Y_MAX, Position.Y_MAX / 3);
        Pair<Integer,Float> zeroPair = new Pair<Integer,Float>(0, height);
        points.add(zeroPair);

        Pair<Integer,Float> prevPair = zeroPair;
        for(int i = 1; i <POINTS_EACH_DIRECTION; i++){
            prevPair = createNextPair(prevPair, i);
        }
        prevPair = zeroPair;
        for(int i = -1; i > -1 * POINTS_EACH_DIRECTION; i--){
            prevPair = createNextPair(prevPair, i);
        }
    }

    private Pair<Integer, Float> createNextPair(Pair<Integer, Float> prevPair, int i) {
        float minHeight = Math.max(prevPair.second-MAX_HEIGHT_DIFF, Position.Y_MIN);
        float maxHeight = Math.min(prevPair.second+MAX_HEIGHT_DIFF,Position.Y_MAX);
    
        float nextHeight = minHeight + random.nextFloat()*(maxHeight-minHeight);
        Pair<Integer,Float> nextPair = new Pair<Integer,Float>(i, nextHeight);
        points.add(nextPair);
        prevPair = nextPair;
        return prevPair;
    }

    public SimpleNoise1D(long seed){
        this.random = new Random(seed);
        this.points = new ArrayList<>();
        //preloading to decrease run time execution
        createInitialPoints();

    }

    public Pair<Integer,Float> getChunkHeight(Integer chunkID){
        for (Pair<Integer,Float> pair : points) {
            if(Objects.equals(pair.first, chunkID)){
                return pair;
            }
            
        }
        return createNewChunkHeight(chunkID);
    }

    private Pair<Integer, Float> createNewChunkHeight(Integer chunkID) {
        Pair<Integer,Float> beforePair = null;
        for(Pair<Integer,Float> iPair : points){
            if(chunkID >= 0){
                if(iPair.first == chunkID-1){
                    beforePair = iPair;
                    break;
                }
            } else{
                if(iPair.first == chunkID+1){
                    beforePair = iPair;
                    break;
                }
            }
        }
        if(beforePair == null){
            if(chunkID >= 0){
                beforePair = getChunkHeight(chunkID-1);
            } else {
                beforePair = getChunkHeight(chunkID+1);
            }
        }
        Pair<Integer,Float> newPair = createNextPair(beforePair, chunkID);
        points.add(newPair);
        return newPair;
    }


}
