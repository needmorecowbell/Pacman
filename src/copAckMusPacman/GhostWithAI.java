package copAckMusPacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GhostWithAI {

	//important data
	int repaintx = ((160 * 3) / 2) - 30, repainty = ((160 * 3) / 2) - 30; //after eaten this is its repaint location(center of map)
	int x,y;
	int width=30;
	int height=30;
	int  speed=2;
	String c;
	boolean benignmode = false; //whether or not the ghosts are edible
	String name = "";
	BufferedImage ghost,ghostBenign,ghostBenignInvert,sprite;
	
	boolean lmove = false, rmove = false, dmove = false, umove = false;
	
	//constructor naming ghost, giving it location, color, and speeds
	public GhostWithAI(String name, int x, int y, String c){
		
		this.name = name;
		this.x = x;
		this.y = y;
		this.c=c;
		
		try {
			
			switch(c){
			case "orange":
				ghost=ImageIO.read(this.getClass().getClassLoader().getResource("res/orangeGhost.png"));
				break;
			case "teal":
				ghost=ImageIO.read(this.getClass().getClassLoader().getResource("res/tealGhost.png"));
				break;
			case "pink":
				ghost=ImageIO.read(this.getClass().getClassLoader().getResource("res/pinkGhost.png"));
				break;
			case "red":
				ghost=ImageIO.read(this.getClass().getClassLoader().getResource("res/redGhost.png"));
				break;
			}
			

			
			sprite = ghost;
			
			
			ghostBenign=ImageIO.read(this.getClass().getClassLoader().getResource("res/benignGhostBlue.png"));
			ghostBenignInvert=ImageIO.read(this.getClass().getClassLoader().getResource("res/benignGhostInverted.png"));
	
			
			height= sprite.getHeight();
			width= sprite.getWidth();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.rmove = true;
		
	}
	//constructor for everything but name
	public GhostWithAI(int x, int y, String c, int speed){
		
		this.x = x;
		this.y = y;
		this.c = c;
		this.speed=speed;
		
		try {
			
			switch(c){
			case "orange":
				ghost=ImageIO.read(this.getClass().getClassLoader().getResource("res/orangeGhost.png"));
				break;
			case "teal":
				ghost=ImageIO.read(this.getClass().getClassLoader().getResource("res/tealGhost.png"));
				break;
			case "pink":
				ghost=ImageIO.read(this.getClass().getClassLoader().getResource("res/pinkGhost.png"));
				break;
			case "red":
				ghost=ImageIO.read(this.getClass().getClassLoader().getResource("res/redGhost.png"));
				break;
			}
			

			
			sprite = ghost;
			
			
			ghostBenign=ImageIO.read(this.getClass().getClassLoader().getResource("res/benignGhostBlue.png"));
			ghostBenignInvert=ImageIO.read(this.getClass().getClassLoader().getResource("res/benignGhostInverted.png"));
	
			
			height= sprite.getHeight();
			width= sprite.getWidth();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.rmove = true;
	}
	//turns benign mode (edibility) on or off
	public void setBenignMode(boolean benignmode){
		
		this.benignmode = benignmode;
		
		if(benignmode){
			sprite=ghostBenign;
		}
		else{
			sprite=ghost;
		}
	}
	//method to paint in the center of the map for after it is eaten
	public void paintincenter(){
		sprite=ghost;
		speed=2;
		x = repaintx;
		y = repainty;
	}
	//paint method sets the color and paints it in its x, y location
	public void paint (Graphics g){
		g.drawImage(sprite, x, y, null);
	}
	//accessor method to determine if in benign mode (edible or not)
	public boolean getBenignMode(){
		
		return benignmode;
	}
	//takes care of movement of the ghost
	public void move(boolean moving){
		
		 if (moving == true){
			if(benignmode){
				speed = 1;
			}
			else{
				speed = 2;
			}
			
		}
	}
	//accessor methods to get info such as name, xvelo, yvelo, x, and y
	public String getName(){return name;}
	public int getx(){return x;}	
	public int gety(){return y;}
	
	//collisions methods between pacman and the ghost
	public boolean collision(Pacman r, Ghost a){
	        double distance = Math.sqrt(((a.getx() - r.getx()) * (a.getx() - r.getx()))+ ((a.gety() - r.gety()) * (a.gety() - r.gety())));
	        
	        if (benignmode){
	        	 if (distance <= (a.height / 2) + (r.height / 2)){
	 	            //balls have collided
	 	        	System.out.println("Collision!!!!");
	 	        	try{
	 	        	//adds to your score, adds the ghost to the arraylist of dead ghosts, and removes it from screen
	 	        	Game.score += 100;	
	 	        	Game.deadghosts.add(a);
	 	        	Game.ghosts.remove(a);
	 	        	}
	 	        	catch (IndexOutOfBoundsException e){
	 	        		System.out.println("Index out of bounds error");
	 	        	}
	 	            return true;
	        	
	        	}
	        	return true;
	        }
	        else{
	        if (distance <= (this.height / 2) + (r.height / 2)){
	            //balls have collided
	        	System.out.println("Collision!!!!");
	            return false;
	        }else{
	            return true;
	         }
	        }
	    }
	public void flash(int timer) {
		if(timer%20==0){
			sprite=ghostBenignInvert;
		}else if(timer%5==0){
			sprite=ghostBenign;
		}
		
	}
}
