package ldts.terrarialike.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.BoundlessPosition;
import ldts.terrarialike.model.Player;
import ldts.terrarialike.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class PlayerViewTest {

    private Player player;
    private Camera camera;

    private TextGraphics textGraphics;
    private PlayerView playerView;

    @BeforeEach
    public void setup(){
        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(1);
        Mockito.when(position.getY()).thenReturn(1);

        player = Mockito.mock(Player.class);
        Mockito.when(player.getPosition()).thenReturn(position);
        camera = Mockito.mock(Camera.class);

        textGraphics = Mockito.mock(TextGraphics.class);

        playerView = new PlayerView(camera);
    }

    @Test
    public void shouldDrawPlayer(){
        Mockito.when(camera.isVisibleInCamera(Mockito.any())).thenReturn(true);
        Mockito.when(camera.getRelativePositionToCamera(Mockito.any())).thenReturn(new BoundlessPosition(1,1));

        playerView.draw(textGraphics,player);


        Mockito.verify(textGraphics).setForegroundColor(TextColor.ANSI.GREEN);
        Mockito.verify(textGraphics).setCharacter(1, 1, 'P');
    }
}
