package ldts.terrarialike.controller.actions;

import com.googlecode.lanterna.input.KeyStroke;
import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.controller.itemInteractions.DirectionItemInteraction;
import ldts.terrarialike.controller.itemInteractions.ItemInteraction;
import ldts.terrarialike.controller.events.ItemEventExecutorEvent;
import ldts.terrarialike.controller.itemInteractions.direction.DefaultAttackItem;
import ldts.terrarialike.model.InteractionType;
import ldts.terrarialike.model.Item;
import ldts.terrarialike.model.Player;

import java.util.ArrayList;
import java.util.List;

import static ldts.terrarialike.utils.InputUtils.getDirection;

public class AttackAction extends AbstractAction {
    public AttackAction() {
        super(true);
    }

    @Override
    public List<GameEvent> processAction(List<KeyStroke> arrowKeys, Player player) {
        if(arrowKeys.size() != 1){
            return new ArrayList<>();
        }
        Boolean direction = getDirection(arrowKeys);
        if(direction == null) return  new ArrayList<>();
        Item selectedItem = player.getInventory().getSelectedItem();
        ItemEventExecutorEvent itemEventExecutorEvent = null;
        DirectionItemInteraction itemInteraction = null;
        if(selectedItem == null){
            itemInteraction = new DefaultAttackItem();
            itemInteraction.setDirection(direction);
            itemEventExecutorEvent = new ItemEventExecutorEvent(InteractionType.ATTACK,
                    new Item(' ',"",itemInteraction));
        } else{
            itemEventExecutorEvent = new ItemEventExecutorEvent(InteractionType.ATTACK, selectedItem);
            if(selectedItem.getInteraction() == null) return new ArrayList<>();
            ItemInteraction selectedItemInteraction = selectedItem.getInteraction();
            if(!(selectedItemInteraction instanceof DirectionItemInteraction directionItemInteraction)) return new ArrayList<>();
            directionItemInteraction.setDirection(direction);

        }
        return List.of(itemEventExecutorEvent);
    }
}
