package ldts.terrarialike.controller.events;

import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.controller.MovementType;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.Entity;
import ldts.terrarialike.model.Position;
import ldts.terrarialike.model.World;

import java.util.ArrayList;
import java.util.List;

public class MoveDownEvent extends MoveEvent{

    Entity entity;

    public MoveDownEvent(Entity entity) {
        super(MovementType.DOWN,entity);
    }

    @Override

    public List<GameEvent> execute(World world) {

        List<GameEvent> list  = new ArrayList<>();

        Position newPosition = null;
        try {
            newPosition = new Position(entity.getPosition().getX(), entity.getPosition().getY() - 1);

            if(world.getBlock(newPosition) == null){
                entity.setPosition(newPosition);
            }

            return list;

        } catch (InvalidPositionException e) {
            throw new RuntimeException(e);
        }
    }
}
