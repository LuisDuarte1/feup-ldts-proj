package ldts.terrarialike.controller;

import ldts.terrarialike.controller.events.ItemEventExecutorEvent;
import ldts.terrarialike.controller.itemInteractions.ItemInteraction;
import ldts.terrarialike.model.InteractionType;
import ldts.terrarialike.model.Item;
import ldts.terrarialike.model.World;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ItemEventExecutorEventTest {



    @Test

    public void testExecute() {
        InteractionType interactionType = InteractionType.ATTACK;
        Item item = Mockito.mock(Item.class);
        ItemInteraction itemInteraction = Mockito.mock(ItemInteraction.class);
        Mockito.when(item.getInteraction()).thenReturn(itemInteraction);
        World world  = Mockito.mock(World.class);
        ItemEventExecutorEvent itemEventExecutorEvent = new ItemEventExecutorEvent(interactionType, item);
        itemEventExecutorEvent.execute(world);
        Mockito.verify(item, Mockito.times(1)).getInteraction();
    }

}
