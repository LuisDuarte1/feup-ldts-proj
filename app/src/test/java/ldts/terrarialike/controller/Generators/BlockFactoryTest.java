package ldts.terrarialike.controller.Generators;

import ldts.terrarialike.controller.generators.BlockFactory;
import ldts.terrarialike.exceptions.InvalidColorStringException;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.Block;
import ldts.terrarialike.model.BlockInfo;
import ldts.terrarialike.model.Position;
import ldts.terrarialike.model.World;
import ldts.terrarialike.utils.WorldUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class BlockFactoryTest {


    @Test
    public void generateBlockTest() throws InvalidColorStringException, InvalidPositionException {
        BlockFactory blockFactory = new BlockFactory(0);
        List<Block> blockList = new ArrayList<>();
        final int maxHeight = 20;
        for(int i = 0; i < maxHeight; i++){
            blockList.add(blockFactory.generateBlock(new Position(1,i), maxHeight));
        }
        List<String> blockNames = blockList.stream().map((b1)->b1.getBlockInfo().getToDropItem().getName()).toList();
        Assertions.assertEquals(List.of("Stone block","Stone block","Stone block","Stone block","Stone block"
                ,"Stone block","Stone block","Stone block","Stone block","Stone block","Stone block","Stone block"
                ,"Stone block","Stone block","Stone block","Stone block","Stone block","Dirt block"
                ,"Dirt block","Dirt block"), blockNames);


    }
}
