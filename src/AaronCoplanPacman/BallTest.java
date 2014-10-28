package AaronCoplanPacman;

import java.awt.Color;
import java.awt.Graphics;

public class BallTest {
	
	int width = 30, height = 30;
	int x = 220, y = 24;
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
	
	public int horizontalcorridorClash(Corridor a){
		
		if((getcy() > a.horizontalcorridorTop()) && (getcy() + 15 < a.corridorBottom()) && (getcx() > a.corridorLeft()) && (getcx() < a.corridorRight())){
			return 1;
		}
		else{
			return 0;
		}
	}
	
	public int verticalcorridorClash(Corridor a){
		
		if((getcy() > a.verticalcorridorTop()) && (getcy() + 15 < a.corridorBottom()) && (getcx() > a.corridorLeft()) && (getcx() < a.corridorRight())){
			return 1;
		}
		else{
			return 0;
		}
	}

}
