package copAckMusPacman;

import java.awt.Color;
import java.awt.Graphics;

public class Corridor {
	
	//instance variables
	int width = 0;
	int height;
	int wallwidth = 6;//how wide each wall is
	int cx; int cy;
	int x, y;
	
	//constructor includes center locations for x and y, as well as width and height
	public Corridor(int cx, int cy, int width, int height)
	{
		this.width = width;
		this.height = height;
		this.cx = cx;
		this.cy = cy;
		x = cx - (width / 2);//find x coordinates from center x coordinates
		y = cy - (height / 2);
	}
	
	//paint method paints each of the four walls of the rectangle
	public void paint(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(x,y,wallwidth,height);
		g.fillRect(x+width,y,wallwidth,height);
		g.fillRect(x,y,width,wallwidth);
		g.fillRect(x,height+y,width+wallwidth,wallwidth);
		
	}
	
	//gets location of top of vertical corridor
	public int verticalcorridorTop(){
		
		return cy - (height / 2) - 16;
	}
	//gets location of top of horizontal corridor
	public int horizontalcorridorTop(){
		
		return cy;
	}
	//gets location of bottom of horizontal corridor
	public int horizontalcorridorBottom(){
		
		return (cy + (height / 2));
	}
	//gets location of bottom of vertical corridor
	public int verticalcorridorBottom(){
		
		return (cy + (height / 2) + 16);
	}
	//gets location of left of corridor
	public int corridorLeft(){
		
		return (cx - (width / 2) - 16);
	}
	//gets location of right of corridor
	public int corridorRight(){
		
		return (cx + (width / 2) + 16);
	}
}
