package AaronCoplanPacman;

import javax.swing.*;
import java.awt.*;

public class HorizontalWalls {

	int width = (160 * 3);
	int height = 10;
	int x = 0;
	int y = 0;
	
	
	public HorizontalWalls(int X, int Y){
		
		x = X;
		y = Y;
	}
	
	public void paint(Graphics g){
		
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
		
	}
	
}
