package ldts.terrarialike.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import ldts.terrarialike.model.BoundlessPosition;
import ldts.terrarialike.model.Entity;
import ldts.terrarialike.model.Skeleton;

public class SkeletonView implements EntityView{

    private Camera camera;

    public SkeletonView(Camera camera) {
        this.camera = camera;
    }


    @Override
    public void draw(TextGraphics graphics, Entity skeleton) {
        if (skeleton instanceof Skeleton && camera.isVisibleInCamera(skeleton.getPosition())) {
            graphics.setForegroundColor(TextColor.ANSI.BLACK);
            BoundlessPosition boundlessPosition = camera.getRelativePositionToCamera(skeleton.getPosition());
            boundlessPosition = camera.invertYPosition(boundlessPosition);
            graphics.setCharacter(boundlessPosition.getX(), boundlessPosition.getY(), 'S');
        }
    }}
