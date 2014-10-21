package AaronCoplanPacman;

import java.awt.Color;
import java.awt.Graphics;

public class BallTest {
	
	int width = 30;
	int height = 30;
	int x = ((160 * 3) / 2) - 30;
	int y = ((160 * 3) / 2) - 30;
	
	public BallTest(){
		
		
	}
	
	public void paint(Graphics g){
		//g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.orange);
		g.fillOval(x, y, width, height);
	}
	
	public int getx(){
		
		return x;
	}
	
	public int gety(){
		
		return y;
	}
	
	public void move(char dir, int posneg){
		
		
		switch(dir)
		{
		case 'x': switch(posneg){
		case 1: x++;
		break;
		case 0: x--;
		break;
		}
			break;
		case 'y': switch(posneg)
		{
		case 1: y--;
		break;
		case 0: y++;
		break;
		}
			break;
		case 'b': 
			break;
		}
		
		
	}
	
	public void moveballx(int changex){
		
		x += changex;
	}
	
	public void movebally(int changey){
	
		y += changey;
	}
}
