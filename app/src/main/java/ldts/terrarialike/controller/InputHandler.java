package ldts.terrarialike.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import ldts.terrarialike.GUI.GUILanterna;
import ldts.terrarialike.controller.itemInteractions.DefaultItemInteraction;
import ldts.terrarialike.exceptions.InvalidIndexException;
import ldts.terrarialike.model.Item;
import ldts.terrarialike.model.ItemStack;
import ldts.terrarialike.model.Player;
import ldts.terrarialike.statemanager.StateManager;
import org.checkerframework.checker.units.qual.K;

import java.util.ArrayList;
import java.util.List;

public class InputHandler {

    private static final KeyStroke USE_KEY = new KeyStroke(' ', false, false);

    private GUILanterna guiLanterna;

    private Player player;

    private StateManager stateManager;


    public InputHandler(GUILanterna guiLanterna, StateManager stateManager, Player player) {
        this.guiLanterna = guiLanterna;
        this.stateManager = stateManager;
        this.player = player;
    }

    public List<GameEvent> handleInput(){
        List<GameEvent> gameEvents = new ArrayList<>();

        List<KeyStroke> keyStrokes = guiLanterna.getAllKeyStrokes();

        if(keyStrokes.size() == 0) return gameEvents;



        if(!keyStrokes.contains(USE_KEY)){
            gameEvents.addAll(MoveEventFactory.buildMoveEvents(player, keyStrokes));
        } else{
            //reserved for item interactions
            if(keyStrokes.contains(new KeyStroke(KeyType.ArrowUp)) && keyStrokes.contains(new KeyStroke(KeyType.ArrowRight))){
                try {
                    Item i = player.getInventory().getItem(player.getInventory().getSelecteditemindex());
                    
                } catch (InvalidIndexException e) {
                    return gameEvents;
                }
            }
        }




        return gameEvents;
    }
}
