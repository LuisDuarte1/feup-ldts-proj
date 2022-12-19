package ldts.terrarialike.controller.actions;

import com.googlecode.lanterna.input.KeyStroke;
import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.controller.events.ItemEventExecutorEvent;
import ldts.terrarialike.controller.itemInteractions.PositionItemInteraction;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.InteractionType;
import ldts.terrarialike.model.Item;
import ldts.terrarialike.model.Player;
import ldts.terrarialike.model.Position;

import java.util.ArrayList;
import java.util.List;

import static ldts.terrarialike.utils.InputUtils.getDesiredPosition;

public class UseAction extends AbstractAction {
    public UseAction() {
        super(true);
    }

    @Override
    public List<GameEvent> processAction(List<KeyStroke> arrowKeys, Player player) {
        Position desiredPosition = null;
        try {
            desiredPosition = getDesiredPosition(arrowKeys, player);
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
        if(!(selectedItem.getInteraction() instanceof PositionItemInteraction positionItemInteraction)) return new ArrayList<>();
        positionItemInteraction.setDesiredPosition(desiredPosition);
        return List.of(itemEventExecutorEvent);
    }
}
