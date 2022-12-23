package ldts.terrarialike.view.statsViews;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.exceptions.InvalidSizeException;
import ldts.terrarialike.model.Inventory;
import ldts.terrarialike.model.Player;
import ldts.terrarialike.model.Position;
import ldts.terrarialike.view.statsViews.PlayerStatsView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class PlayerStatsViewTest {

    private PlayerStatsView playerStatsView;
    private Player player;
    private TextGraphics textGraphics;

    @BeforeEach
    public void setUp()  {
        try {
            player = Mockito.mock(Player.class);
            Mockito.when(player.getPosition()).thenReturn(new Position(1,2));
            Mockito.when(player.getHp()).thenReturn(69);
            Inventory inventory = Mockito.mock(Inventory.class);
            Mockito.when(player.getInventory()).thenReturn(inventory);

            Mockito.when(inventory.getMaxSize()).thenReturn(30);
            Mockito.when(inventory.getSize()).thenReturn(5);

            playerStatsView = new PlayerStatsView(player,200);
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
        Mockito.verify(textGraphics).putString(0, 2, "Hp: " + "69");
        Mockito.verify(textGraphics).putString(0, 4, "Inventory: 5 / 30");
        Mockito.verify(textGraphics).putString(0, 3, "Pos X: 1 Y:2");
        Mockito.verify(textGraphics).putString(0, 5, "Seed: 200");
    }












}
