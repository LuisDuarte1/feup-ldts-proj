package ldts.terrarialike.controller.actions;

import com.googlecode.lanterna.input.KeyStroke;
import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.model.Player;
import ldts.terrarialike.utils.InputUtils;

import java.util.ArrayList;
import java.util.List;

public class EmptyAction extends AbstractAction {
    public EmptyAction(InputUtils inputUtils) {
        super(false, inputUtils);
    }

    @Override
    public List<GameEvent> processAction(List<KeyStroke> arrowKeys, Player player) {
        player.getInventory().selectEmpty();
        return new ArrayList<>();
    }
}
