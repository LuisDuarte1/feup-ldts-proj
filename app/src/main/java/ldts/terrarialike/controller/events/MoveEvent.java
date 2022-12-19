package ldts.terrarialike.controller.events;

import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.model.*;

public abstract class MoveEvent implements GameEvent{

    protected Entity entity;



    public MoveEvent(Entity entity) {


        this.entity = entity;

    }


    }

