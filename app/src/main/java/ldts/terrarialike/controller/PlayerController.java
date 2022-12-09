package ldts.terrarialike.controller;

import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.Block;
import ldts.terrarialike.model.Player;
import ldts.terrarialike.model.Position;
import ldts.terrarialike.model.World;

public class PlayerController {

    //FIXME (luisd): not a good way to do this, maybe we need to refactor entities to account for physics
    
    private Player player;


    private Integer startFallingHeight;
    
    
    
    
    public PlayerController(Player player) {
        this.player = player;
        this.startFallingHeight = -1;
    }
    
    private Integer calculateGravityDamage(Integer fallenHeight){
        if(fallenHeight <= 3) return 0;
        return fallenHeight*4;

    }

    private void damagePlayerDueGravity(){
        player.setHp(player.getHp() - calculateGravityDamage(startFallingHeight));
        startFallingHeight = -1;
    }
    

    private void applyGravity(World world){
        Position blockBelowPosition = null;
        try {
            blockBelowPosition = new Position(player.getPosition().getX(), player.getPosition().getY()-1);
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
                player.setPosition(blockBelowPosition);
                startFallingHeight += 1;
        } else{
            damagePlayerDueGravity();
        }
    }

    public void tick(World world){
        applyGravity(world);


    }
    
}
