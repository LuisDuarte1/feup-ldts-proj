package ldts.terrarialike.controller.events.moveEvents;

import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.controller.events.MoveEvent;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.Entity;
import ldts.terrarialike.model.Position;
import ldts.terrarialike.model.World;
import ldts.terrarialike.utils.WorldUtils;

import java.util.ArrayList;
import java.util.List;


public class MoveDiagonallyLeftEvent  extends MoveEvent {
    public MoveDiagonallyLeftEvent(Entity entity) {
        super(entity);
    }

    @Override
    public List<GameEvent> execute(World world, WorldUtils worldUtils) {
        List<GameEvent> list  = new ArrayList<>();

        Position newPosition = null;
        try {
            newPosition = new Position(entity.getPosition().getX()-1 , entity.getPosition().getY()+1);

            if(worldUtils.getBlock(newPosition, world) == null){
                entity.setPosition(newPosition);
            }

            return list;

        } catch (InvalidPositionException e) {
            return list;
        }
    }
}
