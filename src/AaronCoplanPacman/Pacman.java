 package AaronCoplanPacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pacman {
	
	int width = 30, height = 30;
	int x = 220, y = 24;
	private int cx = 0, cy = 0;
	
	BufferedImage pacmanRight,pacmanLeft,pacmanDown,pacmanUp, sprite;
	public Pacman(){
		try {
			pacmanRight=ImageIO.read(this.getClass().getClassLoader().getResource("res/pacmanRight.png"));
			sprite = pacmanRight;
			pacmanLeft= ImageIO.read(this.getClass().getClassLoader().getResource("res/pacmanLeft.png"));
			pacmanUp= ImageIO.read(this.getClass().getClassLoader().getResource("res/pacmanUp.png"));
			pacmanDown = ImageIO.read(this.getClass().getClassLoader().getResource("res/pacmanDown.png"));
			
			height= pacmanRight.getHeight();
			width= pacmanRight.getWidth();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void spriteDirection(char dir){
		
		switch(dir)
		{
		case 'l': sprite = pacmanLeft;
		break;
		case 'r': sprite = pacmanRight;
		break;
		case 'd': sprite = pacmanDown;
		break;
		case 'u': sprite = pacmanUp;
		break;
		default: sprite = pacmanRight;
		}
	}
	
	public void paint(Graphics g){
		g.drawImage(sprite, x, y, null);
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
		
		if((getcy() > a.horizontalcorridorTop()) && (getcy() + 15 < a.horizontalcorridorBottom()) && (getcx() > a.corridorLeft() + 40) && (getcx() < a.corridorRight() - 40)){
			return 1;
		}
		else{
			return 0;
		}
	}
	
	public int verticalcorridorClash(Corridor a){
		
		if((getcy() > a.verticalcorridorTop() +40) && (getcy() + 15 < a.verticalcorridorBottom() - 40) && (getcx() > a.corridorLeft()) && (getcx() < a.corridorRight())){
			return 1;
		}
		else{
			return 0;
		}
	}
	
	public int square1(Corridor hor, Corridor vert){
		
		if (getcy() < hor.horizontalcorridorBottom() && getcx() < vert.corridorRight()){
			
			System.out.println("IN A FREAKING SQUARE");
			return 1;
		}
		else{
			return 0;
		}
	}
	

}
