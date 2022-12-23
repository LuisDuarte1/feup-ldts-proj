package ldts.terrarialike.controller.ItemInteraction.position;

import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.controller.itemInteractions.position.DefaultDestroyItemInteraction;
import ldts.terrarialike.controller.itemInteractions.position.PickaxeItemInteraction;
import ldts.terrarialike.exceptions.*;
import ldts.terrarialike.model.*;
import ldts.terrarialike.utils.WorldUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class DefaultDestroyItemInteractionTest {

    @Test

    public void testExecute() throws InvalidSizeException, InvalidPositionException, BlockNotFoundException, InvalidQuantityException, InventoryFullException {

        WorldUtils worldUtils = Mockito.mock(WorldUtils.class);
        World world = Mockito.mock(World.class);

        Position position = Mockito.mock(Position.class);

        Mockito.when(position.getX()).thenReturn(0);
        Mockito.when(position.getY()).thenReturn(0);

        Block block = Mockito.mock(Block.class);


        List<Chunk> chunks = new ArrayList<>();
        Chunk chunk = Mockito.mock(Chunk.class);

        chunks.add(chunk);

        Mockito.when(chunk.getChunkID()).thenReturn(0);


        Mockito.when(world.getChunks()).thenReturn(chunks);
        // chunkID = 0
        Mockito.when(worldUtils.getBlock(position, world)).thenReturn(block);

        Item item = Mockito.mock(Item.class);
        Item item1 = Mockito.mock(Item.class);

        BlockInfo blockInfo = Mockito.mock(BlockInfo.class);

        Mockito.when(block.getBlockInfo()).thenReturn(blockInfo);

        Mockito.when(block.getBlockInfo().getToDropItem()).thenReturn(item);

        Mockito.when(block.getBlockInfo().getDurability()).thenReturn(2);

        Inventory inventory = Mockito.mock(Inventory.class);

        Player player = Mockito.mock(Player.class);
        Mockito.when(world.getPlayer()).thenReturn(player);
        Mockito.when(player.getInventory()).thenReturn(inventory);


        PlayerLogs playerLogs = Mockito.mock(PlayerLogs.class);
        Mockito.when(player.getPlayerLogs()).thenReturn(playerLogs);

        DefaultDestroyItemInteraction defaultDestroyItemInteraction = new DefaultDestroyItemInteraction();

        defaultDestroyItemInteraction.setDesiredPosition(position);

        List<GameEvent> gameEvents = defaultDestroyItemInteraction.execute(world, InteractionType.DESTROY, item1, worldUtils);

        Assertions.assertEquals(0, gameEvents.size());
        Mockito.verify(chunk, Mockito.times(0)).removeBlock(position);

    }

    @Test

    public void testExecute2() throws InvalidSizeException, InvalidPositionException, BlockNotFoundException, InvalidQuantityException, InventoryFullException {

        WorldUtils worldUtils = Mockito.mock(WorldUtils.class);
        World world = Mockito.mock(World.class);

        Position position = Mockito.mock(Position.class);

        Mockito.when(position.getX()).thenReturn(0);
        Mockito.when(position.getY()).thenReturn(0);

        Block block = Mockito.mock(Block.class);


        List<Chunk> chunks = new ArrayList<>();
        Chunk chunk = Mockito.mock(Chunk.class);

        chunks.add(chunk);

        Mockito.when(chunk.getChunkID()).thenReturn(0);


        Mockito.when(world.getChunks()).thenReturn(chunks);
        // chunkID = 0
        Mockito.when(worldUtils.getBlock(position,world)).thenReturn(block);

        Item item = Mockito.mock(Item.class);
        Item item1 = Mockito.mock(Item.class);

        BlockInfo blockInfo = Mockito.mock(BlockInfo.class);

        Mockito.when(block.getBlockInfo()).thenReturn(blockInfo);

        Mockito.when(block.getBlockInfo().getToDropItem()).thenReturn(item);

        Mockito.when(block.getBlockInfo().getDurability()).thenReturn(1);

        Inventory inventory = Mockito.mock(Inventory.class);

        Player player = Mockito.mock(Player.class);
        Mockito.when(world.getPlayer()).thenReturn(player);
        Mockito.when(player.getInventory()).thenReturn(inventory);


        PlayerLogs playerLogs = Mockito.mock(PlayerLogs.class);
        Mockito.when(player.getPlayerLogs()).thenReturn(playerLogs);

        DefaultDestroyItemInteraction defaultDestroyItemInteraction = new DefaultDestroyItemInteraction();

        defaultDestroyItemInteraction.setDesiredPosition(position);

        List<GameEvent> gameEvents = defaultDestroyItemInteraction.execute(world,InteractionType.DESTROY,item1, worldUtils);

        Mockito.verify(chunk,Mockito.times(1)).removeBlock(position);
        Mockito.verify(inventory,Mockito.times(1)).add(item,1);
        Mockito.verify(playerLogs,Mockito.times(1)).addLogString(Mockito.anyString());




    }}
