package ldts.terrarialike.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.Block;
import ldts.terrarialike.model.BoundlessPosition;
import ldts.terrarialike.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class BlockViewTest {
    /*
    private Camera camera;
    private TextGraphics textGraphics;

    private Block block;

    private BlockView blockView;

    @BeforeEach
    public void setUp(){
        textGraphics = Mockito.mock(TextGraphics.class);
        camera = Mockito.mock(Camera.class);
        block = Mockito.mock(Block.class);
        Mockito.when(block.getColor()).thenReturn("#000000");
        Position mocked = Mockito.mock(Position.class);
        Mockito.when(mocked.getX()).thenReturn(0);
        Mockito.when(mocked.getY()).thenReturn(0);
        Mockito.when(block.getPosition()).thenReturn(mocked);
        Mockito.when(block.getRepresentation_char()).thenReturn('a');
        blockView = new BlockView(block, camera);


    }

    @Test
    public void renderVisibleBlock(){
        Mockito.when(camera.isVisibleInCamera(Mockito.any())).thenReturn(true);
        Mockito.when(camera.getRelativePositionToCamera(Mockito.any())).thenReturn(new BoundlessPosition(0,0));
        blockView.draw(textGraphics);

        Mockito.verify(textGraphics).setBackgroundColor(TextColor.Factory.fromString(block.getColor()));
        Mockito.verify(textGraphics).setCharacter(0,0, block.getRepresentation_char());
    }

    @Test
    public void dontRenderInvisibleBlockTest(){
        Mockito.when(camera.isVisibleInCamera(Mockito.any())).thenReturn(false);
        Mockito.when(camera.getRelativePositionToCamera(Mockito.any())).thenReturn(new BoundlessPosition(0,0));

        blockView.draw(textGraphics);
        Mockito.verify(textGraphics, Mockito.never()).setBackgroundColor(TextColor.Factory.fromString(block.getColor()));
        Mockito.verify(textGraphics, Mockito.never()).setCharacter(0,0, block.getRepresentation_char());

    }
    */

}
