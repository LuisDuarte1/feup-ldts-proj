package ldts.terrarialike.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import ldts.terrarialike.model.BoundlessPosition;
import ldts.terrarialike.model.Entity;
import ldts.terrarialike.model.Player;
import org.jetbrains.annotations.NotNull;

public class PlayerView implements EntityView {

    private Camera camera;

    public PlayerView(Camera camera) {
        this.camera = camera;
    }

    @Override
    public void draw(TextGraphics graphics, Entity player) {
        if(player instanceof Player  && camera.isVisibleInCamera(player.getPosition())){
            graphics.setForegroundColor(TextColor.ANSI.GREEN);
            BoundlessPosition boundlessPosition = camera.getRelativePositionToCamera(player.getPosition());
            graphics.setCharacter(boundlessPosition.getX(), boundlessPosition.getY(), 'P');
        }
    }
}
