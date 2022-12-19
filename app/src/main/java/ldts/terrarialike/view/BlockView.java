package ldts.terrarialike.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import ldts.terrarialike.model.Block;
import ldts.terrarialike.model.BlockInfo;
import ldts.terrarialike.model.BoundlessPosition;
import ldts.terrarialike.view.statsViews.ElementView;

public class BlockView implements ElementView {

    private Block block;
    private Camera camera;

    public BlockView(Block block, Camera camera){
        this.block = block;
        this.camera = camera;
    }

    @Override
    public void draw(TextGraphics graphics) {
        if(camera.isVisibleInCamera(block.getPosition())) {
            BlockInfo blockInfo = block.getBlockInfo();
            graphics.setBackgroundColor(TextColor.Factory.fromString(blockInfo.getBackgroundcolor()));
            graphics.setForegroundColor(TextColor.Factory.fromString(blockInfo.getForegroundColor()));


            BoundlessPosition boundlessPosition = camera.getRelativePositionToCamera(block.getPosition());
            boundlessPosition = camera.invertYPosition(boundlessPosition);
            graphics.putString(boundlessPosition.getX(), boundlessPosition.getY(), Character.toString(blockInfo.getRepresentation_char()));
        }
    }
}
