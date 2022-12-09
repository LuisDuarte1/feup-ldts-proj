package ldts.terrarialike.model;

import ldts.terrarialike.controller.EnemyAI;

public class Zombie extends Enemy {


    public Zombie(Position position) {
        //FIXME: turn this into a FollowPlayerAI
        super(position, 100,10, e -> {
        });
    }
}
