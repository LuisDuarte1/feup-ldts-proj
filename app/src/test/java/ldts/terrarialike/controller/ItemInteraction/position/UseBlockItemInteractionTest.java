package ldts.terrarialike.controller.ItemInteraction.position;

import ldts.terrarialike.controller.itemInteractions.position.UseBlockItemInteraction;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.exceptions.InvalidSizeException;
import ldts.terrarialike.model.BlockInfo;
import ldts.terrarialike.model.InteractionType;
import ldts.terrarialike.model.Item;
import ldts.terrarialike.model.World;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class UseBlockItemInteractionTest {

    @Test

    public void testExecute() throws InvalidSizeException, InvalidPositionException {

        Item item = Mockito.mock(Item.class);
        BlockInfo blockInfo = Mockito.mock(BlockInfo.class);
        World world = new World();

        UseBlockItemInteraction useBlockItemInteraction = new UseBlockItemInteraction(blockInfo);

        Assertions.assertEquals(new ArrayList<>(),useBlockItemInteraction.execute(world, InteractionType.ATTACK, item));
        Assertions.assertEquals(new ArrayList<>(),useBlockItemInteraction.execute(world, InteractionType.DEFENSE, item));
        Assertions.assertEquals(new ArrayList<>(),useBlockItemInteraction.execute(world, InteractionType.DESTROY, item));

    }


}
