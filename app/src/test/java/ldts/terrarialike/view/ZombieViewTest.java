package ldts.terrarialike.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import ldts.terrarialike.model.BoundlessPosition;
import ldts.terrarialike.model.Position;
import ldts.terrarialike.model.Zombie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ZombieViewTest {
    private Zombie zombie;
    private Camera camera;

    private TextGraphics textGraphics;
    private ZombieView zombieView;

    @BeforeEach
    public void setup(){
        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(1);
        Mockito.when(position.getY()).thenReturn(1);

        zombie = Mockito.mock(Zombie.class);
        Mockito.when(zombie.getPosition()).thenReturn(position);
        camera = Mockito.mock(Camera.class);

        textGraphics = Mockito.mock(TextGraphics.class);

        zombieView = new ZombieView(camera);
    }

    @Test
    public void shouldDrawZombie(){
        BoundlessPosition originalPos = Mockito.mock(BoundlessPosition.class);
        Mockito.when(originalPos.getX()).thenReturn(1);
        Mockito.when(originalPos.getY()).thenReturn(1);

        BoundlessPosition invertedPos = Mockito.mock(BoundlessPosition.class);
        Mockito.when(invertedPos.getX()).thenReturn(1);
        Mockito.when(invertedPos.getY()).thenReturn(99);

        Mockito.when(camera.isVisibleInCamera(Mockito.any())).thenReturn(true);
        Mockito.when(camera.getRelativePositionToCamera(Mockito.any())).thenReturn(new BoundlessPosition(1,1));
        Mockito.when(camera.getRelativePositionToCamera(Mockito.any())).thenReturn(originalPos);
        Mockito.when(camera.invertYPosition(originalPos)).thenReturn(invertedPos);

        zombieView.draw(textGraphics,zombie);


        Mockito.verify(textGraphics).setForegroundColor(TextColor.ANSI.MAGENTA);
        Mockito.verify(textGraphics).setCharacter(1, 99, 'Z');
    }
}
