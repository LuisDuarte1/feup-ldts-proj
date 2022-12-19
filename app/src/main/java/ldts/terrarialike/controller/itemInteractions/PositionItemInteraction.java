package ldts.terrarialike.controller.itemInteractions;

import ldts.terrarialike.model.Position;

public abstract class PositionItemInteraction implements ItemInteraction {
    protected Position desiredPosition;
    public Position getDesiredPosition() {
        return desiredPosition;
    }

    public void setDesiredPosition(Position desiredPosition) {
        this.desiredPosition = desiredPosition;
    }
}
