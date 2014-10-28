package AaronCoplanPacman;

import java.awt.Color;
import java.awt.Graphics;

public class Corridor {
	int width = 0;
	int height;
	int wallwidth = 6;//how wide each wall is
	int cx; int cy;
	int x, y;
	
	public Corridor(int cx, int cy, int width, int height)
	{
		this.width = width;
		this.height = height;
		this.cx = cx;
		this.cy = cy;
		x = cx - (width / 2);//find x coordinates from center x coordinates
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
	
	public int verticalcorridorTop(){
		
		return cy - (height / 2) - 16;
	}
	
	public int horizontalcorridorTop(){
		
		return cy;
	}
	
	public int horizontalcorridorBottom(){
		
		return (cy + (height / 2));
	}
	
	public int verticalcorridorBottom(){
		
		return (cy + (height / 2) + 16);
	}
	
	public int corridorLeft(){
		
		return (cx - (width / 2) - 16);
	}
	
	public int corridorRight(){
		
		return (cx + (width / 2) + 16);
	}
}
