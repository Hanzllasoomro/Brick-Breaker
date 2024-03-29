import java.awt.*;

public class MapGenerator {
    MapGenerator(int row , int column){

        map = new int[row][column];
        for (int i =0; i<row ; i++){
            for(int j =0 ; j<column ; j++)
                map[i][j]=1;
        }

        brickWidth = 540/column;
        brickHeight = 150/row;
    }
    public void setBrick(int value , int r , int c){
        map[r][c] = value;
    }
    public void draw(Graphics2D g){

        for(int i = 0 ; i< map.length ; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    g.setColor(Color.WHITE);
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);

                    g.setColor(Color.BLACK);
                    g.setStroke(new BasicStroke(2));
                    g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }
    public int[][] map;
    public int brickWidth;
    public int brickHeight;
}
