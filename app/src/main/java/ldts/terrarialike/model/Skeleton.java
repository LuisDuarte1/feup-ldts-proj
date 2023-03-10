package ldts.terrarialike.model;

import ldts.terrarialike.controller.FollowPlayerAI;
import ldts.terrarialike.utils.WorldUtils;

public class Skeleton extends Enemy {

    public Skeleton(Position position, Player player) {
        super(position, 100, 30, new FollowPlayerAI(player, 16, new WorldUtils()));
    }
}