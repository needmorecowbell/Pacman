package copAckMusPacman;

import java.awt.*;

public class VerticalWalls {

	int width = 10;//sets block dimensions
	int height = (160 * Game.SCALE);
	int x = 0;//sets position in game
	int y = 0;
	
	
	public VerticalWalls(int x, int y){
		
		this.x = x;
		this.y = y;
	}
	
	public void paint(Graphics g){
		
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);//draws a black block at x,y
		
	}
	
}
