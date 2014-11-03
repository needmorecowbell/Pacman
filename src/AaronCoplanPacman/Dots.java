package AaronCoplanPacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Dots {

	//instance data (including image)
	private int x = 100,y = 100;
	private int centerx = 0, centery = 0;
	private int width = 10, height = 10;
	private BufferedImage img;
	
	//constructor sets location and uses try/catch to get image
	public Dots(int x, int y){
		
		this.x = x;
		this.y = y;
		 
		try {
			img = ImageIO.read(this.getClass().getClassLoader().getResource("res/dot.png"));
			height= img.getHeight();
			width= img.getWidth();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//paint method that draws the image at an x, y location	
public void paint(Graphics g){	
		g.drawImage(img,x,y,null);
	}
//accessor method to obtain center x location
public int getcx(){
	centerx = x + (height / 2);
	return centerx;
}
//accessor method to obtain center y location
public int getcy(){
	centery = y + (height / 2);
	return centery;
}
//collision method to determine if the dot is eaten by pacman
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
