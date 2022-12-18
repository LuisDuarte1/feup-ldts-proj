package ldts.terrarialike.controller.actions;

import com.googlecode.lanterna.input.KeyStroke;
import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.controller.ItemInteraction;
import ldts.terrarialike.controller.events.ItemEventExecutorEvent;
import ldts.terrarialike.controller.itemInteractions.DefaultDestroyItemInteraction;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.InteractionType;
import ldts.terrarialike.model.Item;
import ldts.terrarialike.model.Player;
import ldts.terrarialike.model.Position;

import java.util.ArrayList;
import java.util.List;

import static ldts.terrarialike.utils.InputUtils.getDesiredPosition;

public class DestroyAction extends AbstractAction {
    public DestroyAction() {
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
}
