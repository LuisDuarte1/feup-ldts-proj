package ldts.terrarialike.model;

import ldts.terrarialike.controller.EnemyAI;

public abstract class  Enemy extends Entity{
    private int damage;

    private EnemyAI ai;

    public Enemy(Position position, int hp, int damage, EnemyAI ai) {
        super(position, hp);
        this.damage = damage;
        this.ai = ai;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public EnemyAI getAi() {
        return ai;
    }

    public void setAi(EnemyAI ai) {
        this.ai = ai;
    }


}