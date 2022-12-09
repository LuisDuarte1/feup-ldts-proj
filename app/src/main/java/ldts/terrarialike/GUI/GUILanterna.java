package ldts.terrarialike.GUI;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.awt.*;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.DefaultWindowManager;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Window;
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

    private MultiWindowTextGUI multiWindowTextGUI;

    public GUILanterna(int width, int height, String windowName){
        terminalSize = new TerminalSize(width, height);
        try{
            DefaultTerminalFactory  dFactory= new DefaultTerminalFactory();
            dFactory.setTerminalEmulatorFontConfiguration(loadSquareFont());
            dFactory.setTerminalEmulatorTitle(windowName);
            //seems like inital terminal size is more of a suggestion to lanterna than a requirement so we must check
            //final terminalSize when terminal is create
            dFactory.setInitialTerminalSize(terminalSize);
            terminal = dFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
            textGraphics = screen.newTextGraphics();
            terminalSize = textGraphics.getSize();
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
        terminalSize = textGraphics.getSize();
        if(!inverted){
            return textGraphics.newTextGraphics(new TerminalPosition(offset_x, 0)
                    , new TerminalSize(terminalSize.getColumns(),(int) (percentage*terminalSize.getRows())));
        }
        return textGraphics.newTextGraphics(new TerminalPosition(offset_x, (int) (terminalSize.getRows() - (1-percentage)*terminalSize.getRows() + 1))
                , new TerminalSize(terminalSize.getColumns(),(int) (terminalSize.getRows() - percentage*terminalSize.getRows() -1)));
    }


    public TerminalSize getTerminalSize() {
        return terminalSize;
    }

    public void refresh() throws IOException {
        if(multiWindowTextGUI == null){
            screen.refresh();
        } else{
            multiWindowTextGUI.updateScreen();
            multiWindowTextGUI.processInput();
        }
    }

    public void removeCurrentWindowsStack(){
        if(multiWindowTextGUI != null){
            //first erase all previous windows because they are not needed and, maybe they must be released to not cause conflicts
            for(Window old_window : multiWindowTextGUI.getWindows()){
                multiWindowTextGUI.removeWindow(old_window);
            }
        }
        multiWindowTextGUI.getScreen().clear();
        multiWindowTextGUI = null;
    }

    public void removeCurrentWindowFromStack(){
        multiWindowTextGUI.removeWindow(multiWindowTextGUI.getActiveWindow());
    }

    public void removeWindowFromStack(Window window){
        multiWindowTextGUI.removeWindow(window);
    }

    public void addWindowToStack(Window window){
        if(multiWindowTextGUI == null){
            multiWindowTextGUI = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
        }
        multiWindowTextGUI.addWindow(window);
    }
}
