package ldts.terrarialike.controller;

import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.Block;
import ldts.terrarialike.model.Player;
import ldts.terrarialike.model.Position;
import ldts.terrarialike.model.World;

public class PlayerController extends EntityController {


    private Integer startFallingHeight;

    
    
    
    public PlayerController(Player player) {
        super(player);
        this.startFallingHeight = -1;
    }
    

    public void tick(World world){
        applyGravity(world);


    }
    
}
