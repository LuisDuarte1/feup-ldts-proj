package ldts.terrarialike.controller;

import ldts.terrarialike.model.World;
import ldts.terrarialike.utils.WorldUtils;

import java.util.List;

public interface GameEvent {

    List<GameEvent> execute(World world, WorldUtils worldUtils);
    
}
