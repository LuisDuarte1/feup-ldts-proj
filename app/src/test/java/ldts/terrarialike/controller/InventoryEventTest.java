package ldts.terrarialike.controller;

import ldts.terrarialike.controller.events.InventoryEvent;
import ldts.terrarialike.exceptions.InvalidIndexException;
import ldts.terrarialike.exceptions.InvalidQuantityException;
import ldts.terrarialike.exceptions.InvalidSizeException;
import ldts.terrarialike.model.Inventory;
import ldts.terrarialike.model.Item;
import ldts.terrarialike.model.ItemStack;
import ldts.terrarialike.model.World;
import ldts.terrarialike.utils.WorldUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class InventoryEventTest {

        @Test
        public void testExecute() throws InvalidSizeException, InvalidQuantityException, InvalidIndexException {
            // Setup
            Inventory invent1 = new Inventory(3);
            Inventory invent2  = new Inventory(3);

            Item item = Mockito.mock(Item.class);
            ItemStack itemStack = new ItemStack(item, 3);

            Item item2 = Mockito.mock(Item.class);
            ItemStack itemStack2 = new ItemStack(item2, 4);

            ItemStack itemStack3 = new ItemStack(item,62);

            List<ItemStack> list = new ArrayList<>();
            list.add(itemStack);
            list.add(itemStack2);

            List<ItemStack> list2 = new ArrayList<>();
            list2.add(itemStack3);

            invent1.setInventory(list);
            invent2.setInventory(list2);

            InventoryEvent inventoryEvent = new InventoryEvent(invent1, invent2, 4, 1);

            World world = Mockito.mock(World.class);
            WorldUtils worldUtils = Mockito.mock(WorldUtils.class);


            // Run the test
            List<GameEvent> result = inventoryEvent.execute(world, worldUtils);



            // Verify the results
            Assertions.assertEquals(1,invent1.getInventory().size());
            Assertions.assertEquals(item, invent1.getItem(0));
            Assertions.assertEquals(2, invent2.getInventory().size());
            Assertions.assertEquals(item2, invent2.getItem(1));


            InventoryEvent inventoryEvent1 = new InventoryEvent(invent1, invent2, 3, 0);

            List<GameEvent> result1 = inventoryEvent1.execute(world, worldUtils);

            Assertions.assertEquals(0,invent1.getInventory().size());
            Assertions.assertEquals(3, invent2.getInventory().size());
            Assertions.assertEquals(item, invent2.getItem(0));
            Assertions.assertEquals(item2, invent2.getItem(1));
            Assertions.assertEquals(item, invent2.getItem(2));
            Assertions.assertEquals(64, invent2.getItemStack(0).getQuantity());
            Assertions.assertEquals(1, invent2.getItemStack(2).getQuantity());






        }
// ver quando o segundo esta cheio, quando o primeiro esta vazio tem de retornar lista vazia e se os inventarios ficam iguais.
    }


