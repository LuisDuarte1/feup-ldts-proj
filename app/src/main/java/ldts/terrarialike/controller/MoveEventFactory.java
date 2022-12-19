package ldts.terrarialike.controller;


import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import ldts.terrarialike.controller.events.moveEvents.*;
import ldts.terrarialike.model.Entity;

import java.util.*;

public class MoveEventFactory {


    public static List<GameEvent> buildMoveEvents(Entity entity, List<KeyStroke> keyStrokes){

        List<GameEvent> gameEvents = new ArrayList<>();

        if(keyStrokes.contains(new KeyStroke(KeyType.ArrowUp)) && keyStrokes.size() == 2){
            //diagonal up move
            if(keyStrokes.contains(new KeyStroke(KeyType.ArrowLeft))) gameEvents.add(new MoveDiagonallyLeftEvent(entity));
            if(keyStrokes.contains(new KeyStroke(KeyType.ArrowRight))) gameEvents.add(new MoveDiagonallyRightEvent(entity));

        }else{
            //normal moves
            for (KeyStroke i:
                    keyStrokes) {
                    switch (i.getKeyType()){
                        case ArrowLeft:
                            gameEvents.add(new MoveLeftEvent(entity));
                            break;
                        case ArrowRight:
                            gameEvents.add(new MoveRightEvent(entity));
                            break;
                        case ArrowUp:
                            gameEvents.add(new MoveUpEvent(entity));
                            break;
                        case ArrowDown:
                            gameEvents.add(new MoveDownEvent(entity));
                            break;
                    }
            }

        }




        return gameEvents;
    }




    
}
