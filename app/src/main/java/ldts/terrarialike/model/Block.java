package ldts.terrarialike.model;

import ldts.terrarialike.exceptions.InvalidColorStringException;

import java.util.Objects;

public class Block {
    private Position position;
    private boolean collision;
    private String color;
    private char representation_char;

    private static void checkColorValid(String color) throws InvalidColorStringException{
        if(color.charAt(0) == '#' && color.length() == 7){
            boolean first = true;
            for (byte c : color.getBytes()) {
                if(first){
                    first = false;
                    continue;
                }
                if(!(('a' <= c && c <= 'f') || ('A' <= c && c <= 'F') || ('0' <= c && c <= '9'))){
                    throw new InvalidColorStringException("Color String has a wrong format... it has a non hex format.");
                }
                
            }
            return;
        }
        throw new InvalidColorStringException("Color String has a wrong format... it doesn't start with # or an invalid size.");
    }

    public Block(Position position, String color, char representation_char) throws InvalidColorStringException{
        this.position = position;
        checkColorValid(color);
        this.color = color;
        this.representation_char = representation_char;
        this.collision = true;
    }

    public Block(Position position, boolean collision, String color, char representation_char) throws InvalidColorStringException {
        this.position = position;
        this.collision = collision;
        checkColorValid(color);
        this.color = color;
        this.representation_char = representation_char;
    }
    public Position getPosition() {
        return position;
    }
    public boolean isCollision() {
        return collision;
    }
    
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public char getRepresentation_char() {
        return representation_char;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return position.equals(block.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
