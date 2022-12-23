package ldts.terrarialike.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerLogsTest {
    @Test
    void playerLogsTest(){
        PlayerLogs playerLogs = new PlayerLogs();
        playerLogs.addLogString("pog");
        Assertions.assertEquals("pog", playerLogs.getLogString(0));
        Assertions.assertEquals("", playerLogs.getLogString(1));
        Assertions.assertEquals("", playerLogs.getLogString(2));

    }

    @Test
    void playerLogsTestFull(){
        PlayerLogs playerLogs = new PlayerLogs();
        playerLogs.addLogString("pog");
        for(int i = 0; i < PlayerLogs.MAX_PLAYER_LOGS_ENTRIES ; i++) playerLogs.addLogString("a");

        Assertions.assertEquals("a", playerLogs.getLogString(PlayerLogs.MAX_PLAYER_LOGS_ENTRIES-1));
        Assertions.assertEquals("", playerLogs.getLogString(PlayerLogs.MAX_PLAYER_LOGS_ENTRIES));


    }

}
