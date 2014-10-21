package AaronCoplanPacman;


import java.awt.*;

import javax.swing.*;

public class TestGhost {

	int x = 30;
	int y = 30;
	int width = 30;
	int height = 30;
	Color c;
	
	
	public TestGhost(int x, int y, Color c){
		
		this.x = x;
		this.y = y;
		this.c = c;
	}
	
	public void paint (Graphics g){
		//g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(c);
		g.fillOval(x, y, width, height);
	}
	
	public void move(boolean moving, int xplusminus, int yplusminus){
		if (moving == true){
			
			switch (xplusminus)
			{
			case 1: switch(yplusminus){
			case 1: x+=2;
			y-=2;
				break;
			case -1: x+=2;
			y+=2;
				break;
			}
				break;
			case -1: switch(yplusminus){
			case 1: x-=2;
			y-=2;
				break;
			case -1: x-=2;
			y+=2;
				break;
			}
				break;
			}
			
		}
	}
	
	public int getx(){
		
		return x;
	}
	
	public int gety(){
		
		return y;
	}
	
	  public boolean collision(BallTest r)
	    {
	        double distance = Math.sqrt(((this.getx() - r.getx()) * (this.getx() - r.getx()))+ ((this.gety() - r.gety()) * (this.gety() - r.gety())));
	     
	        if (distance <= (this.height / 2) + (r.height / 2))
	        {
	            //balls have collided
	        	System.out.println("Collision!!!!");
	            return false;
	        }
	        else
	            return true;

	    }
}
