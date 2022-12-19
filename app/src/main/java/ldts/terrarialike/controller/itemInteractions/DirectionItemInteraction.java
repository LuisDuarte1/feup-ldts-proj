package ldts.terrarialike.controller.itemInteractions;

public abstract class DirectionItemInteraction implements ItemInteraction{
    protected Boolean direction;

    public boolean isDirection() {
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

}
