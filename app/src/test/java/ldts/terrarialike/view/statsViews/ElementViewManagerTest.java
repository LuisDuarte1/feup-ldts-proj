package ldts.terrarialike.view.statsViews;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ElementViewManagerTest {

    @Test
    void elementViewDrawTest(){
        ElementView elementView1 = Mockito.mock(ElementView.class);
        ElementView elementView2 = Mockito.mock(ElementView.class);


        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        Mockito.when(textGraphics.getSize()).thenReturn(new TerminalSize(100,100));
        Mockito.when(textGraphics.newTextGraphics(Mockito.any(), Mockito.any())).thenReturn(textGraphics);

        ElementViewManager elementViewManager = new ElementViewManager(textGraphics);

        elementViewManager.addElementViewPercentage(elementView1, 1.0);
        elementViewManager.addElementViewPercentage(elementView1, 1.0);
        Mockito.verify(textGraphics).newTextGraphics(new TerminalPosition(0,0), new TerminalSize(100,100));
        elementViewManager.addElementViewPercentage(elementView2, 0.5);
        Mockito.verify(textGraphics).newTextGraphics(new TerminalPosition(50,0), new TerminalSize(50,100));

        elementViewManager.draw();
        Mockito.verify(elementView1).draw(textGraphics);
        Mockito.verify(elementView2).draw(textGraphics);

    }

}
