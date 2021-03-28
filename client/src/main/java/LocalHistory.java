import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class LocalHistory {
    private static final int LAST_MSG = 100;

    private static Path getHistoryPath(String login) {
        Path historyPath = Paths.get("history", "history" + login + ".txt");

        if (Files.notExists(historyPath.getParent())) {
            try {
                Files.createDirectories(historyPath.getParent());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return historyPath;
    }
    public static void writeMsg(String login,String msg){
        try {
            Files.write(getHistoryPath(login),msg.getBytes(),StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getLastMsgInHistory(String login){
        Path historyFile =getHistoryPath(login);
        if (Files.notExists(historyFile)) {
            return "";
        }
        try {
            List<String> msg =Files.readAllLines(historyFile, StandardCharsets.UTF_8);
            return getLastMsg(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    private static String getLastMsg(List<String> msg){
        StringBuilder result =new StringBuilder();
        int startPosition =0;
        if (msg.size()>LAST_MSG){
            startPosition=msg.size()-LAST_MSG;
        }
        for (int i = startPosition; i <msg.size() ; i++) {
            result.append(msg.get(i)).append(System.lineSeparator());
        }
        return result.toString();
    }

}
