package AaronCoplanPacman;

import javax.swing.*;
import java.awt.*;

public class VerticalWalls {

	int width = 10;
	int height = (160 * 3);
	int x = 0;
	int y = 0;
	
	
	public VerticalWalls(int X, int Y){
		
		x = X;
		y = Y;
	}
	
	public void paint(Graphics g){
		
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
		
	}
	
}
