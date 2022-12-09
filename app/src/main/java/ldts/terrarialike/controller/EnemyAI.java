package ldts.terrarialike.controller;

import ldts.terrarialike.model.Enemy;
import ldts.terrarialike.model.World;

public interface EnemyAI {
    void tick(Enemy thisEnemy, World world);
}
