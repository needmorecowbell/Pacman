package copAckMusPacman;

import java.awt.Color;
import java.awt.Graphics;
//small method to paint over the overlap of the corridors, works perfectly for now
public class PaintOver {

	private int x, y, width, height;
	
	public PaintOver(int x, int y, int width, int height){
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void paint(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(x, y, width, height);
	}
}
