package ldts.terrarialike.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import ldts.terrarialike.model.BoundlessPosition;
import ldts.terrarialike.model.Entity;
import ldts.terrarialike.model.Player;

public class PlayerView implements EntityView {

    private Camera camera;

    public PlayerView(Camera camera) {
        this.camera = camera;
    }

    @Override
    public void draw(TextGraphics graphics, Entity player) {
        if(player instanceof Player  && camera.isVisibleInCamera(player.getPosition())){
            graphics.setForegroundColor(TextColor.ANSI.MAGENTA);
            BoundlessPosition boundlessPosition = camera.getRelativePositionToCamera(player.getPosition());
            boundlessPosition = camera.invertYPosition(boundlessPosition);
            graphics.enableModifiers(SGR.BOLD, SGR.BORDERED);
            graphics.setCharacter(boundlessPosition.getX(), boundlessPosition.getY(), 'P');
            graphics.disableModifiers(SGR.BOLD, SGR.BORDERED);
        }
    }
}
