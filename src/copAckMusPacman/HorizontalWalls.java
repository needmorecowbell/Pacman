package copAckMusPacman;

import java.awt.*;

public class HorizontalWalls {

	int width = (160 * Game.SCALE);
	int height = 10;
	int x = 0;
	int y = 0;
	
	
	public HorizontalWalls(int x, int y){
		
		this.x = x;
		this.y = y;
	}
	
	public void paint(Graphics g){
		
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
		
	}
	
}
