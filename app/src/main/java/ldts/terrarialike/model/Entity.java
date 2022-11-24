package ldts.terrarialike.model;

public abstract class Entity {
    private Position position;
    private int hp;
    
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
        this.hp = hp;
    }

    public boolean collidesWith(Entity t){
        return t.getPosition() == position;
    }
    
}
