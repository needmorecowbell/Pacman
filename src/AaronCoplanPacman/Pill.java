package AaronCoplanPacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pill {

	private int x = 100, y = 100;
	private int width = 20, height = 20;
	int cx = 0, cy = 0;
	private BufferedImage img;
	
	public Pill(int x, int y){
		
		this.x = x;
		this.y = y;
		
		try {
			img = ImageIO.read(this.getClass().getClassLoader().getResource("res/pill.png"));
			height= img.getHeight();
			width= img.getWidth();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g){
		
		g.drawImage(img, x, y,null);
	}
	
	public void disappear(){
		
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
        double distance = Math.sqrt(((this.getcx() - r.getcx()) * (this.getcx() - r.getcx()))+ ((this.getcy() - r.getcy()) * (this.getcy() - r.getcy())));
     
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
