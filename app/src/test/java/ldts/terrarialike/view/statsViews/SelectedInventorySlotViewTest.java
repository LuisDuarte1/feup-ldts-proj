package ldts.terrarialike.view.statsViews;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import ldts.terrarialike.model.Inventory;
import ldts.terrarialike.model.Item;
import ldts.terrarialike.model.ItemStack;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.fail;

public class SelectedInventorySlotViewTest {

    @Test
    void drawEmptyTest(){
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        Mockito.when(textGraphics.getSize()).thenReturn(new TerminalSize(5,5));

        Inventory inventory = Mockito.mock(Inventory.class);
        Mockito.when(inventory.getSelectedItemStack()).thenReturn(null);

        SelectedInventorySlotView selectedInventorySlotView = new SelectedInventorySlotView(inventory);
        selectedInventorySlotView.draw(textGraphics);
        Mockito.verify(textGraphics, Mockito.times(1)).enableModifiers(SGR.BOLD, SGR.BORDERED);
        Mockito.verify(textGraphics, Mockito.times(1)).setBackgroundColor(TextColor.ANSI.BLACK);
        Mockito.verify(textGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#8C2D19"));
        Mockito.verify(textGraphics, Mockito.times(1)).setCharacter(0,0, '#');
        Mockito.verify(textGraphics, Mockito.times(1)).setCharacter(1,0, '#');
        Mockito.verify(textGraphics, Mockito.times(1)).setCharacter(2,0, '#');
        Mockito.verify(textGraphics, Mockito.times(1)).setCharacter(1,2, '#');
        Mockito.verify(textGraphics, Mockito.times(1)).setCharacter(0,1, '#');
        Mockito.verify(textGraphics, Mockito.times(1)).setCharacter(0,2, '#');
        Mockito.verify(textGraphics, Mockito.times(1)).setCharacter(2,1, '#');
        Mockito.verify(textGraphics, Mockito.times(1)).setCharacter(2,2, '#');
        Mockito.verify(textGraphics, Mockito.times(1)).setForegroundColor(TextColor.ANSI.WHITE);
        Mockito.verify(textGraphics, Mockito.times(1)).putString(0,3, "Empty");
        Mockito.verify(textGraphics, Mockito.times(1)).disableModifiers(SGR.BOLD, SGR.BORDERED);

    }

    @Test
    void drawItemTest(){
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        Mockito.when(textGraphics.getSize()).thenReturn(new TerminalSize(5,5));

        Inventory inventory = Mockito.mock(Inventory.class);
        Item item = Mockito.mock(Item.class);
        Mockito.when(item.getRepresentation()).thenReturn('L');

        ItemStack itemStack = Mockito.mock(ItemStack.class);
        Mockito.when(itemStack.getItem()).thenReturn(item);
        Mockito.when(itemStack.getQuantity()).thenReturn(33);

        Mockito.when(inventory.getSelectedItemStack()).thenReturn(itemStack);

        SelectedInventorySlotView selectedInventorySlotView = new SelectedInventorySlotView(inventory);
        selectedInventorySlotView.draw(textGraphics);
        Mockito.verify(textGraphics, Mockito.times(1)).enableModifiers(SGR.BOLD, SGR.BORDERED);
        Mockito.verify(textGraphics, Mockito.times(1)).setBackgroundColor(TextColor.ANSI.BLACK);
        Mockito.verify(textGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#8C2D19"));
        Mockito.verify(textGraphics, Mockito.times(1)).setCharacter(0,0, '#');
        Mockito.verify(textGraphics, Mockito.times(1)).setCharacter(1,0, '#');
        Mockito.verify(textGraphics, Mockito.times(1)).setCharacter(2,0, '#');
        Mockito.verify(textGraphics, Mockito.times(1)).setCharacter(1,2, '#');
        Mockito.verify(textGraphics, Mockito.times(1)).setCharacter(0,1, '#');
        Mockito.verify(textGraphics, Mockito.times(1)).setCharacter(0,2, '#');
        Mockito.verify(textGraphics, Mockito.times(1)).setCharacter(2,1, '#');
        Mockito.verify(textGraphics, Mockito.times(1)).setCharacter(2,2, '#');
        Mockito.verify(textGraphics, Mockito.times(1)).setForegroundColor(TextColor.ANSI.WHITE);
        Mockito.verify(textGraphics, Mockito.times(1)).setCharacter(1,1, 'L');
        Mockito.verify(textGraphics, Mockito.times(1)).putString(0,3, String.format("S:%d", 33));
        Mockito.verify(textGraphics, Mockito.times(1)).disableModifiers(SGR.BOLD, SGR.BORDERED);
    }

    @Test
    void notEnoughTextGraphicsSize(){
        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        Mockito.when(textGraphics.getSize()).thenReturn(new TerminalSize(2,1));

        Inventory inventory = Mockito.mock(Inventory.class);

        SelectedInventorySlotView selectedInventorySlotView = new SelectedInventorySlotView(inventory);
        try{
            selectedInventorySlotView.draw(textGraphics);
            fail("RuntimeException not thrown");
        } catch (RuntimeException ignored){

        }
    }

}
