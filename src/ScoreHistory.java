import java.io.*;
import java.util.ArrayList;

public abstract class ScoreHistory {
    private static final String SCORE_HISTORY_FILE = "score_history.txt";

    public static ArrayList<Integer> loadScoreHistory() {
        ArrayList<Integer> scoreHistory = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(SCORE_HISTORY_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                scoreHistory.add(Integer.parseInt(line));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scoreHistory;
    }

    public static void saveScore(int score) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(SCORE_HISTORY_FILE, true));
            writer.write(Integer.toString(score));
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
