package ldts.terrarialike.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import ldts.terrarialike.model.BoundlessPosition;
import ldts.terrarialike.model.Player;
import ldts.terrarialike.model.Position;
import ldts.terrarialike.model.Skeleton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SkeletonViewTest {
    private Skeleton skeleton;
    private Camera camera;

    private TextGraphics textGraphics;
    private SkeletonView skeletonView;

    @BeforeEach
    public void setup(){
        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(1);
        Mockito.when(position.getY()).thenReturn(1);

        skeleton = Mockito.mock(Skeleton.class);
        Mockito.when(skeleton.getPosition()).thenReturn(position);
        camera = Mockito.mock(Camera.class);

        textGraphics = Mockito.mock(TextGraphics.class);

        skeletonView = new SkeletonView(camera);
    }

    @Test
    public void shouldDrawPlayer(){
        Mockito.when(camera.isVisibleInCamera(Mockito.any())).thenReturn(true);
        Mockito.when(camera.getRelativePositionToCamera(Mockito.any())).thenReturn(new BoundlessPosition(1,1));

        skeletonView.draw(textGraphics,skeleton);


        Mockito.verify(textGraphics).setForegroundColor(TextColor.ANSI.BLACK);
        Mockito.verify(textGraphics).setCharacter(1, 1, 'S');
    }
}
