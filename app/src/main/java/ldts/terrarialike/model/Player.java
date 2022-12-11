package ldts.terrarialike.model;

import ldts.terrarialike.exceptions.InvalidSizeException;

public class Player extends Entity {
    private Inventory inventory;


    private PlayerLogs playerLogs;

    public Player(Position position, int hp) throws InvalidSizeException {
        super(position, hp);
        inventory = new Inventory(30);
        this.playerLogs = new PlayerLogs();
    }

    public PlayerLogs getPlayerLogs() {
        return playerLogs;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
