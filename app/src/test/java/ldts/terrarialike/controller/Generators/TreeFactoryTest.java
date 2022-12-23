package ldts.terrarialike.controller.Generators;

import ldts.terrarialike.controller.generators.TreeFactory;
import ldts.terrarialike.model.Block;
import ldts.terrarialike.utils.Pair;
import net.bytebuddy.build.ToStringPlugin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TreeFactoryTest {

    @Test

    public void generateTreeTest() {
        TreeFactory treeFactory = new TreeFactory(0);
        List<Pair<Integer, Integer>> maxHeights = new ArrayList<>();

        for(int i = 0; i < 16; i++) maxHeights.add(new Pair<>(i, 0));
        List<Block> blocks = treeFactory.generateTrees(maxHeights);

        Assertions.assertEquals(0, blocks.size());

        blocks = treeFactory.generateTrees(maxHeights);
        Assertions.assertEquals(0, blocks.size());

        blocks = treeFactory.generateTrees(maxHeights);
        Assertions.assertEquals(21, blocks.size());

    }
}
