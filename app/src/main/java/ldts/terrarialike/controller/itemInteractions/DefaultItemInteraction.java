package ldts.terrarialike.controller.itemInteractions;

import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.controller.ItemInteraction;
import ldts.terrarialike.model.InteractionType;
import ldts.terrarialike.model.Item;
import ldts.terrarialike.model.World;

import java.util.ArrayList;
import java.util.List;

public class DefaultItemInteraction implements ItemInteraction {
    @Override
    public List<GameEvent> execute(World one, InteractionType interactionType, Item item) {
        return new ArrayList<>();
    }
}
