package ldts.terrarialike.controller.events;

import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.exceptions.*;
import ldts.terrarialike.model.Inventory;
import ldts.terrarialike.model.Item;
import ldts.terrarialike.model.World;

import java.util.ArrayList;
import java.util.List;

public class InventoryEvent implements GameEvent {

    private int itemindex;
    private int quantity;
    private Inventory sourceinvent;
    private Inventory receiverinvent;

    public InventoryEvent(Inventory sourceinvent, Inventory receiverinvent, int quantity, int itemindex) {


        this.sourceinvent = sourceinvent;
        this.receiverinvent = receiverinvent;
        this.itemindex = itemindex;
        this.quantity = quantity;
    }

    @Override
    public List<GameEvent> execute(World world) {

        List<GameEvent> list = new ArrayList<>();


        try {
            if(sourceinvent != null){
                Item item = sourceinvent.getItem(itemindex);
                sourceinvent.remove(item, quantity);
                receiverinvent.add(item, quantity);
            }

            }
        catch( InvalidIndexException | ItemNotFoundException | InventoryFullException | InvalidQuantityException e){
            e.printStackTrace();
            return new ArrayList<>();
    }
        return list;
    }

}
