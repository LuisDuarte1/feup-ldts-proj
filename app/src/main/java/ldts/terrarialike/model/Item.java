package ldts.terrarialike.model;

import ldts.terrarialike.controller.itemInteractions.ItemInteraction;

import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return representation == item.representation && Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(representation, name);
    }
}
