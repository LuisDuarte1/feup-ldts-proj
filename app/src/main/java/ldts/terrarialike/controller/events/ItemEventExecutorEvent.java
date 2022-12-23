package ldts.terrarialike.controller.events;

import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.model.*;
import ldts.terrarialike.utils.WorldUtils;

import java.util.List;

public class ItemEventExecutorEvent implements GameEvent{

    InteractionType interactionType;

    Item item;

    public ItemEventExecutorEvent(InteractionType interactionType, Item item) {

        this.interactionType = interactionType;
        this.item = item;

    }
    @Override
    public List<GameEvent> execute(World world, WorldUtils worldUtils) {
        return item.getInteraction().execute(world, interactionType, item, worldUtils);
    }

//setters and getters

    public InteractionType getInteractionType() {
        return interactionType;
    }

    public Item getItem() {
        return item;
    }


}

