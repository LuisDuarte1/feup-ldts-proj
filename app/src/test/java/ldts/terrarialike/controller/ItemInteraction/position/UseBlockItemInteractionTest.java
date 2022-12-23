package ldts.terrarialike.controller.ItemInteraction.position;

import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.controller.itemInteractions.position.UseBlockItemInteraction;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.exceptions.InvalidQuantityException;
import ldts.terrarialike.exceptions.InvalidSizeException;
import ldts.terrarialike.exceptions.ItemNotFoundException;
import ldts.terrarialike.model.*;
import ldts.terrarialike.utils.WorldUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class UseBlockItemInteractionTest {

    @Test

    public void testExecute() throws InvalidSizeException, InvalidPositionException, InvalidQuantityException, ItemNotFoundException {

        BlockInfo blockInfo = Mockito.mock(BlockInfo.class);

        Item item = Mockito.mock(Item.class);



        WorldUtils worldUtils = Mockito.mock(WorldUtils.class);
        World world = Mockito.mock(World.class);

        Position position = Mockito.mock(Position.class);

        Mockito.when(position.getX()).thenReturn(0);
        Mockito.when(position.getY()).thenReturn(0);

        Inventory inventory = Mockito.mock(Inventory.class);
        Player player = Mockito.mock(Player.class);
        Mockito.when(world.getPlayer()).thenReturn(player);
        Mockito.when(player.getInventory()).thenReturn(inventory);


        PlayerLogs playerLogs = Mockito.mock(PlayerLogs.class);
        Mockito.when(player.getPlayerLogs()).thenReturn(playerLogs);

        List<Chunk> chunks = new ArrayList<>();
        Chunk chunk = Mockito.mock(Chunk.class);

        chunks.add(chunk);

        Mockito.when(chunk.getChunkID()).thenReturn(0);

        Mockito.when(world.getChunks()).thenReturn(chunks);

        UseBlockItemInteraction useBlockItemInteraction = new UseBlockItemInteraction(blockInfo);

        useBlockItemInteraction.setDesiredPosition(position);

        List<GameEvent> gameEvents= useBlockItemInteraction.execute(world,InteractionType.USE,item, worldUtils);

        Mockito.verify(chunk, Mockito.times(1)).addBlock(Mockito.any());
        Mockito.verify(inventory, Mockito.times(1)).remove(item,1);


        // chunkID = 0









    }



}
