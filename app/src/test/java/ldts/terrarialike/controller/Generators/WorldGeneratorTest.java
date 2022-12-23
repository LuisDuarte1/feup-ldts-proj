package ldts.terrarialike.controller.Generators;

import ldts.terrarialike.controller.generators.BlockFactory;
import ldts.terrarialike.controller.generators.TreeFactory;
import ldts.terrarialike.controller.generators.WorldGenerator;
import ldts.terrarialike.exceptions.InvalidColorStringException;
import ldts.terrarialike.model.Block;
import ldts.terrarialike.model.Chunk;
import ldts.terrarialike.utils.Pair;
import ldts.terrarialike.utils.SimpleNoise1D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class WorldGeneratorTest {

    @Test
    public void interpolationTest(){
        double[] pog = WorldGenerator.interpolate(100,100-Chunk.CHUNK_SIZE, Chunk.CHUNK_SIZE);
        for(double i : pog){
            System.out.println(i);
        }

        Assertions.assertEquals(100.0, pog[0]);
        Assertions.assertEquals(99.0, pog[1]);
        Assertions.assertEquals(98.0, pog[2]);
        Assertions.assertEquals(97.0, pog[3]);
        Assertions.assertEquals(96.0, pog[4]);
        Assertions.assertEquals(95.0, pog[5]);
        Assertions.assertEquals(94.0, pog[6]);
        Assertions.assertEquals(93.0, pog[7]);
        Assertions.assertEquals(92.0, pog[8]);
        Assertions.assertEquals(91.0, pog[9]);
        Assertions.assertEquals(90.0, pog[10]);
        Assertions.assertEquals(89.0, pog[11]);
        Assertions.assertEquals(88.0, pog[12]);
        Assertions.assertEquals(87.0, pog[13]);
        Assertions.assertEquals(86.0, pog[14]);
        Assertions.assertEquals(85.0, pog[15]);
        Assertions.assertEquals(84.0, pog[16]);
    }

    @Test
    void worldGenerationTest(){
        SimpleNoise1D simpleNoise1D = Mockito.mock(SimpleNoise1D.class);
        TreeFactory treeFactory = Mockito.mock(TreeFactory.class);
        BlockFactory blockFactory = Mockito.mock(BlockFactory.class);

        Block block = Mockito.mock(Block.class);


        Mockito.when(simpleNoise1D.getChunkHeight(Mockito.any())).thenReturn(new Pair<>(1,100.0f));
        Mockito.when(treeFactory.generateTrees(Mockito.any())).thenReturn(new ArrayList<>());
        try {
            Mockito.when(blockFactory.generateBlock(Mockito.any(), Mockito.anyInt())).thenReturn(block);
        } catch (InvalidColorStringException ignored) {
        }
        WorldGenerator worldGenerator = new WorldGenerator(simpleNoise1D,blockFactory,treeFactory);

        //TODO:
    }
}
