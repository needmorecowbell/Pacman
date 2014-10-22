package AaronCoplanPacman;

import javax.swing.*;

import java.awt.*;

public class Pill {

	private int x = 100, y = 100;
	private int width = 20, height = 20;
	
	public Pill(int x, int y){
		
		this.x = x;
		this.y = y;
	}
	
	public void paint(Graphics g){
		
		g.setColor(Color.white);
		g.fillOval(x, y, width, height);
	}
	
	public void disappear(){
		
	}
	
	public int getx(){
		
		return x;
	}
	
	public int gety(){
		
		return y;
	}
	

	public boolean pillEaten(BallTest r){
        double distance = Math.sqrt(((this.getx() - r.getx()) * (this.getx() - r.getx()))+ ((this.gety() - r.gety()) * (this.gety() - r.gety())));
     
        if (distance <= (this.height / 2) + (r.height / 2))
        {
            //balls have collided
        	System.out.println("Collision with pill!");
            return false;
        }
        else
            return true;

    }
}
