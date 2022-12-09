package ldts.terrarialike.controller.itemInteractions;


import ldts.terrarialike.controller.ItemInteraction;
import ldts.terrarialike.model.BlockInfo;
import ldts.terrarialike.model.InteractionType;
import ldts.terrarialike.model.Item;
import ldts.terrarialike.model.Position;
import ldts.terrarialike.model.World;

public class UseBlockItemInteraction implements ItemInteraction{

    private BlockInfo blockInfo;
    private Position desiredPosition;



    public UseBlockItemInteraction(BlockInfo blockInfo, Position desiredPosition) {
        this.blockInfo = blockInfo;
        this.desiredPosition = desiredPosition;
    }



    @Override
    public void execute(World one, InteractionType interactionType, Item item) {
        if(interactionType == InteractionType.USE && one.getBlock(desiredPosition) == null){
            
        }
    }
    
}
