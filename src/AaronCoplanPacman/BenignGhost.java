package AaronCoplanPacman;

import java.awt.Color;
import java.awt.Graphics;

public class BenignGhost extends TestGhost {

	int x,y;
	int width=30;
	int height=30;
	Color c;
	int xvelo,yvelo;
	int  speed=2;
	
	public BenignGhost(){}
	
	public BenignGhost(int x, int y, Color c, int xvelo, int yvelo){
		
		this.x = x;
		this.y = y;
		this.c = c;
		this.xvelo=xvelo;
		this.yvelo=yvelo;
	}
	
	public BenignGhost(int x, int y, Color c, int xvelo, int yvelo,int speed){
		
		this.x = x;
		this.y = y;
		this.c = c;
		this.xvelo=xvelo;
		this.yvelo=yvelo;
		this.speed=speed;
	}

	
	public void paint (Graphics g){
		//g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(c);
		g.fillOval(x, y, width, height);
	}
	
	public void move(boolean moving, int xplusminus, int yplusminus){
		super.move(moving, xplusminus, yplusminus);	
	}
	public int getxvelo(){return xvelo;}
	public int getyvelo(){return yvelo;}
	public int getx(){return x;}	
	public int gety(){return y;}
	public void setxvelo(int xvelo){this.xvelo=xvelo;}
	public void setyvelo(int yvelo){this.yvelo=yvelo;}
	
	  public boolean collision(BallTest r){
	        double distance = Math.sqrt(((this.getx() - r.getx()) * (this.getx() - r.getx()))+ ((this.gety() - r.gety()) * (this.gety() - r.gety())));
	     
	        if (distance <= (this.height / 2) + (r.height / 2)){
	            //balls have collided
	        	//this.move(true, xvelo, yvelo);
	        	System.out.println("Collision!!!!");
	            return false;
	        }else{
	            return true;
	         }
	    }
}
