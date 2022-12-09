package ldts.terrarialike.controller;

import ldts.terrarialike.controller.itemInteractions.UseBlockItemInteraction;
import ldts.terrarialike.exceptions.InvalidColorStringException;
import ldts.terrarialike.model.*;

import java.util.Random;

public class BlockFactory {

    private static BlockInfo grassInfo;
    private static BlockInfo dirtInfo;
    private static BlockInfo stoneInfo;

    private Random random;


    public BlockFactory(long seed) {
        this.random = new Random(seed);
        try {
            grassInfo = new BlockInfo(1, BlockType.DIRT,
                    "#137804", 'G');
            grassInfo.setToDropItem(generateItemForBlock('G', "Grass block", grassInfo));

            dirtInfo = new BlockInfo(1,
                    BlockType.DIRT, "#523701", 'D');
            dirtInfo.setToDropItem(generateItemForBlock('D', "Dirt block", grassInfo));


            stoneInfo = new BlockInfo(2, BlockType.STONE,
                    "#5e5a5a", 'S');
            stoneInfo.setToDropItem(generateItemForBlock('S', "Stone block", grassInfo));


        } catch (InvalidColorStringException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static Item generateItemForBlock(char representation_char, String object_name, BlockInfo blockInfo){
        return  new Item(representation_char, object_name, new UseBlockItemInteraction(blockInfo));
    }

    public Block generateBlock(Position desiredPos, int chunkID, int max_height) throws InvalidColorStringException {
        Block returnBlock = null;
        float mega_probability = random.nextFloat();
        if(desiredPos.getY() == max_height){
            returnBlock = new Block(desiredPos,true, grassInfo);

        } //always render 2 blocks of dirt
        else if ( desiredPos.getY() >= max_height-2 && desiredPos.getY() <= max_height-1){
            returnBlock = new Block(desiredPos,true, dirtInfo);


        //between 3-5 blocks maybe render dirt
        }else if (desiredPos.getY() >= max_height-5 && desiredPos.getY() <= max_height-3 && mega_probability <= 0.33){
            returnBlock = new Block(desiredPos,true, dirtInfo);


        }else{
            //if nothing is triggered generate stone
            returnBlock = new Block(desiredPos,true, stoneInfo);



        }

        return returnBlock;
    }
}
