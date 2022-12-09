package ldts.terrarialike.view;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.Inventory;
import ldts.terrarialike.model.Player;

public class PlayerStatsView implements ElementView{

    private Player player;

    public PlayerStatsView(Player player) {
        this.player = player;
    }
    @Override
    public void draw(TextGraphics graphics) {
        try {
            graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
            graphics.putString(0, 0, "Player Stats");
            graphics.drawLine(new TerminalPosition(0, 1), new TerminalPosition(5, 1), '-');
            graphics.putString(0, 2, "Hp: " + Integer.toString(player.getHp()));
            graphics.putString(0,4, "Inventory: " + "30");
        } catch ( Exception e) {
            e.printStackTrace();
        }


    }




    
}
