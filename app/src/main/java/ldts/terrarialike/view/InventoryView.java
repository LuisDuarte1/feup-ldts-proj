package ldts.terrarialike.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import ldts.terrarialike.model.Inventory;

public class InventoryView implements ElementView{

    private Inventory inventory;
    final private int width = 3;
    final private int height = 3;


    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#808080"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        int column = 1;
        int row = 1;
        for( int i = 0; i < inventory.getSize(); i++) {

            TextGraphics textGraphics = graphics.newTextGraphics(new TerminalPosition(column, row), new TerminalSize(height, width));
            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#8C2D19"));
            textGraphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
            textGraphics.drawRectangle(new TerminalPosition(column, row), new TerminalSize(height, width), ' ');
            textGraphics.setCharacter(column + 1, row + 1, inventory.getSelectedItem().getRepresentation());

            column += 4;
            if(column  == 25) {
                column = 1;
                row += 4;
            }
        }
    }
}
