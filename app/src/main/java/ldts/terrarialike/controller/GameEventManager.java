package ldts.terrarialike.controller;


import ldts.terrarialike.exceptions.GameEventsIsNullException;
import ldts.terrarialike.model.World;

import java.util.ArrayList;
import java.util.List;

public class GameEventManager {

    List<GameEvent> gameEvents;
    List<GameEvent> nextFrameGameEvent;


    GameEventManager () {

        gameEvents = new ArrayList<GameEvent>();

        nextFrameGameEvent = new ArrayList<GameEvent>();

    }

    //setters and getters

    public void setGameEvents(List<GameEvent> gameEvents) {
        this.gameEvents = gameEvents;
    }

    public List<GameEvent> getGameEvents() {
        return gameEvents;
    }

    public void setNextFrameGameEvent(List<GameEvent> nextFrameGameEvent) {
        this.nextFrameGameEvent = nextFrameGameEvent;
    }

    public List<GameEvent> getNextFrameGameEvent() {
        return nextFrameGameEvent;
    }

    public void addGameEvent(GameEvent event) {
        gameEvents.add(event);
    }

    public void removeGameEvent(GameEvent event) throws GameEventsIsNullException {

        if( gameEvents != null) {

            gameEvents.remove(event);}

        else
            throw new GameEventsIsNullException("GameEvent is null");
    }

    public void executeAllEvents (World world){

        List<GameEvent> nextEvents = null;

        for( int i = 0; i < gameEvents.size(); i++){

            nextEvents = gameEvents.get(i).execute(world);

            nextFrameGameEvent.addAll(nextEvents);

        }

        gameEvents = nextFrameGameEvent;

        nextFrameGameEvent = new ArrayList<>();


    } // como inicializar o nextFrameGameEvent !!!!










    
}
