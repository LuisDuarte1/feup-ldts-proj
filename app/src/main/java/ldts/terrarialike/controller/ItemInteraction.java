package ldts.terrarialike.controller;

import ldts.terrarialike.model.InteractionType;
import ldts.terrarialike.model.Item;
import ldts.terrarialike.model.World;

public interface ItemInteraction {
    void execute(World one, InteractionType interactionType, Item item);
}
