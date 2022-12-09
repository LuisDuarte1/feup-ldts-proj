package ldts.terrarialike.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import ldts.terrarialike.model.BoundlessPosition;
import ldts.terrarialike.model.Entity;
import ldts.terrarialike.model.Zombie;

public class ZombieView implements EntityView {

    private Camera camera;

    public ZombieView(Camera camera) {
        this.camera = camera;
    }


    @Override
    public void draw(TextGraphics graphics, Entity zombie) {
        if(zombie instanceof Zombie && camera.isVisibleInCamera(zombie.getPosition())){
            graphics.setForegroundColor(TextColor.ANSI.MAGENTA);
            BoundlessPosition boundlessPosition = camera.getRelativePositionToCamera(zombie.getPosition());
           // boundlessPosition = camera.invertYPosition(boundlessPosition);
            graphics.setCharacter(boundlessPosition.getX(), boundlessPosition.getY(), 'Z');
        }



    }
}

