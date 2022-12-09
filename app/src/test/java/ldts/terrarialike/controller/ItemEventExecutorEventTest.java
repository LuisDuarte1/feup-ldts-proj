package ldts.terrarialike.controller;

import ldts.terrarialike.controller.events.ItemEventExecutorEvent;
import ldts.terrarialike.model.InteractionType;
import ldts.terrarialike.model.Item;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ItemEventExecutorEventTest {

    InteractionType interactionType;
    Item item;

    @Test

    public void testExecute() {
        interactionType = Mockito.mock(InteractionType.class);
        item = Mockito.mock(Item.class);
        ItemEventExecutorEvent itemEventExecutorEvent = new ItemEventExecutorEvent(interactionType, item);
        itemEventExecutorEvent.execute(null);
        Mockito.verify(item, Mockito.times(1)).getInteraction();
    }

}
