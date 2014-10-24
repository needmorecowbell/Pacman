package AaronCoplanPacman;

import java.awt.Color;
import java.awt.Graphics;

public class Dots {

	private int x = 100,y = 100;
	private int width = 10, height = 10;
	
	public Dots(int x, int y){
		
		this.x = x;
		this.y = y;
	}
	
public void paint(Graphics g){
		
		g.setColor(Color.white);
		g.fillOval(x, y, width, height);
	}

public int getx(){
	return x;
}

public int gety(){
	return y;
}

public boolean dotEaten(BallTest r){
    double distance = Math.sqrt(((this.getx() - r.getx()) * (this.getx() - r.getx()))+ ((this.gety() - r.gety()) * (this.gety() - r.gety())));
 
    if (distance <= (this.height / 2) + (r.height / 2))
    {
        //balls have collided
    	System.out.println("Collision with dot!");
        return false;
    }
    else
        return true;

}
}
