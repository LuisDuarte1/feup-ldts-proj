package ldts.terrarialike.controller.events;

import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.model.World;

import java.util.List;

public class EnemyDiedEvent implements GameEvent{


    @Override
    public List<GameEvent> execute(World world) {
        return null;
    }
}
