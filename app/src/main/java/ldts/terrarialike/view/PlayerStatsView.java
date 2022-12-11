package ldts.terrarialike.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.Inventory;
import ldts.terrarialike.model.Player;

public class PlayerStatsView implements ElementView{

    private static final double LOGS_PERCENTAGE = 0.60;

    private Player player;
    private PlayerLogsView playerLogsView;

    public PlayerStatsView(Player player) {
        this.player = player;
        this.playerLogsView = new PlayerLogsView(player.getPlayerLogs());
    }
    @Override
    public void draw(TextGraphics graphics) {
        try {
            TerminalSize statsSize = graphics.getSize();
            TextGraphics logGraphics = graphics.newTextGraphics(
                    new TerminalPosition((int) (statsSize.getColumns()*(1-LOGS_PERCENTAGE)), 0),
                    new TerminalSize((int) (statsSize.getColumns()*LOGS_PERCENTAGE),statsSize.getRows()));

            playerLogsView.draw(logGraphics);

            graphics.fillRectangle(new TerminalPosition(0,0),
                    new TerminalSize((int) (statsSize.getColumns()*(1-LOGS_PERCENTAGE) -1), statsSize.getRows()), ' ');
            graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
            graphics.putString(0, 0, "Player Stats");
            graphics.drawLine(new TerminalPosition(0, 1), new TerminalPosition(5, 1), '-');
            graphics.putString(0, 2, "Hp: " + Integer.toString(player.getHp()));
            graphics.putString(0,4, "Inventory: "
                    + Integer.toString(player.getInventory().getSize()) + "/30");
        } catch ( Exception e) {
            e.printStackTrace();
        }


    }




    
}
