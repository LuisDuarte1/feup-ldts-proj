package ldts.terrarialike.controller;


import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import ldts.terrarialike.controller.events.moveEvents.*;
import ldts.terrarialike.model.Entity;
import org.checkerframework.checker.units.qual.K;

import java.util.*;

public class MoveEventFactory {


    public static List<GameEvent> buildMoveEvents(Entity entity, List<KeyStroke> keyStrokes){
        List<KeyStroke> keyStrokeFilteredList = new ArrayList<>();

        //filter list
        for (KeyStroke keyStroke :
                keyStrokes) {
            switch (keyStroke.getKeyType()){
                case ArrowLeft:
                case ArrowUp:
                case ArrowRight:
                    keyStrokeFilteredList.add(keyStroke);
                    break;
            }
        }
        List<GameEvent> gameEvents = new ArrayList<>();

        if(keyStrokeFilteredList.contains(new KeyStroke(KeyType.ArrowUp)) && keyStrokeFilteredList.size() == 2){
            //diagonal up move
            if(keyStrokeFilteredList.contains(new KeyStroke(KeyType.ArrowLeft))) gameEvents.add(new MoveDiagonallyLeftEvent(entity));
            if(keyStrokeFilteredList.contains(new KeyStroke(KeyType.ArrowRight))) gameEvents.add(new MoveDiagonallyRightEvent(entity));

        }else{
            //normal moves
            for (KeyStroke i:
                 keyStrokeFilteredList) {
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
                    }
            }

        }




        return gameEvents;
    }




    
}
