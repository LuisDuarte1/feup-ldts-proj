package ldts.terrarialike.controller;

import ldts.terrarialike.exceptions.GameEventsIsNullException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;


public class GameEventManagerTest {

    private GameEvent gameEvent;

    private GameEventManager gameEventManagerTest;





    @Test

    public void gameEventManagerTest() {

        GameEventManager gameEventManager = new GameEventManager();
        Assertions.assertEquals(0,gameEventManager.gameEvents.size());
        Assertions.assertEquals(0,gameEventManager.nextFrameGameEvent.size());

    }

    @Test

    public void addGameEventTest() {

        gameEvent = Mockito.mock(GameEvent.class);
        GameEventManager gameEventManager = new GameEventManager();
        gameEventManager.addGameEvent(gameEvent);
        Assertions.assertEquals(1,gameEventManager.gameEvents.size());
        Assertions.assertEquals(gameEvent,gameEventManager.gameEvents.get(0));
        // ver se falta acrescentar alguma coisa

    }

    //fazer o teste do removeGameEvent

    @Test

    public void removeGameEventTest() throws GameEventsIsNullException {

        gameEvent = Mockito.mock(GameEvent.class);
        GameEventManager gameEventManager = new GameEventManager();
        List<GameEvent> gameEvents1 = new ArrayList<GameEvent>();
        gameEvents1.add(gameEvent);
        gameEventManager.setGameEvents(gameEvents1);
        gameEventManager.removeGameEvent(gameEvent);
        Assertions.assertEquals(0,gameEventManager.gameEvents.size());
        // ver se falta acrescentar alguma coisa

    }


    @Test

    public void executeAllEventsTest() { // verificar se falta acrescentar alguma coisa

        gameEventManagerTest = new GameEventManager();

        gameEvent = Mockito.mock(GameEvent.class);
        GameEvent gameEvent1 = Mockito.mock(GameEvent.class);
        GameEvent gameEvent2 = Mockito.mock(GameEvent.class);
        GameEvent gameEvent3 = Mockito.mock(GameEvent.class);
        GameEvent gameEvent4 = Mockito.mock(GameEvent.class);
        GameEvent gameEvent5 = Mockito.mock(GameEvent.class);

        List<GameEvent> gameEvents = new ArrayList<>();
        gameEvents.add(gameEvent5);

        List<GameEvent> gameEvents1 = new ArrayList<GameEvent>();
        gameEvents1.add(gameEvent3);

        List<GameEvent> gameEvents2 = new ArrayList<GameEvent>();
        gameEvents2.add(gameEvent4);

        List<GameEvent> gameEventstest= new ArrayList<GameEvent>();
        gameEventstest.add(gameEvent);
        gameEventstest.add(gameEvent1);
        gameEventstest.add(gameEvent2);

        gameEventManagerTest.setGameEvents(gameEventstest);

        Mockito.when(gameEvent.execute(Mockito.any())).thenReturn(gameEvents);
        Mockito.when(gameEvent1.execute(Mockito.any())).thenReturn(gameEvents1);
        Mockito.when(gameEvent2.execute(Mockito.any())).thenReturn(gameEvents2);

        gameEventManagerTest.executeAllEvents(Mockito.any());

            Assertions.assertEquals(3,gameEventManagerTest.gameEvents.size());
            Assertions.assertEquals(gameEvent5,gameEventManagerTest.gameEvents.get(0));
            Assertions.assertEquals(gameEvent3,gameEventManagerTest.gameEvents.get(1));
            Assertions.assertEquals(gameEvent4,gameEventManagerTest.gameEvents.get(2));
            Assertions.assertEquals(0,gameEventManagerTest.nextFrameGameEvent.size());

    }








}
