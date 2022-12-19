package ldts.terrarialike.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import ldts.terrarialike.model.BoundlessPosition;
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
    public void shouldDrawSkeleton(){
        BoundlessPosition originalPos = Mockito.mock(BoundlessPosition.class);
        Mockito.when(originalPos.getX()).thenReturn(1);
        Mockito.when(originalPos.getY()).thenReturn(1);

        BoundlessPosition invertedPos = Mockito.mock(BoundlessPosition.class);
        Mockito.when(invertedPos.getX()).thenReturn(1);
        Mockito.when(invertedPos.getY()).thenReturn(99);

        Mockito.when(camera.isVisibleInCamera(Mockito.any())).thenReturn(true);
        Mockito.when(camera.getRelativePositionToCamera(Mockito.any())).thenReturn(originalPos);
        Mockito.when(camera.invertYPosition(originalPos)).thenReturn(invertedPos);


        skeletonView.draw(textGraphics,skeleton);


        Mockito.verify(textGraphics).setForegroundColor(TextColor.ANSI.BLACK);
        Mockito.verify(textGraphics).setCharacter(1, 99, 'S');
    }
}
