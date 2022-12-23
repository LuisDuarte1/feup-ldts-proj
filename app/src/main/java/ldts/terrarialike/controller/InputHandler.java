package ldts.terrarialike.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import ldts.terrarialike.GUI.GUILanterna;
import ldts.terrarialike.controller.actions.*;
import ldts.terrarialike.exceptions.InvalidIndexException;
import ldts.terrarialike.model.Player;
import ldts.terrarialike.statemanager.StateManager;
import ldts.terrarialike.utils.InputUtils;
import ldts.terrarialike.utils.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static ldts.terrarialike.utils.InputUtils.*;

public class InputHandler {

    private HashMap<KeyStroke, Pair<AbstractAction,Boolean>> actionsMap = new HashMap<>();


    private final Player player;

    private final GUILanterna guiLanterna;






    public InputHandler(GUILanterna guiLanterna, StateManager stateManager, Player player) {
        this.player = player;
        this.guiLanterna = guiLanterna;
        actionsMap.put(new KeyStroke('a', false, false),
                new Pair<>(new UseAction(new InputUtils()), false));

        actionsMap.put(new KeyStroke('d', false, false),
                new Pair<>(new DestroyAction(new InputUtils()), false));

        actionsMap.put(new KeyStroke('s', false, false),
                new Pair<>(new AttackAction(new InputUtils()), false));
        actionsMap.put(new KeyStroke('i', false, false),
                new Pair<>(new InventoryAction(stateManager, guiLanterna, new InputUtils()), false));

        actionsMap.put(new KeyStroke('c', false, false),
                new Pair<>(new CraftingAction(stateManager, guiLanterna, new InputUtils()), false));

        actionsMap.put(new KeyStroke('e', false, false),
                new Pair<>(new EmptyAction(new InputUtils()), false));
    }

    private void resetActions(){
        for (Pair<AbstractAction, Boolean> p :
                actionsMap.values()) {
            p.second = false;
        }
    }

    private AbstractAction getActiveAction(){
        for (Pair<AbstractAction, Boolean> p :
                actionsMap.values()) {
            if(p.second) return p.first;
        }
        return null;
    }

    private void processActions(List<KeyStroke> keyStrokes){
        //check if there are any actions keys pressed at the moment
        List<KeyStroke> pressed_actions_keys = keyStrokes.stream().filter(
                        actionsMap.keySet()::contains)
                .toList();
        if(pressed_actions_keys.size() == 1){
            resetActions();
            KeyStroke e = pressed_actions_keys.get(0);
            for (KeyStroke i : actionsMap.keySet()){
                if(i.equals(e)){
                    actionsMap.get(i).second = true;
                    break;
                }
            }
        } else if(pressed_actions_keys.size() != 0){
            resetActions();
        }

    }


    public List<GameEvent> handleInput(){
        List<GameEvent> gameEvents = new ArrayList<>();

        List<KeyStroke> keyStrokes = guiLanterna.getAllKeyStrokes();

        if(keyStrokes.size() == 0) return gameEvents;

        List<KeyStroke> numberKeys = keyStrokes.stream().filter((k1) -> k1.getKeyType() == KeyType.Character && k1.getCharacter() >= '1' && k1.getCharacter() <= '9').toList();
        for(KeyStroke number : numberKeys){
            int index = Integer.parseInt(number.getCharacter().toString()) - 1;
            try {
                player.getInventory().setSelecteditem(index);
            } catch (InvalidIndexException e) {
                player.getInventory().selectEmpty();
            }

        }
        List<KeyStroke> arrowKeys = filterArrowKeys(keyStrokes);
        processActions(keyStrokes);
        AbstractAction activeAction = getActiveAction();
        if (activeAction == null){
            gameEvents.addAll(MoveEventFactory.buildMoveEvents(player, arrowKeys));
        } else if(arrowKeys.size() != 0 || !activeAction.usesArrows()){
            gameEvents.addAll(activeAction.processAction(arrowKeys,player));
            resetActions();
        }


        return gameEvents;
    }

    public HashMap<KeyStroke, Pair<AbstractAction, Boolean>> getActionsMap() {
        return actionsMap;
    }

    public void setActionsMap(HashMap<KeyStroke, Pair<AbstractAction, Boolean>> actionsMap) {
        this.actionsMap = actionsMap;
    }
}
