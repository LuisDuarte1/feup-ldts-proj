package ldts.terrarialike.controller;

import ldts.terrarialike.model.Player;
import ldts.terrarialike.model.World;
import ldts.terrarialike.utils.WorldUtils;

public class PlayerController extends EntityController {


    private Integer startFallingHeight;

    
    
    
    public PlayerController(Player player) {
        super(player);
        this.startFallingHeight = -1;
    }
    

    public void tick(World world, WorldUtils worldUtils){
        int old_hp = entity.getHp();
        applyGravity(world, worldUtils);
        int new_hp = entity.getHp();
        if(new_hp < old_hp){
            Player player = (Player) entity;
            player.getPlayerLogs().addLogString(String.format("Player took %d fall damage", old_hp-new_hp));
        }


    }
    
}
