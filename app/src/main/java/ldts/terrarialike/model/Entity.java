package ldts.terrarialike.model;

public abstract class Entity {
    private Position position;
    private int hp;

    private boolean flying;
    
    public Entity(Position position, int hp) {
        this.position = position;
        this.hp = hp;
    }
    
    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        //never let hp get to bellow 0
        this.hp = Math.max(hp, 0);
    }

    public boolean isFlying() {
        return flying;
    }

    public void setFlying(boolean flying) {
        this.flying = flying;
    }

    public boolean collidesWith(Entity t){
        return t.getPosition() == position;
    }
    
}
