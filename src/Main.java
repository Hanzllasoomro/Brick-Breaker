import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Brick Breaker");
        frame.setSize(700,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        GamePlay gamePlay = new GamePlay();
        frame.add(gamePlay);
    }
}