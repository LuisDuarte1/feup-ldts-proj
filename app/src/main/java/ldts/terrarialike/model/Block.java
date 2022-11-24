package ldts.terrarialike.model;

public class Block {
    private Position position;
    private boolean collision;
    private Color color;
    private char representation_char;

    public Block(Position position, Color color, char representation_char) {
        this.position = position;
        this.color = color;
        this.representation_char = representation_char;
        this.collision = true;
    }

    public Block(Position position, boolean collision, Color color, char representation_char) {
        this.position = position;
        this.collision = collision;
        this.color = color;
        this.representation_char = representation_char;
    }
    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public boolean isCollision() {
        return collision;
    }
    
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public char getRepresentation_char() {
        return representation_char;
    }
    public void setRepresentation_char(char representation_char) {
        this.representation_char = representation_char;
    }

    
}
