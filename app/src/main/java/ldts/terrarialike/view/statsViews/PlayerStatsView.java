package ldts.terrarialike.view.statsViews;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import ldts.terrarialike.model.Player;

public class PlayerStatsView implements ElementView{


    private final Player player;
    private final Integer seed;

    public PlayerStatsView(Player player, Integer seed) {
        this.player = player;
        this.seed = seed;
    }
    @Override
    public void draw(TextGraphics graphics) {
        try {
            TerminalSize statsSize = graphics.getSize();
            graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
            graphics.putString(0, 0, "Player Stats");
            graphics.drawLine(new TerminalPosition(0, 1), new TerminalPosition(5, 1), '-');
            graphics.putString(0, 2, "Hp: " + Integer.toString(player.getHp()));
            graphics.putString(0,3, String.format("Pos X: %d Y:%d", player.getPosition().getX()
                    , player.getPosition().getY()));
            graphics.putString(0,4,String.format("Inventory: %d / %d",player.getInventory().getSize(),player.getInventory().getMaxSize()));
            graphics.putString(0,5,String.format("Seed: %d",seed));

        } catch ( Exception e) {
            e.printStackTrace();
        }


    }




    
}
