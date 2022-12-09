package ldts.terrarialike.controller;

import ldts.terrarialike.model.Enemy;
import ldts.terrarialike.model.Position;

public class Skeleton extends Enemy {

    public Skeleton(Position position) {
        super(position, 100, 15, e -> {
        });
    }
}

