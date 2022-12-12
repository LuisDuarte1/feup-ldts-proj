package ldts.terrarialike.model;

import ldts.terrarialike.exceptions.InvalidColorStringException;

public class BlockInfo {
    private int durability;
    private BlockType blockType;
    private String backgroundcolor;
    private String foregroundColor; 
    private char representation_char;

    private Item toDropItem;


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
    
    public BlockInfo(int durability, BlockType blockType, String backgroundcolor, String foregroundColor,
            char representation_char) throws InvalidColorStringException {
        this.durability = durability;
        this.blockType = blockType;
        checkColorValid(foregroundColor);
        checkColorValid(backgroundcolor);
        this.backgroundcolor = backgroundcolor;
        this.foregroundColor = foregroundColor;
        this.representation_char = representation_char;
    }
    
    public BlockInfo(int durability, BlockType blockType, String backgroundcolor, char representation_char) throws InvalidColorStringException {
        this.durability = durability;
        this.blockType = blockType;
        checkColorValid(backgroundcolor);
        this.backgroundcolor = backgroundcolor;
        this.foregroundColor = backgroundcolor;
        this.representation_char = representation_char;
    }
    public int getDurability() {
        return durability;
    }
    public BlockType getBlockType() {
        return blockType;
    }
    public String getBackgroundcolor() {
        return backgroundcolor;
    }
    public String getForegroundColor() {
        return foregroundColor;
    }
    public char getRepresentation_char() {
        return representation_char;
    }

    //this can be null
    public Item getToDropItem() {
        return toDropItem;
    }
    
    public void setBackgroundcolor(String backgroundcolor) {
        this.backgroundcolor = backgroundcolor;
    }
    public void setForegroundColor(String foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public void setToDropItem(Item toDropItem) {
        this.toDropItem = toDropItem;
    }
}
