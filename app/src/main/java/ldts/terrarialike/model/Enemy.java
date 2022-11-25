package ldts.terrarialike.model;

public class Enemy extends Entity{
    private int damage;

    public Enemy(Position position, int hp, int damage) {
        super(position, hp);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
