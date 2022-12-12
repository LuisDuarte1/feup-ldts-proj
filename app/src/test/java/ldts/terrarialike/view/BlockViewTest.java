package ldts.terrarialike.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.Block;
import ldts.terrarialike.model.BlockInfo;
import ldts.terrarialike.model.BoundlessPosition;
import ldts.terrarialike.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class BlockViewTest {
<<<<<<< HEAD
    /*
=======

    //FIXME: add tests for BlockView

>>>>>>> origin/main
    private Camera camera;
    private TextGraphics textGraphics;

    private Block block;

    private BlockInfo blockInfo;

    private BlockView blockView;

    @BeforeEach
    public void setUp(){
        textGraphics = Mockito.mock(TextGraphics.class);
        camera = Mockito.mock(Camera.class);
        block = Mockito.mock(Block.class);
        blockInfo = Mockito.mock(BlockInfo.class);
        Mockito.when(blockInfo.getBackgroundcolor()).thenReturn("#000000");
        Mockito.when(blockInfo.getForegroundColor()).thenReturn("#000000");

        Position mocked = Mockito.mock(Position.class);
        Mockito.when(mocked.getX()).thenReturn(0);
        Mockito.when(mocked.getY()).thenReturn(0);
        Mockito.when(block.getPosition()).thenReturn(mocked);
        Mockito.when(blockInfo.getRepresentation_char()).thenReturn('a');
        Mockito.when(block.getBlockInfo()).thenReturn(blockInfo);
        blockView = new BlockView(block, camera);


    }

    @Test
    public void renderVisibleBlock(){
        Mockito.when(camera.isVisibleInCamera(Mockito.any())).thenReturn(true);
        BoundlessPosition originalPos = Mockito.mock(BoundlessPosition.class);
        Mockito.when(originalPos.getX()).thenReturn(1);
        Mockito.when(originalPos.getY()).thenReturn(1);

        BoundlessPosition invertedPos = Mockito.mock(BoundlessPosition.class);
        Mockito.when(invertedPos.getX()).thenReturn(1);
        Mockito.when(invertedPos.getY()).thenReturn(99);
        Mockito.when(camera.getRelativePositionToCamera(Mockito.any())).thenReturn(originalPos);
        Mockito.when(camera.invertYPosition(originalPos)).thenReturn(invertedPos);

        blockView.draw(textGraphics);

        Mockito.verify(textGraphics).setBackgroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(textGraphics).setForegroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(textGraphics).putString(1,99, "a");
    }

    @Test
    public void dontRenderInvisibleBlockTest(){
        Mockito.when(camera.isVisibleInCamera(Mockito.any())).thenReturn(false);
        Mockito.when(camera.getRelativePositionToCamera(Mockito.any())).thenReturn(new BoundlessPosition(0,0));

        blockView.draw(textGraphics);
        Mockito.verify(textGraphics, Mockito.never()).setBackgroundColor(TextColor.Factory.fromString("#000000"));
        Mockito.verify(textGraphics, Mockito.never()).putString(1,99, "a");

    }
    */

}
