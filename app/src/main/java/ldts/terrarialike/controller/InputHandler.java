package ldts.terrarialike.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import ldts.terrarialike.GUI.GUILanterna;
import ldts.terrarialike.controller.events.ItemEventExecutorEvent;
import ldts.terrarialike.controller.itemInteractions.DefaultAttackItem;
import ldts.terrarialike.controller.itemInteractions.DefaultDestroyItemInteraction;
import ldts.terrarialike.exceptions.InvalidIndexException;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.exceptions.NotInitializedStateException;
import ldts.terrarialike.model.InteractionType;
import ldts.terrarialike.model.Item;
import ldts.terrarialike.model.Player;
import ldts.terrarialike.model.Position;
import ldts.terrarialike.statemanager.State;
import ldts.terrarialike.statemanager.StateManager;
import ldts.terrarialike.utils.Pair;
import ldts.terrarialike.view.menus.CraftingMenuView;
import ldts.terrarialike.view.menus.InventoryMenuView;
import ldts.terrarialike.view.menus.MainMenuView;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputHandler {

    private static final KeyStroke USE_KEY = new KeyStroke('a', false, false);
    private static final KeyStroke DESTROY_KEY = new KeyStroke('d', false, false);
    private static final KeyStroke ATTACK_KEY = new KeyStroke('s', false, false);

    private static final KeyStroke EMPTY_KEY = new KeyStroke('e',false,false);

    private static final KeyStroke INVENTORY_KEY = new KeyStroke('i',false,false);

    private static final KeyStroke CRAFTING_KEY = new KeyStroke('c', false,false);

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
            System.err.println("Invalid position while getting the desiredPosition...");
            return new ArrayList<>();
        }
        if(desiredPosition == null) return new ArrayList<>();
        Item selectedItem = player.getInventory().getSelectedItem();
        ItemEventExecutorEvent itemEventExecutorEvent = null;
        ItemInteraction itemInteraction = null;
        if(selectedItem != null){
            itemInteraction = selectedItem.getInteraction();
            if(itemInteraction == null) return new ArrayList<>();
            itemEventExecutorEvent = new ItemEventExecutorEvent(InteractionType.DESTROY, selectedItem);
        } else {
            itemInteraction = new DefaultDestroyItemInteraction();
            Item item = new Item('-', "Nothing", itemInteraction);
            itemEventExecutorEvent = new ItemEventExecutorEvent(InteractionType.DESTROY, item);
        }
        itemInteraction.setDesiredPosition(desiredPosition);
        return List.of(itemEventExecutorEvent);
    }

    private List<GameEvent> processUseAction(List<KeyStroke> arrowKeys){
        Position desiredPosition = null;
        try {
            desiredPosition = getDesiredPosition(arrowKeys);
        } catch (InvalidPositionException e) {
            System.err.println("Invalid position while getting the desiredPosition...");
            return new ArrayList<>();
        }
        if(desiredPosition == null) return new ArrayList<>();
        Item selectedItem = player.getInventory().getSelectedItem();
        ItemEventExecutorEvent itemEventExecutorEvent = null;
        if(selectedItem == null){
            //TODO: default item use interaction
            return new ArrayList<>();
        }
        itemEventExecutorEvent = new ItemEventExecutorEvent(InteractionType.USE, selectedItem);
        if(selectedItem.getInteraction() == null) return new ArrayList<>();
        selectedItem.getInteraction().setDesiredPosition(desiredPosition);
        return List.of(itemEventExecutorEvent);
    }

    private List<GameEvent> processAttackKey(List<KeyStroke> arrowKeys){
        if(arrowKeys.size() != 1){
            return new ArrayList<>();
        }
        Boolean direction = getDirection(arrowKeys);
        if(direction == null) return  new ArrayList<>();
        Item selectedItem = player.getInventory().getSelectedItem();
        ItemEventExecutorEvent itemEventExecutorEvent = null;
        ItemInteraction itemInteraction = null;
        if(selectedItem == null){
            itemInteraction = new DefaultAttackItem();
            itemInteraction.setDirection(direction);
            itemEventExecutorEvent = new ItemEventExecutorEvent(InteractionType.ATTACK,
                    new Item(' ',"",itemInteraction));
        } else{

            itemEventExecutorEvent = new ItemEventExecutorEvent(InteractionType.ATTACK, selectedItem);
            if(selectedItem.getInteraction() == null) return new ArrayList<>();
            selectedItem.getInteraction().setDirection(direction);

        }
        return List.of(itemEventExecutorEvent);
    }

    private static Boolean getDirection(List<KeyStroke> arrowKeys) {
        switch (arrowKeys.get(0).getKeyType()){
            case ArrowLeft -> {
                return false;
            }
            case ArrowRight -> {
                return true;
            }
            default -> {
                return null;
            }
        }
    }

    public List<GameEvent> handleInput(){
        List<GameEvent> gameEvents = new ArrayList<>();

        List<KeyStroke> keyStrokes = guiLanterna.getAllKeyStrokes();

        if(keyStrokes.size() == 0) return gameEvents;
        if(keyStrokes.contains(EMPTY_KEY)){
            player.getInventory().selectEmpty();
        } else if (keyStrokes.contains(INVENTORY_KEY)) {
            State inventoryManager = new State(Object.class, InventoryMenuView.class, Object.class);
            if(stateManager.getStates().contains(inventoryManager)){
                stateManager.removeState(inventoryManager);
            }
            inventoryManager.initializeDataClass();
            inventoryManager.initializeControllerClass();
            inventoryManager.initializeViewClass(player.getInventory(),stateManager,guiLanterna);
            try {
                stateManager.addState(inventoryManager);
                stateManager.selectState(inventoryManager);
            } catch (NotInitializedStateException e) {
                throw new RuntimeException(e);
            }
            return gameEvents;
        } else if (keyStrokes.contains(CRAFTING_KEY)) {
            State craftingManager = new State(Object.class, CraftingMenuView.class, Object.class);
            if(stateManager.getStates().contains(craftingManager)){
                stateManager.removeState(craftingManager);
            }
            craftingManager.initializeDataClass();
            craftingManager.initializeControllerClass();
            craftingManager.initializeViewClass(stateManager,guiLanterna, player.getInventory());
            try {
                stateManager.addState(craftingManager);
                stateManager.selectState(craftingManager);
            } catch (NotInitializedStateException e) {
                throw new RuntimeException(e);
            }
        }
        List<KeyStroke> numberKeys = keyStrokes.stream().filter((k1) -> {
            return k1.getKeyType() == KeyType.Character && k1.getCharacter() >= '1' && k1.getCharacter() <= '9';
        }).toList();
        for(KeyStroke number : numberKeys){
            Integer index = Integer.parseInt(number.getCharacter().toString()) - 1;
            try {
                player.getInventory().setSelecteditem(index);
            } catch (InvalidIndexException e) {
                player.getInventory().selectEmpty();
            }

        }
        List<KeyStroke> arrowKeys = filterArrowKeys(keyStrokes);
        processActions(keyStrokes);
        KeyStroke activeAction = getActiveAction();
        if (activeAction == null){
            gameEvents.addAll(MoveEventFactory.buildMoveEvents(player, arrowKeys));
        } else if(arrowKeys.size() != 0){
            if (activeAction.equals(DESTROY_KEY)) {
                gameEvents.addAll(processDestroyAction(arrowKeys));
            } else if(activeAction.equals(USE_KEY)){
                gameEvents.addAll(processUseAction(arrowKeys));
            } else if(activeAction.equals(ATTACK_KEY)){
                gameEvents.addAll(processAttackKey(arrowKeys));
            }
            resetActions();
        }




        return gameEvents;
    }
}
