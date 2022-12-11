package ldts.terrarialike.controller;

import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.Block;
import ldts.terrarialike.model.Entity;
import ldts.terrarialike.model.Position;
import ldts.terrarialike.model.World;

public abstract class EntityController {

    private Integer startFallingHeight;

    protected Entity entity;

    public EntityController(Entity entity){
        this.entity = entity;
        this.startFallingHeight = -1;
    }
    private Integer calculateGravityDamage(Integer fallenHeight){
        if(fallenHeight <= 3) return 0;
        return fallenHeight*4;

    }

    private void damagePlayerDueGravity(){
        entity.setHp(entity.getHp() - calculateGravityDamage(startFallingHeight));
        startFallingHeight = -1;
    }


    protected void applyGravity(World world){
        Position blockBelowPosition = null;
        try {
            blockBelowPosition = new Position(entity.getPosition().getX(), entity.getPosition().getY()-1);
        } catch (InvalidPositionException e) {
            //if this ever happens, we assume that it isn't valling and we damage the player if
            //necessary
            if(startFallingHeight > 0){
                damagePlayerDueGravity();

            }
            return;
        }

        Block blockBelow = world.getBlock(blockBelowPosition);
        if(blockBelow == null){
            entity.setPosition(blockBelowPosition);
            startFallingHeight += 1;
        } else{
            damagePlayerDueGravity();
        }
    }


    abstract void tick(World world);

}
