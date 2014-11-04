package copAckMusPacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pill {

	private int x = 100, y = 100;//sets default pos. on pill
	private int width = 20, height = 20;//dimensions of sprite
	int cx = 0, cy = 0;//delta values
	private BufferedImage sprite;
	
	public Pill(int x, int y){
		
		this.x = x;
		this.y = y;
		
		try {
			sprite = ImageIO.read(this.getClass().getClassLoader().getResource("res/pill.png"));//reads sprite img from resource file
			height= sprite.getHeight();//sets using image dimensions
			width= sprite.getWidth();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g){
		
		g.drawImage(sprite, x, y,null);//draws sprite to screen
	}
	
	
	public int getx(){
		
		return x;
	}
	
	public int gety(){
		
		return y;
	}
	
	public int getcx(){
		
		cx = x + (height / 2);
		return cx;
	}
	
	public int getcy(){
		
		cy = y + (height / 2);
		return cy;
	}

	public boolean pillEaten(Pacman r){
        double distance = Math.sqrt(((this.getcx() - r.getcx()) * (this.getcx() - r.getcx()))+ ((this.getcy() - r.getcy()) * (this.getcy() - r.getcy())));//uses distance formula
     
        if (distance <= (this.height / 2) + (r.height / 2))//finds center of ball and pacman, sees if sum is less than distance
        {
            //balls have collided
        	System.out.println("Collision with pill!");
            return false;
        }
        else
            return true;

    }
}
