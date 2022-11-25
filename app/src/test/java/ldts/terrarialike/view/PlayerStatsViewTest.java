package ldts.terrarialike.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import ldts.terrarialike.GUI.GUILanterna;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.Player;
import ldts.terrarialike.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlayerStatsViewTest {

    private PlayerStatsView playerStatsView;
    private Player player;
    private TextGraphics textGraphics;

    @BeforeEach
    public void setUp() {
        try {
            player = new Player(new Position(0, 0), 100);
            playerStatsView = new PlayerStatsView(player);
            textGraphics = Mockito.mock(TextGraphics.class);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
    }


    @Test

    public void testDraw() {
        playerStatsView.draw(textGraphics);
        Mockito.verify(textGraphics).putString(0, 0, "Player Stats");
        Mockito.verify(textGraphics).drawLine(new TerminalPosition(0,1), new TerminalPosition(5,1), '-');
        Mockito.verify(textGraphics).putString(0, 2, "Hp: " + "100");
        Mockito.verify(textGraphics).putString(0, 4, "Inventory: " + "30");
    }












}
