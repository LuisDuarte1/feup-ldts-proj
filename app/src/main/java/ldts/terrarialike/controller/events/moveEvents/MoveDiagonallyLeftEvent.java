package ldts.terrarialike.controller.events.moveEvents;

import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.controller.events.MoveEvent;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.Entity;
import ldts.terrarialike.model.Position;
import ldts.terrarialike.model.World;

import java.util.ArrayList;
import java.util.List;

public class MoveDiagonallyLeftEvent  extends MoveEvent {
    public MoveDiagonallyLeftEvent(Entity entity) {
        super(entity);
    }

    @Override
    public List<GameEvent> execute(World world) {
        List<GameEvent> list  = new ArrayList<>();

        Position newPosition = null;
        try {
            newPosition = new Position(entity.getPosition().getX()-1 , entity.getPosition().getY()+1);

            if(world.getBlock(newPosition) == null){
                entity.setPosition(newPosition);
            }

            return list;

        } catch (InvalidPositionException e) {
            return list;
        }
    }
}