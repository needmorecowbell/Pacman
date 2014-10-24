package AaronCoplanPacman;

import java.awt.Color;
import java.awt.Graphics;

public class Corridor {
	int width = 0;
	int height;
	int wallwidth = 5;
	int cx; int cy;
	int x, y;
	
	public Corridor(int cx, int cy, int width, int height)
	{
		this.width = width;
		this.height = height;
		this.cx = cx;
		this.cy = cy;
		x = cx - (width / 2);
		y = cy - (height / 2);
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
