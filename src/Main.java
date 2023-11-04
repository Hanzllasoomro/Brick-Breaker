import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main extends JFrame {
        Main(){
                SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                setTitle("Brick Breaking");
                setSize(700,600);
                setLocationRelativeTo(null);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setResizable(false);
                setVisible(true);

                displayWelcomeScreen();

                GamePlay gamePlay = new GamePlay();
                add(gamePlay);

            }
        });

    }
    void displayWelcomeScreen(){
        JWindow window = new JWindow(this);
        window.setSize(700,600);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setBackground(Color.WHITE);
        window.add(panel);

        String imagePath = "C:\\Users\\HP\\Downloads\\intro.jpeg";
        JLabel label = new JLabel(new ImageIcon(imagePath));
        panel.add(label);

        JProgressBar pb = new JProgressBar(0,100);
        pb.setForeground(Color.CYAN);
        pb.setStringPainted(true);
        window.add(BorderLayout.PAGE_END,pb);
        window.revalidate();

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = pb.getValue();
                if(x <= 50)
                pb.setString(x+"%\tWait for a moment your game is loading");
                if(x > 50  && x <= 75)
                pb.setString(x+"%\tplease wait!! ur about to enter the game");
                if(x == 100) {
                    window.dispose();
                    Main.this.setVisible(true);
                    timer.stop();
                } else {
                    pb.setValue(x+2);
                }
            }
        });
        timer.start();
    }

    public void displayScoreHistory() {
        ArrayList<Integer> scoreHistory = ScoreHistory.loadScoreHistory();
        System.out.println("Score History:");
        for (int i = 0; i < scoreHistory.size(); i++) {
            System.out.println("Game " + (i + 1) + ": " + scoreHistory.get(i));
        }
    }


    Timer timer;
}