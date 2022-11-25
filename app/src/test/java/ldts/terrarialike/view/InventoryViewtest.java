package ldts.terrarialike.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import ldts.terrarialike.exceptions.InvalidQuantityException;
import ldts.terrarialike.exceptions.InvalidSizeException;
import ldts.terrarialike.exceptions.InventoryFullException;
import ldts.terrarialike.model.Inventory;
import ldts.terrarialike.model.Item;
import ldts.terrarialike.model.ItemStack;
import ldts.terrarialike.model.ItemStackTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InventoryViewtest {

    private InventoryView inventoryView;
    private Inventory inventory;

    private Item item, item2, item3;
    private TextGraphics textGraphics;

    private ArrayList<ItemStack> inventoryList;
    private System system;

    @BeforeEach

    public void setup() {


            textGraphics = Mockito.mock(TextGraphics.class);
            Mockito.when(textGraphics.newTextGraphics(Mockito.any(TerminalPosition.class), Mockito.any(TerminalSize.class))).thenReturn(textGraphics);
            // Mockito for item
            item = Mockito.mock(Item.class);
            Mockito.when(item.getRepresentation()).thenReturn('a');
            Mockito.when(item.getName()).thenReturn("axe");


            item2 = Mockito.mock(Item.class);
            Mockito.when(item2.getRepresentation()).thenReturn('b');
            Mockito.when(item2.getName()).thenReturn("bow");


            item3 = Mockito.mock(Item.class);
            Mockito.when(item3.getRepresentation()).thenReturn('s');
            Mockito.when(item3.getName()).thenReturn("sword");


            //Mockito for ItemStack
            inventory = Mockito.mock(Inventory.class);
            ItemStack itemStack = Mockito.mock(ItemStack.class);
            Mockito.when(itemStack.getItem()).thenReturn(item);
            Mockito.when(itemStack.getQuantity()).thenReturn(10);

            ItemStack itemStack2 = Mockito.mock(ItemStack.class);
            Mockito.when(itemStack2.getItem()).thenReturn(item2);
            Mockito.when(itemStack2.getQuantity()).thenReturn(20);

            ItemStack itemStack3 = Mockito.mock(ItemStack.class);
            Mockito.when(itemStack3.getItem()).thenReturn(item3);
            Mockito.when(itemStack3.getQuantity()).thenReturn(30);

            //Mockito for inventory ItemStack
            inventoryList = new ArrayList<ItemStack>(Arrays.asList(itemStack, itemStack2, itemStack3));
            Mockito.when(inventory.getInventory()).thenReturn(inventoryList);
            Mockito.when(inventory.getSize()).thenReturn(3);

            inventoryView = new InventoryView(inventory);
    }

    @Test

    public void testDraw() {
        inventoryView.draw(textGraphics);
        int height =3;
        int width = 3;
        int column = 1;
        int row = 1;

        Mockito.verify(textGraphics,Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#808080"));
        Mockito.verify(textGraphics,Mockito.times(4)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        Mockito.verify(textGraphics,Mockito.times(3)).setBackgroundColor(TextColor.Factory.fromString("#8C2D19"));
        for(int i = 0; i < inventory.getSize(); i++) {



        Mockito.verify(textGraphics).drawRectangle(new TerminalPosition(column, row), new TerminalSize(height, width), ' ');
        Mockito.verify(textGraphics).setCharacter(column + 1, row + 1, inventory.getInventory().get(i).getItem().getRepresentation());

            column += 4;
        if(column  == 25) {
            column = 1;
            row += 4;
        }}

    }





}
