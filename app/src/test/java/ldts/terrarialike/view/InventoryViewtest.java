package ldts.terrarialike.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
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
            item = Mockito.mock(Item.class);
            Mockito.when(item.getRepresentation()).thenReturn('a');
            Mockito.when(item.getName()).thenReturn("axe");
            system.out.println(item.getRepresentation());
            item2 = Mockito.mock(Item.class);
            Mockito.when(item2.getRepresentation()).thenReturn('b');
            Mockito.when(item2.getName()).thenReturn("bow");
            system.out.println(item2.getRepresentation());
            item3 = Mockito.mock(Item.class);
            Mockito.when(item3.getRepresentation()).thenReturn('s');
            Mockito.when(item3.getName()).thenReturn("sword");
            system.out.println(item3.getRepresentation());

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
            inventoryList = new ArrayList<ItemStack>(Arrays.asList(itemStack, itemStack2, itemStack3));
            Mockito.when(inventory.getInventory()).thenReturn(inventoryList);






            Mockito.when(inventory.getSize()).thenReturn(3);
            Mockito.when(inventory.getInventory()).thenReturn(inventoryList);



            inventoryView = new InventoryView();



    }

    @Test

    public void testDraw() {
        inventoryView.draw(textGraphics);
        int height =3;
        int width = 3;
        int column = 1;
        int row = 1;
        for(int i = 0; i < inventory.getSize(); i++) {

            Mockito.verify(textGraphics).drawRectangle(new TerminalPosition(column, row), new TerminalSize(height, width), ' ');
            Mockito.verify(textGraphics).setCharacter(column + 1, row + 1, inventory.getSelectedItem().getRepresentation());
        }
        Mockito.verify(textGraphics).drawRectangle(new TerminalPosition(1, 1), new TerminalSize(3, 3), ' ');
        Mockito.verify(textGraphics).setCharacter(2, 2, item.getRepresentation());
        Mockito.verify(textGraphics).drawRectangle(new TerminalPosition(5, 1), new TerminalSize(3, 3), ' ');
        Mockito.verify(textGraphics).setCharacter(6, 2, item2.getRepresentation());
        Mockito.verify(textGraphics).drawRectangle(new TerminalPosition(9, 1), new TerminalSize(3, 3), ' ');
        Mockito.verify(textGraphics).setCharacter(10, 2, item3.getRepresentation());
    }





}
