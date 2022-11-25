package ldts.terrarialike.model;

import ldts.terrarialike.exceptions.InvalidSizeException;

public class Player extends Entity {
    private Inventory inventory;

    public Player(Position position, int hp) throws InvalidSizeException {
        super(position, hp);
        inventory = new Inventory(0);
    }

    public Inventory getInventory() {
        return inventory;
    }
}
