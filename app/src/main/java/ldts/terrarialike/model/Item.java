package ldts.terrarialike.model;

import ldts.terrarialike.controller.ItemInteraction;

public class Item {
    private char representation;
    private String name;
    private ItemInteraction interaction;

    public Item(char representation, String name, ItemInteraction itemInteraction) {
        this.representation = representation;
        this.name = name;
        this.interaction = itemInteraction;
    }

    public char getRepresentation() {
        return representation;
    }

    public String getName() {
        return name;
    }

    public ItemInteraction getInteraction() {
        return interaction;
    }



}
