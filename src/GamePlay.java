
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements KeyListener, ActionListener {

    GamePlay(){
        addKeyListener( this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);

        timer = new Timer( delay ,this);
        timer.start();
        mapGenerator = new MapGenerator(3,7);

    }
    public void paint(Graphics g){
        super.paint(g);

        g.setColor(Color.BLACK);
        g.fillRect(1,1,683,590);

        g.setColor(Color.YELLOW);
        g.fillRect(0,0,692,3);
        g.fillRect(0,3,3,591);
        g.fillRect(682,3,3,591);

        g.setColor(Color.green);
        g.fillRect(playerX , 550 , 100 ,8);

        mapGenerator.draw((Graphics2D) g);

        g.setColor(Color.RED);
        g.fillOval(ballposX , ballposY , 20,20);

        g.setColor(Color.green);
        g.setFont(new Font("serif", Font.BOLD,20));
        g.drawString("Score :" + score ,550,30);

        if(ballposY >= 570) {
            if (!scoreSaved) {
                ScoreHistory.saveScore(score);
                scoreSaved = true;
            }
                play = false;
                ballXdir = 0;
                ballYdir = 0;


                g.setColor(Color.CYAN);
                g.setFont(new Font("Arial", Font.BOLD, 40));
                g.drawString("GAME OVER", 200, 300);
                g.drawString("Score :" + score, 230, 335);
                g.setFont(new Font("Arial", Font.BOLD, 27));
                g.drawString("Press ENTER to restart ", 175, 370);

        }
        if(totalBrick <=0) {
            if (!scoreSaved) {

                ScoreHistory.saveScore(score);
                scoreSaved = true;
            }
                play = false;
                g.setColor(Color.CYAN);
                g.setFont(new Font("Arial", Font.BOLD, 40));
                g.drawString("U WON", 200, 300);
                g.drawString("Score :" + score, 230, 335);
                g.setFont(new Font("Arial", Font.BOLD, 27));
                g.drawString("Press ENTER to restart ", 175, 370);
        }

    }
    public void moveLeft(){
        play =true;
        playerX-=20;
    }

    public void moveRight(){
        play = true;
        playerX+=20;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(play){

            ballposX += ballXdir;
            ballposY += ballYdir;

            if(ballposX <= 0 )
                ballXdir = -ballXdir;

            if ( ballposX >= 670 )
                ballXdir = -ballXdir;


            if(ballposY <= 0)
                ballYdir =- ballYdir;

            Rectangle ballRect = new Rectangle(ballposX,ballposY ,20,20);
            Rectangle padelRect = new Rectangle(playerX , 550, 100 ,8);

            if(ballRect.intersects(padelRect))
                ballYdir =- ballYdir;

            A:for(int i = 0 ; i<mapGenerator.map.length ; i++)
                for(int j =0 ; j<mapGenerator.map[0].length ; j++)
                    if(mapGenerator.map[i][j] > 0){

                        int width = mapGenerator.brickWidth;
                        int height = mapGenerator.brickHeight;
                        int brickXpos = 80 +j*width;
                        int brickYpos = 50 +i*height;

                        Rectangle brickRect = new Rectangle(brickXpos , brickYpos ,width , height);

                        if(ballRect.intersects(brickRect)) {
                            mapGenerator.setBrick(0, i, j);
                            totalBrick--;
                            score++;

                            if(ballposX+19<=brickXpos || ballposX +1 >= brickXpos+width)
                                ballXdir =- ballXdir;
                            else ballYdir=-ballYdir;

                            break A;
                        }
                    }

        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_LEFT)
            if(playerX<=0)
                playerX=0;
        else moveLeft();
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            if (playerX>=600)
                playerX = 600;
        else   moveRight();

        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            if(!play){
                score = 0;
                totalBrick = 21;
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                playerX = 320;

                mapGenerator = new MapGenerator(3,7);

            }
        }

        repaint();

    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    private boolean play = false;
    private int totalBrick = 21;
    private Timer timer;
    private int delay =-80;
    private int ballposX =120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private int playerX = 350;
    private int score = 0;
    private MapGenerator mapGenerator;
    private boolean scoreSaved = false;

}