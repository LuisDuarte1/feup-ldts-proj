package ldts.terrarialike.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import ldts.terrarialike.GUI.GUILanterna;
import ldts.terrarialike.controller.events.ItemEventExecutorEvent;
import ldts.terrarialike.controller.itemInteractions.DefaultDestroyItemInteraction;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.InteractionType;
import ldts.terrarialike.model.Item;
import ldts.terrarialike.model.Player;
import ldts.terrarialike.model.Position;
import ldts.terrarialike.statemanager.StateManager;
import ldts.terrarialike.utils.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputHandler {

    private static final KeyStroke USE_KEY = new KeyStroke('a', false, false);
    private static final KeyStroke DESTROY_KEY = new KeyStroke('d', false, false);
    private static final KeyStroke ATTACK_KEY = new KeyStroke('s', false, false);

    private static final KeyStroke EMPTY_KEY = new KeyStroke('e',false,false);

    private static final List<KeyStroke> ACTION_KEYS = Arrays.asList(USE_KEY,DESTROY_KEY,ATTACK_KEY);

    private final GUILanterna guiLanterna;

    private final Player player;

    private final StateManager stateManager;

    private final List<Pair<KeyStroke, Boolean>> actionActive;

    private static List<KeyStroke> filterArrowKeys(List<KeyStroke> keyStrokeList){
        return keyStrokeList.stream().filter((KeyStroke i) -> switch (i.getKeyType()) {
            case ArrowUp, ArrowDown, ArrowLeft, ArrowRight -> true;
            default -> false;
        }).toList();
    }


    public InputHandler(GUILanterna guiLanterna, StateManager stateManager, Player player) {
        this.guiLanterna = guiLanterna;
        this.stateManager = stateManager;
        this.player = player;
        this.actionActive = new ArrayList<>();
        for(KeyStroke e: ACTION_KEYS){
            actionActive.add(new Pair<>(e,false));
        }

    }

    private void resetActions(){
        for (Pair<KeyStroke, Boolean> p :
                actionActive) {
            p.second = false;
        }
    }

    private KeyStroke getActiveAction(){
        for (Pair<KeyStroke, Boolean> p :
                actionActive) {
            if(p.second) return p.first;
        }
        return null;
    }

    private void processActions(List<KeyStroke> keyStrokes){
        //check if there are any actions keys pressed at the moment
        List<KeyStroke> pressed_actions_keys = keyStrokes.stream().filter(
                        ACTION_KEYS::contains)
                .toList();
        if(pressed_actions_keys.size() == 1){
            resetActions();
            KeyStroke e = pressed_actions_keys.get(0);
            for (Pair<KeyStroke, Boolean> p: actionActive){
                if(p.first.equals(e)){
                    p.second = true;
                    break;
                }
            }
        } else if(pressed_actions_keys.size() != 0){
            resetActions();
        }

    }

    private Position getDesiredPosition(List<KeyStroke> arrowKeys) throws InvalidPositionException {
        if(arrowKeys.size() == 1){
            switch (arrowKeys.get(0).getKeyType()){
                case ArrowUp -> {
                    return new Position(player.getPosition().getX(), player.getPosition().getY() + 1);
                }
                case ArrowDown -> {
                    return new Position(player.getPosition().getX(), player.getPosition().getY() - 1);
                }
                case ArrowLeft -> {
                    return new Position(player.getPosition().getX() - 1, player.getPosition().getY());
                }
                case ArrowRight -> {
                    return  new Position(player.getPosition().getX() + 1, player.getPosition().getY());
                }
                default -> throw  new RuntimeException("ArrowList has  non Arrow Type... This should never happen... Good luck");
            }
        } else if(arrowKeys.size() == 2){
            //diagonals
            if(arrowKeys.contains(new KeyStroke(KeyType.ArrowUp)) && arrowKeys.contains(new KeyStroke(KeyType.ArrowRight)))
                return new Position(player.getPosition().getX() + 1, player.getPosition().getY() + 1);
            if(arrowKeys.contains(new KeyStroke(KeyType.ArrowUp)) && arrowKeys.contains(new KeyStroke(KeyType.ArrowLeft)))
                return new Position(player.getPosition().getX() - 1, player.getPosition().getY() + 1);
            if(arrowKeys.contains(new KeyStroke(KeyType.ArrowDown)) && arrowKeys.contains(new KeyStroke(KeyType.ArrowLeft)))
                return new Position(player.getPosition().getX() - 1, player.getPosition().getY() - 1);
            if(arrowKeys.contains(new KeyStroke(KeyType.ArrowDown)) && arrowKeys.contains(new KeyStroke(KeyType.ArrowRight)))
                return new Position(player.getPosition().getX() + 1, player.getPosition().getY() - 1);
        }
        return  null;
    }

    private List<GameEvent> processDestroyAction(List<KeyStroke> arrowKeys){
        Position desiredPosition = null;
        try {
            desiredPosition = getDesiredPosition(arrowKeys);
        } catch (InvalidPositionException e) {
            System.err.println(String.format("Invalid position while getting the desiredPosition..."));
            return new ArrayList<>();
        }
        if(desiredPosition == null) return new ArrayList<>();
        Item selectedItem = player.getInventory().getSelectedItem();
        ItemEventExecutorEvent itemEventExecutorEvent = null;
        if(selectedItem != null){
            selectedItem.getInteraction().setDesiredPosition(desiredPosition);
            itemEventExecutorEvent = new ItemEventExecutorEvent(InteractionType.DESTROY, selectedItem);
        } else {
            DefaultDestroyItemInteraction destroyItemInteraction = new DefaultDestroyItemInteraction();
            destroyItemInteraction.setDesiredPosition(desiredPosition);
            Item item = new Item('-', "Nothing", destroyItemInteraction);
            itemEventExecutorEvent = new ItemEventExecutorEvent(InteractionType.DESTROY, item);
        }
        return Arrays.asList(itemEventExecutorEvent);
    }

    public List<GameEvent> handleInput(){
        List<GameEvent> gameEvents = new ArrayList<>();

        List<KeyStroke> keyStrokes = guiLanterna.getAllKeyStrokes();

        if(keyStrokes.size() == 0) return gameEvents;
        if(keyStrokes.contains(EMPTY_KEY)){
            player.getInventory().selectEmpty();
        }
        List<KeyStroke> arrowKeys = filterArrowKeys(keyStrokes);
        processActions(keyStrokes);
        KeyStroke activeAction = getActiveAction();
        if (activeAction == null){
            gameEvents.addAll(MoveEventFactory.buildMoveEvents(player, arrowKeys));
        } else if(arrowKeys.size() != 0){
            if (activeAction.equals(DESTROY_KEY)) {
                gameEvents.addAll(processDestroyAction(arrowKeys));
            }
            resetActions();
        }




        return gameEvents;
    }
}
