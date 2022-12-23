package ldts.terrarialike.controller.actions;

import com.googlecode.lanterna.input.KeyStroke;
import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.controller.events.ItemEventExecutorEvent;
import ldts.terrarialike.controller.itemInteractions.position.DefaultDestroyItemInteraction;
import ldts.terrarialike.controller.itemInteractions.PositionItemInteraction;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.InteractionType;
import ldts.terrarialike.model.Item;
import ldts.terrarialike.model.Player;
import ldts.terrarialike.model.Position;
import ldts.terrarialike.utils.InputUtils;

import java.util.ArrayList;
import java.util.List;


public class DestroyAction extends AbstractAction {
    public DestroyAction(InputUtils inputUtils) {
        super(true, inputUtils);
    }

    @Override
    public List<GameEvent> processAction(List<KeyStroke> arrowKeys, Player player) {
        Position desiredPosition = null;
        try {
            desiredPosition = inputUtils.getDesiredPosition(arrowKeys, player);
        } catch (InvalidPositionException e) {
            System.err.println("Invalid position while getting the desiredPosition...");
            return new ArrayList<>();
        }
        if(desiredPosition == null) return new ArrayList<>();
        Item selectedItem = player.getInventory().getSelectedItem();
        ItemEventExecutorEvent itemEventExecutorEvent = null;
        PositionItemInteraction itemInteraction = null;
        if(selectedItem != null){
            if(!(selectedItem.getInteraction() instanceof PositionItemInteraction)) return new ArrayList<>();
            itemInteraction = (PositionItemInteraction) selectedItem.getInteraction();
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
