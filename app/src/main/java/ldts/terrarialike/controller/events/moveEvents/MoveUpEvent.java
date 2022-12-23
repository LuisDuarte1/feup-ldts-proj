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


public class MoveUpEvent extends MoveEvent {



    public MoveUpEvent(Entity entity) {
        super(entity);
    }

    @Override

    public List<GameEvent> execute(World world, WorldUtils worldUtils) {


        Position newPosition = null;
        try {
            newPosition = new Position(entity.getPosition().getX(), entity.getPosition().getY() + 2);

            if(worldUtils.getBlock(newPosition,world) == null && !entity.isFlying()){
                entity.setPosition(newPosition);
            }


        } catch (InvalidPositionException e) {
        }
        return  new ArrayList<>();
    }

}

