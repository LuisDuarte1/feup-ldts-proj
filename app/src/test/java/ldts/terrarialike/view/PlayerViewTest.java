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
        BoundlessPosition originalPos = Mockito.mock(BoundlessPosition.class);
        Mockito.when(originalPos.getX()).thenReturn(1);
        Mockito.when(originalPos.getY()).thenReturn(1);

        BoundlessPosition invertedPos = Mockito.mock(BoundlessPosition.class);
        Mockito.when(invertedPos.getX()).thenReturn(1);
        Mockito.when(invertedPos.getY()).thenReturn(99);

        Mockito.when(camera.isVisibleInCamera(Mockito.any())).thenReturn(true);
        Mockito.when(camera.getRelativePositionToCamera(Mockito.any())).thenReturn(originalPos);
        Mockito.when(camera.invertYPosition(originalPos)).thenReturn(invertedPos);

        playerView.draw(textGraphics,player);


        Mockito.verify(textGraphics).setForegroundColor(TextColor.ANSI.GREEN);
        Mockito.verify(textGraphics).setCharacter(1, 99, 'P');
    }
}
