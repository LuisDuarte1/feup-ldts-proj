package ldts.terrarialike.controller.events;

import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.controller.MovementType;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.*;

import java.util.ArrayList;
import java.util.List;

public abstract class MoveEvent implements GameEvent{

    protected Entity entity;

    MovementType movementType;


    public MoveEvent(MovementType movementType, Entity entity) {

        this.movementType = movementType;

        this.entity = entity;

    }


    }

