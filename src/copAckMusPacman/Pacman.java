package copAckMusPacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pacman {
	
	int width = 30, height = 30;//Radius = 15
	int x = 220, y = 24;//positions pacman in corridor at start
	private int cx = 0, cy = 0;
	
	BufferedImage pacmanRight,pacmanLeft,pacmanDown,pacmanUp, sprite;
	public Pacman(){
		
		try {
			//loads images for each direction of pacman
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
		case 1: x+=1;
		break;
		case 0: x-=1;
		break;
		}
			break;
		case 'y': switch(posneg)
		{
		case 1: y-=1;
		break;
		case 0: y+=1;
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
	//40 is equal to width of corridor, so whenever 40 is used, it is adjusting for that
	public String horizontalcorridorClash(Corridor a){
		//checks to see if pacman is within bounds of corridor entered (a)
		if((getcy() > a.horizontalcorridorTop()) && (getcy() + 15 < a.horizontalcorridorBottom()) && (getcx() > a.horizontalcorridorLeft() + 20) && (getcx() < a.horizontalcorridorRight() - 40)){
			
			//System.out.println("it is in horz");
			return "horizontal";
		}
		else{
			return "";
		}
	}
	
	public String verticalcorridorClash(Corridor a){
		//checks to see if pacman is within bounds of corridor entered (a)
		if((getcy() > a.verticalcorridorTop() +40) && (getcy() + 15 < a.verticalcorridorBottom() -20) && (getcx() > a.verticalcorridorLeft()) && (getcx() < a.verticalcorridorRight())){
			return "vertical";
		}
		else{
			return "";
		}
	}
	
	public String square1(Corridor hor, Corridor vert){
		//checks to see if pacman is within bounds of square entered (crosssection between 2 corridors)
		if (getcy() < hor.horizontalcorridorBottom()-30 && getcx() < vert.verticalcorridorRight()-30){
			System.out.println("in upper left square");
			return "upperleft";
		}else{
			return "";
		}
	}
	public String square2(Corridor hor, Corridor vert){ 
		if(getcy() > hor.horizontalcorridorTop() && getcx() < vert.verticalcorridorRight()-30){
			System.out.println("in bottom left square!!!");
			return "bottomleft";
		}else{
			return "";
		}
	}
	public String square3(Corridor hor, Corridor vert){
		
		if(getcy() > hor.horizontalcorridorTop() && getcx() > vert.verticalcorridorLeft() + 30){
			System.out.println("in bottom right corner");
			return "bottomright";
		}else{
			return "";
		}
	}
	public String square4(Corridor hor, Corridor vert){
		
		if(getcy() < hor.horizontalcorridorBottom() && getcx() > vert.verticalcorridorLeft() + 30){
			System.out.println("in upper right corner");
			return "upperright";
		}else{
			return "";
		}
	}
	//need to implement a method to deal with all of this at 1 time, then game class will call that method
	//before determining which directional keys are able to be used.

}
