package ldts.terrarialike.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import ldts.terrarialike.GUI.GUILanterna;
import ldts.terrarialike.controller.actions.AbstractAction;
import ldts.terrarialike.model.Player;
import ldts.terrarialike.statemanager.StateManager;
import ldts.terrarialike.utils.Pair;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.List;

public class InputHandlerTest {

    @Test
    public void inputHandlerNeedsKeyTest(){
        GUILanterna guiLanterna = Mockito.mock(GUILanterna.class);
        StateManager stateManager = Mockito.mock(StateManager.class);
        Player player = Mockito.mock(Player.class);

        InputHandler inputHandler = new InputHandler(guiLanterna,stateManager,player);

        AbstractAction abstractAction = Mockito.mock(AbstractAction.class);
        KeyStroke keyStroke = Mockito.mock(KeyStroke.class);
        Mockito.when(keyStroke.getKeyType()).thenReturn(KeyType.Character);


        HashMap<com.googlecode.lanterna.input.KeyStroke, Pair<AbstractAction,Boolean>> actionsMap = new HashMap<>();

        actionsMap.put(keyStroke,new Pair<>(abstractAction, true));

        inputHandler.setActionsMap(actionsMap);

        Mockito.when(guiLanterna.getAllKeyStrokes()).thenReturn(List.of(keyStroke));

        inputHandler.handleInput();

        Mockito.when(guiLanterna.getAllKeyStrokes()).thenReturn(List.of(new KeyStroke(KeyType.ArrowDown)));

        inputHandler.handleInput();

        Mockito.verify(abstractAction).processAction(Mockito.any(), Mockito.any());



    }

    @Test
    public void inputHandlerDoesntNeedKeyTest(){
        GUILanterna guiLanterna = Mockito.mock(GUILanterna.class);
        StateManager stateManager = Mockito.mock(StateManager.class);
        Player player = Mockito.mock(Player.class);

        InputHandler inputHandler = new InputHandler(guiLanterna,stateManager,player);

        AbstractAction abstractAction = Mockito.mock(AbstractAction.class);
        KeyStroke keyStroke = Mockito.mock(KeyStroke.class);
        Mockito.when(keyStroke.getKeyType()).thenReturn(KeyType.Character);


        HashMap<com.googlecode.lanterna.input.KeyStroke, Pair<AbstractAction,Boolean>> actionsMap = new HashMap<>();

        actionsMap.put(keyStroke,new Pair<>(abstractAction, false));

        inputHandler.setActionsMap(actionsMap);

        Mockito.when(guiLanterna.getAllKeyStrokes()).thenReturn(List.of(keyStroke));

        inputHandler.handleInput();

        Mockito.verify(abstractAction).processAction(Mockito.any(), Mockito.any());



    }
}
