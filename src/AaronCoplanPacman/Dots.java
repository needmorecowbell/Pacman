package AaronCoplanPacman;

import java.awt.Color;
import java.awt.Graphics;

public class Dots {

	private int x = 100,y = 100;
	private int centerx = 0, centery = 0;
	private int width = 10, height = 10;
	
	public Dots(int x, int y){
		
		this.x = x;
		this.y = y;
	}
	
public void paint(Graphics g){
		
		g.setColor(Color.white);
		g.fillOval(x, y, width, height);
	}

public int getcx(){
	centerx = x + (height / 2);
	return centerx;
}

public int getcy(){
	centery = y + (height / 2);
	return centery;
}

public boolean dotEaten(Pacman r){
    double distance = Math.sqrt(((this.getcx() - r.getcx()) * (this.getcx() - r.getcx()))+ ((this.getcy() - r.getcy()) * (this.getcy() - r.getcy())));
 
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
