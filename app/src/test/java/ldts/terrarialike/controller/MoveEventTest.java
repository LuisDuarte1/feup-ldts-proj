package ldts.terrarialike.controller;

import ldts.terrarialike.controller.events.*;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.Block;
import ldts.terrarialike.model.Entity;
import ldts.terrarialike.model.Position;
import ldts.terrarialike.model.World;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MoveEventTest {




    @Test

    public void testExecute() throws InvalidPositionException {

        Entity entity = Mockito.mock(Entity.class);
        Mockito.when(entity.getPosition()).thenReturn(new Position(5,5));

        World worldtest = Mockito.mock(World.class);
        Mockito.when(worldtest.getBlock(Mockito.any())).thenReturn(null);



        MoveEvent moveEvent = new MoveUpEvent( entity);
        moveEvent.execute(worldtest);
        Mockito.verify(entity, Mockito.times(1)).setPosition(new Position(5, 6));

        moveEvent = new MoveDownEvent(entity);
        moveEvent.execute(worldtest);
        Mockito.verify(entity, Mockito.times(1)).setPosition(new Position(5,4));

        moveEvent = new MoveLeftEvent(entity);
        moveEvent.execute(worldtest);
        Mockito.verify(entity, Mockito.times(1)).setPosition(new Position(4,5));

        moveEvent = new MoveRightEvent(entity);
        moveEvent.execute(worldtest);
        Mockito.verify(entity, Mockito.times(1)).setPosition(new Position(6,5));

        //when he cant move
        Block blocktest = Mockito.mock(Block.class);
        Mockito.when(worldtest.getBlock(Mockito.any())).thenReturn(blocktest);

        moveEvent = new MoveUpEvent(entity);
        moveEvent.execute(worldtest);
        Mockito.verify(entity, Mockito.times(0)).setPosition(null);
        Assertions.assertEquals(new Position(5,5), entity.getPosition());


        moveEvent = new MoveDownEvent(entity);
        moveEvent.execute(worldtest);
        Mockito.verify(entity, Mockito.times(0)).setPosition(null);
        Assertions.assertEquals(new Position(5,5), entity.getPosition());

        moveEvent = new MoveLeftEvent(entity);
        moveEvent.execute(worldtest);
        Mockito.verify(entity, Mockito.times(0)).setPosition(null);
        Assertions.assertEquals(new Position(5,5), entity.getPosition());

        moveEvent = new MoveRightEvent(entity);
        moveEvent.execute(worldtest);
        Mockito.verify(entity, Mockito.times(0)).setPosition(null);
        Assertions.assertEquals(new Position(5,5), entity.getPosition());











    }



}
