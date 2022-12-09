package ldts.terrarialike.model;

public class Zombie extends Enemy {


    public Zombie(Position position) {
        //FIXME: turn this into a FollowPlayerAI
        super(position, 100,10, e -> {
        });
    }


}
