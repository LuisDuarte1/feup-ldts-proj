package ldts.terrarialike.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import ldts.terrarialike.model.Block;
import ldts.terrarialike.model.BoundlessPosition;

public class BlockView implements ElementView {

    private Block block;
    private Camera camera;

    public BlockView(Block block, Camera camera){
        this.block = block;
        this.camera = camera;
    }

    @Override
    public void draw(TextGraphics graphics) {
        if(camera.isVisibleInCamera(block.getPosition())){
            graphics.setBackgroundColor(TextColor.Factory.fromString(block.getColor()));
            BoundlessPosition boundlessPosition = camera.getRelativePositionToCamera(block.getPosition());
            graphics.setCharacter(boundlessPosition.getX(), boundlessPosition.getX(), block.getRepresentation_char());
        }
    }
}
