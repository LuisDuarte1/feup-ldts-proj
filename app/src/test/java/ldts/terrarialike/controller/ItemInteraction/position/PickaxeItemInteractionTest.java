package ldts.terrarialike.controller.ItemInteraction.position;

import ldts.terrarialike.controller.itemInteractions.position.PickaxeItemInteraction;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.exceptions.InvalidSizeException;
import ldts.terrarialike.model.InteractionType;
import ldts.terrarialike.model.Item;
import ldts.terrarialike.model.World;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class PickaxeItemInteractionTest {

    @Test

    public void testExecute() throws InvalidSizeException, InvalidPositionException {

        Item item = Mockito.mock(Item.class);
        World world = new World();

        PickaxeItemInteraction pickaxeItemInteraction = new PickaxeItemInteraction(1);


        Assertions.assertEquals(new ArrayList<>(),pickaxeItemInteraction.execute(world, InteractionType.ATTACK, item));
        Assertions.assertEquals(new ArrayList<>(),pickaxeItemInteraction.execute(world, InteractionType.DEFENSE, item));
        Assertions.assertEquals(new ArrayList<>(),pickaxeItemInteraction.execute(world, InteractionType.USE, item));


    }
}
