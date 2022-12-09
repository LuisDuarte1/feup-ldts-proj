package ldts.terrarialike.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import ldts.terrarialike.model.PlayerLogs;

public class PlayerLogsView implements ElementView{

    private PlayerLogs playerLogs;

    public PlayerLogsView(PlayerLogs playerLogs){
        this.playerLogs = playerLogs;
    }

    @Override
    public void draw(TextGraphics graphics) {
        //clear last logs
        graphics.fill(' ');
        TerminalSize drawableSize = graphics.getSize();
        for(int i = 0; i < drawableSize.getRows(); i++){
            String log_message = playerLogs.getLogString(i);
            //truncate message if necessary
            if(log_message.length() > drawableSize.getColumns()){
                log_message = log_message.substring(0,drawableSize.getColumns()-3) + "...";
            }
            graphics.setBackgroundColor(TextColor.ANSI.BLACK);
            graphics.setForegroundColor(TextColor.ANSI.WHITE);
            graphics.putString(0,i,log_message);
        }
    }
}
