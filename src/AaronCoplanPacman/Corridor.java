package AaronCoplanPacman;

import java.awt.Color;
import java.awt.Graphics;

public class Corridor {
	int width;
	int height;
	int wallwidth = 10;
	int x; int y;
	
	public Corridor(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(x,y,wallwidth,height);
		g.fillRect(x+width,y,wallwidth,height);
		g.fillRect(x,y,width,wallwidth);
		g.fillRect(x,height+y,width+wallwidth,wallwidth);
		
	}
}
