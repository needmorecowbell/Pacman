package AaronCoplanPacman;

import java.awt.Color;
import java.awt.Graphics;

public class BallTest {
	
	int width = 30, height = 30;
	int x = ((160 * 3) / 2) - 30, y = ((160 * 3) / 2) - 30;
	private int cx = 0, cy = 0;
	
	public BallTest(){}
	
	public void paint(Graphics g){
		g.setColor(Color.orange);
		g.fillOval(x, y, width, height);
	}
	
	public int getx(){
		
		return x;
	}
	
	public int gety(){
		
		return y;
	}
	
	public int getcy(){
		
		cy = y + (height / 2);
		return cy;
	}
	
	public int getcx(){
		
		cx = x + (height / 2);
		return cx;
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
