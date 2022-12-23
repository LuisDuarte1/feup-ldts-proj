package ldts.terrarialike.model;

import ldts.terrarialike.controller.FollowPlayerAI;
import ldts.terrarialike.utils.WorldUtils;

public class Zombie extends Enemy {


    public Zombie(Position position, Player player) {
        super(position, 100,10, new FollowPlayerAI(player, 25, new WorldUtils()));
    }


}
