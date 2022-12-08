package ldts.terrarialike.controller;

import ldts.terrarialike.exceptions.InvalidColorStringException;
import ldts.terrarialike.model.Block;
import ldts.terrarialike.model.Position;

import java.util.Random;

public class BlockFactory {

    private Random random;

    public BlockFactory(long seed){
        this.random = new Random(seed);
    }

    public Block generateBlock(Position desiredPos, int chunkID, int max_height) throws InvalidColorStringException {
        Block returnBlock = null;
        float mega_probability = random.nextFloat();
        if(desiredPos.getY() == max_height){
            returnBlock = new Block(desiredPos,true, "#137804", 'G');

        } //always render 2 blocks of dirt
        else if ( desiredPos.getY() >= max_height-2 && desiredPos.getY() <= max_height-1){
            returnBlock = new Block(desiredPos,true, "#523701", 'D');


        //between 3-5 blocks maybe render dirt
        }else if (desiredPos.getY() >= max_height-5 && desiredPos.getY() <= max_height-3 && mega_probability <= 0.33){
            returnBlock = new Block(desiredPos,true, "#523701", 'D');


        }else{
            //if nothing is triggered generate stone
            returnBlock = new Block(desiredPos,true, "#5e5a5a", 'S');


        }

        return returnBlock;
    }
}
