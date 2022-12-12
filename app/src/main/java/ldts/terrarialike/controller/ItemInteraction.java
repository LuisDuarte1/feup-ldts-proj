package ldts.terrarialike.controller;

import ldts.terrarialike.model.InteractionType;
import ldts.terrarialike.model.Item;
import ldts.terrarialike.model.Position;
import ldts.terrarialike.model.World;

import java.util.List;

public abstract class ItemInteraction {
    protected Position desiredPosition;
    protected Boolean direction;

    public boolean isDirection() {
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public Position getDesiredPosition() {
        return desiredPosition;
    }

    public void setDesiredPosition(Position desiredPosition) {
        this.desiredPosition = desiredPosition;
    }

    public abstract  List<GameEvent> execute(World one, InteractionType interactionType, Item item);
}
