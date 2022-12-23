package ldts.terrarialike.view.statsViews;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import ldts.terrarialike.model.PlayerLogs;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlayerLogsViewTest {

    @Test
    void playerLogsTest(){
        PlayerLogs playerLogs = Mockito.mock(PlayerLogs.class);
        Mockito.when(playerLogs.getLogString(0)).thenReturn("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Mockito.when(playerLogs.getLogString(1)).thenReturn("Among us sussy baka");
        Mockito.when(playerLogs.getLogString(2)).thenReturn("bing chilling");
        Mockito.when(playerLogs.getLogString(3)).thenReturn("");
        Mockito.when(playerLogs.getLogString(4)).thenReturn("");



        TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
        Mockito.when(textGraphics.getSize()).thenReturn(new TerminalSize(30,5));

        PlayerLogsView playerLogsView = new PlayerLogsView(playerLogs);

        playerLogsView.draw(textGraphics);

        Mockito.verify(textGraphics).fill(' ');
        Mockito.verify(textGraphics).setBackgroundColor(TextColor.ANSI.BLACK);
        Mockito.verify(textGraphics).setForegroundColor(TextColor.ANSI.WHITE);
        Mockito.verify(textGraphics).putString(0, 0, "aaaaaaaaaaaaaaaaaaaaaaaaaaa...");
        Mockito.verify(textGraphics).putString(0, 1, "Among us sussy baka");
        Mockito.verify(textGraphics).putString(0, 2, "bing chilling");
        Mockito.verify(textGraphics).putString(0, 3, "");
        Mockito.verify(textGraphics).putString(0, 4, "");




    }
}
