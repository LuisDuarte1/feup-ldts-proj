package ldts.terrarialike.controller;

import ldts.terrarialike.model.InteractionType;
import ldts.terrarialike.model.Item;
import ldts.terrarialike.model.World;

import java.util.List;

public interface ItemInteraction {
    List<GameEvent> execute(World one, InteractionType interactionType, Item item);
}
