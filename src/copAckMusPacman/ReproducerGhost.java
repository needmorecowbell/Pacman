package copAckMusPacman;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ReproducerGhost extends Ghost{


	
	int repaintx = ((160 * 3) / 2) - 30, repainty = ((160 * 3) / 2) - 30; //after eaten this is its repaint location(center of map)
	int x,y;
	int width=30;
	int height=30;
	int xvelo,yvelo;
	int  speed=2;
	boolean benignmode = false; //whether or not the ghosts are edible
	String name = "";
	BufferedImage ghost,ghostBenign,ghostBenignInvert,sprite;
	
	public ReproducerGhost(String name, int x, int y, String c, int xvelo, int yvelo){
		super(name, x,  y, c, xvelo,yvelo);
	}
	
	//constructor naming ghost, giving it location, color, and speeds
	public ReproducerGhost(String name, int x, int y, int xvelo, int yvelo){
		super(yvelo, yvelo, name, yvelo, yvelo, yvelo);
		this.name = name;
		this.x = x;
		this.y = y;

		this.xvelo=xvelo;
		this.yvelo=yvelo;
		
		try {
			ghost=ImageIO.read(this.getClass().getClassLoader().getResource("res/reproducerghost.png"));
			sprite = ghost;
			
			
			ghostBenign=ImageIO.read(this.getClass().getClassLoader().getResource("res/benignGhostBlue.png"));
			ghostBenignInvert=ImageIO.read(this.getClass().getClassLoader().getResource("res/benignGhostInverted.png"));
	
			
			height= sprite.getHeight();
			width= sprite.getWidth();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	//constructor for everything but name
	public ReproducerGhost(int x, int y, String c, int xvelo, int yvelo,int speed){
		super(speed, speed, c, speed, speed, speed);
		this.x = x;
		this.y = y;
		this.xvelo=xvelo;
		this.yvelo=yvelo;
		this.speed=speed;
		
		try {
			ghost=ImageIO.read(this.getClass().getClassLoader().getResource("res/reproducerghost.png"));			
			sprite = ghost;
			
			
			ghostBenign=ImageIO.read(this.getClass().getClassLoader().getResource("res/benignGhostBlue.png"));
			ghostBenignInvert=ImageIO.read(this.getClass().getClassLoader().getResource("res/benignGhostInverted.png"));
	
			
			height= sprite.getHeight();
			width= sprite.getWidth();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
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
	public void move(boolean moving, int xplusminus, int yplusminus){
		
		 if (moving == true){
			if(benignmode){
				speed = 1;
			}
			else{
				speed = 2;
			}
			switch (xplusminus){
			case 1: 
				switch(yplusminus){
					case 1:
						x+=speed;
						y-=speed;
						break;
					case -1: 
						x+=speed;
						y+=speed;
						break;
				}
				break;
			case -1: 
				switch(yplusminus){
					case 1: 
						x-=speed;
						y-=speed;
						break;
					case -1: 
						x-=speed;
						y+=speed;
						break;
				}
				break;
			}
			
			
			
		}
	}
	//accessor methods to get info such as name, xvelo, yvelo, x, and y
	public String getName(){return name;}
	public int getxvelo(){return xvelo;}
	public int getyvelo(){return yvelo;}
	public int getx(){return x;}	
	public int gety(){return y;}
	//method to change the x and y velocities
	public void setxvelo(int xvelo){this.xvelo=xvelo;}
	public void setyvelo(int yvelo){this.yvelo=yvelo;}
	
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
	            Game.ghosts.add(new Ghost("reproduced", 50,50, "pink",1,-1));
	            System.out.println("reproduced!");
	            return true;
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
