package ldts.terrarialike.GUI;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.awt.*;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

public class GUILanterna {

    private Terminal terminal;
    private Screen screen;
    private TerminalSize terminalSize;

    private TextGraphics textGraphics;

    public GUILanterna(int width, int height, String windowName){
        terminalSize = new TerminalSize(width, height);
        try{
            DefaultTerminalFactory  dFactory= new DefaultTerminalFactory();
            dFactory.setTerminalEmulatorFontConfiguration(loadSquareFont());
            dFactory.setTerminalEmulatorTitle(windowName);
            terminal = dFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
            textGraphics = screen.newTextGraphics();
        } catch(Exception e){
            System.err.println("Error when creating the GUI:\n\n");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private AWTTerminalFontConfiguration loadSquareFont() throws URISyntaxException, FontFormatException, IOException{
        URL resource = getClass().getClassLoader().getResource("square.ttf");
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, 25);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
        return fontConfig;
    }

    public TextGraphics getTextGraphics(TerminalPosition topCorner, TerminalSize size){
        return textGraphics.newTextGraphics(topCorner, size);
    }

    public TextGraphics getPercentageOfScreenVertical(double percentage, int offset_x, boolean inverted){
        if(!inverted){
            return textGraphics.newTextGraphics(new TerminalPosition(offset_x, (int) (percentage*terminalSize.getRows()))
                    , new TerminalSize(terminalSize.getColumns(),(int) (percentage*terminalSize.getRows())));
        }
        return textGraphics.newTextGraphics(new TerminalPosition(offset_x, (int) (terminalSize.getRows() - percentage*terminalSize.getRows()))
                , new TerminalSize(terminalSize.getColumns(),(int) (terminalSize.getRows() - percentage*terminalSize.getRows())));
    }


    public TerminalSize getTerminalSize() {
        return terminalSize;
    }

    public void refresh() throws IOException {
        screen.refresh();
    }
}
