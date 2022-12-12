package ldts.terrarialike.controller;

import ldts.terrarialike.exceptions.InvalidColorStringException;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.Block;
import ldts.terrarialike.model.BlockInfo;
import ldts.terrarialike.model.BlockType;
import ldts.terrarialike.model.Position;
import ldts.terrarialike.utils.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ldts.terrarialike.controller.BlockFactory.generateItemForBlock;

public class TreeFactory {

    private static BlockInfo woodInfo;
    private static BlockInfo leafInfo;
    private Random random;


    public TreeFactory(Integer seed){
        random = new Random(seed);
        try {
            woodInfo = new BlockInfo(1, BlockType.WOOD,
                    "#592e0c", 'W');
            woodInfo.setToDropItem(generateItemForBlock('W', "Wood block", woodInfo));

            leafInfo = new BlockInfo(1, BlockType.WOOD,
                    "#36ad03", 'L');

        } catch (InvalidColorStringException e) {
            throw new RuntimeException("This should never happen good luck :)");
        }

    }

    public List<Block> generateTrees(List<Pair<Integer, Integer>> maxHeights){
        List<Block> blocks = new ArrayList<>();
        float megaProbabilityPog = random.nextFloat();
        if(maxHeights.size() < 0){
            throw  new RuntimeException("cope");
        }
        if(megaProbabilityPog <= 0.25){
            Integer index = random.nextInt(0, maxHeights.size());
            Pair<Integer, Integer> maxHeight = maxHeights.get(index);
            Integer treeSize = random.nextInt(3,6);
            Integer leafWidth = random.nextInt(2,4);
            try {
                blocks.add(new Block(new Position(maxHeight.first, maxHeight.second+1), woodInfo));
            } catch (InvalidPositionException e) {
                return new ArrayList<>();
            }
            Integer maxTreeSize = treeSize;
            for(int i = 2; i < treeSize; i++){
                try{
                    blocks.add(new Block(new Position(maxHeight.first, maxHeight.second+i), woodInfo));
                    for(int e = 1; e <= leafWidth; e++){
                        blocks.add(new Block(new Position(maxHeight.first+e, maxHeight.second+i), leafInfo));
                        blocks.add(new Block(new Position(maxHeight.first-e, maxHeight.second+i), leafInfo));
                    }
                } catch (InvalidPositionException e){
                    break;
                }
            }
            try {
                blocks.add(new Block(new Position(maxHeight.first, maxHeight.second+treeSize), leafInfo));
                for(int i = 0; i < leafWidth; i++){
                    blocks.add(new Block(new Position(maxHeight.first+i, maxHeight.second+treeSize), leafInfo));
                    blocks.add(new Block(new Position(maxHeight.first-i, maxHeight.second+treeSize), leafInfo));
                }
            } catch (InvalidPositionException e) {
                return new ArrayList<>();
            }

        }

        return blocks;
    }
}
