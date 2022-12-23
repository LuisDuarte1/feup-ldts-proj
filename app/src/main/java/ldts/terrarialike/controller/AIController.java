package ldts.terrarialike.controller;

import ldts.terrarialike.model.Enemy;
import ldts.terrarialike.model.Entity;
import ldts.terrarialike.model.World;
import ldts.terrarialike.utils.WorldUtils;

public class AIController extends  EntityController{

    public AIController(Entity entity) {
        super(entity);
        if(!(entity instanceof Enemy)){
            throw new RuntimeException("Attempted to create an AIController that isn't a Enemy");
        }
    }

    @Override
    void tick(World world, WorldUtils worldUtils) {
        applyGravity(world, worldUtils);
        Enemy enemy = (Enemy) entity;
        enemy.getAi().tick(enemy, world);

    }
}
