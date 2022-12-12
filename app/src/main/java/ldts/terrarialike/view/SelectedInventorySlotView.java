package ldts.terrarialike.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import ldts.terrarialike.model.Inventory;
import ldts.terrarialike.model.ItemStack;

public class SelectedInventorySlotView implements  ElementView{


    private Inventory inventory;


    public SelectedInventorySlotView(Inventory inventory) {
        this.inventory = inventory;
    }

    public void draw(TextGraphics graphics) {
        TerminalSize terminalSize = graphics.getSize();
        if(!(terminalSize.getColumns() >= 5 && terminalSize.getRows() >= 5)){
            throw new RuntimeException("TextGraphics hasn't enough size to render selected inventory slot" +
                    " view");
        }
        ItemStack selectedItemStack = inventory.getSelectedItemStack();
        graphics.enableModifiers(SGR.BOLD, SGR.BORDERED);
        graphics.setBackgroundColor(TextColor.ANSI.BLACK);
        graphics.setForegroundColor(TextColor.Factory.fromString("#8C2D19"));
        graphics.setCharacter(0,0, '#');
        graphics.setCharacter(1,0, '#');
        graphics.setCharacter(2,0, '#');
        graphics.setCharacter(1,2, '#');
        graphics.setCharacter(0,1, '#');
        graphics.setCharacter(0,2, '#');
        graphics.setCharacter(2,1, '#');
        graphics.setCharacter(2,2, '#');
        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        if(selectedItemStack != null){
            graphics.setCharacter(1,1, selectedItemStack.getItem().getRepresentation());
            graphics.putString(0,3, String.format("S:%d", selectedItemStack.getQuantity()));
        } else{
            graphics.putString(0,3,String.format("Empty"));
        }
        graphics.disableModifiers(SGR.BOLD, SGR.BORDERED);





    }
}
