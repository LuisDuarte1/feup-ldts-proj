package ldts.terrarialike.model;


import java.util.ArrayList;
import java.util.List;

public class PlayerLogs {

    private static final int MAX_PLAYER_LOGS_ENTRIES=1000;

    private List<String> logList;


    public PlayerLogs(){
        logList = new ArrayList<>();
    }


    public List<String> getLogList() {
        return logList;
    }

    public void addLogString(String log){
        logList.add(0,log);
        //keep this at a maximum size to avoid memory leaks
        while(logList.size() > MAX_PLAYER_LOGS_ENTRIES){
            logList.remove(logList.size()-1);
        }
    }

    public String getLogString(int index){
        try{

            String logString = logList.get(index);
            return logString;
        } catch(IndexOutOfBoundsException e){
            return new String("");
        }

    }
}
