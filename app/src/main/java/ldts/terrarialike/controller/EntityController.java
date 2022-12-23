package ldts.terrarialike.controller;

import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.Block;
import ldts.terrarialike.model.Entity;
import ldts.terrarialike.model.Position;
import ldts.terrarialike.model.World;
import ldts.terrarialike.utils.WorldUtils;

import java.util.Objects;


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


    protected void applyGravity(World world, WorldUtils worldUtils){
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

        Block blockBelow = worldUtils.getBlock(blockBelowPosition, world);
        if(blockBelow == null){
            entity.setPosition(blockBelowPosition);
            startFallingHeight += 1;
            entity.setFlying(true);
        } else{
            damagePlayerDueGravity();
            entity.setFlying(false);
        }
    }


    abstract void tick(World world, WorldUtils worldUtils);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityController that = (EntityController) o;
        return Objects.equals(entity, that.entity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entity);
    }
}
