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
	
	
	boolean lmove = false, rmove = false, dmove = false, umove = false, decidelr = true, decideud = true, notinsquare = true;
	
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
	public void decideMoveDirection(){
		
		/* this decision method can easily be changed such that the ghost can turn up 80% of the time if pac
		 * is above the ghost.  you would simply compare the y's and x's of the ghost and pacman and have it turn in the direction
		 * that brings it closer to pacman.
		 * You would only want it to turn towards pacman 70-80% of the time because otherwise they would catch you every time...
		 */
		if(upperleftSquare(Game.corridortop, Game.corridorleft).equals("upperleft")){
			
			//System.out.println("in upper left square");
			if(notinsquare){
				if(Math.random() < 0.49){
					lmove = false;
					umove = false;
					dmove = false;
					rmove = true;
					decideud = false;
					notinsquare = false;
				}else{
					rmove = false; 
					dmove = true;
					umove = false;
					lmove = false;
					decideud = false;
					notinsquare = false;
				}
			}
		}else if(bottomrightSquare(Game.corridorbottom, Game.corridorright).equals("bottomright")){
			
			//System.out.println("in bottom right square");
			if(notinsquare){
				if(Math.random() < 0.49){
					lmove = false;
					rmove = false;
					dmove = false;
					umove = true;
					decideud = false;
					notinsquare = false;
				}else{
					rmove = false;
					dmove = false;
					umove = false;
					lmove = true;
					decidelr = false;
					notinsquare = false;
				}
			}
		}else if(upperrightSquare(Game.corridortop, Game.corridorright).equals("upperright")){
			
			//System.out.println("in upper right square");
			if(notinsquare){
				if(Math.random() < 0.49){
					rmove = false;
					umove = false;
					lmove = false;
					dmove = true;
					decideud = false;
					notinsquare = false;
				}else{
					rmove = false;
					umove = false;
					dmove = false;
					lmove = true;
					decidelr = false;
					notinsquare = false;
				}
			}
		}else if(bottomleftSquare(Game.corridorbottom, Game.corridorleft).equals("bottomleft")){
			
			//System.out.println("in bottom left square");
			if(notinsquare){
				if(Math.random() < 0.49){
					lmove = false;
					rmove = false;
					dmove = false;
					umove = true;
					decideud = false;
					notinsquare = false;
				}else{
					rmove = true;
					dmove = false;
					umove = false;
					lmove = false;
					decidelr = false;
					notinsquare = false;
				}
			}
		}else if(horizontalcorridorClash(Game.corridormiddle).equals("horizontal")){
			//System.out.println("in horizontal middle corridor");
			umove = false;
			dmove = false;
			decideud = true;
			notinsquare = true;
			
			if(decidelr){
				if(Math.random() < 0.49){
					rmove = false;
					lmove = true;
					decidelr = false;
				}else{
					lmove = false;
					rmove = true;
					decidelr = false;
				}
			}
		}else if(horizontalcorridorClash(Game.corridortop).equals("horizontal")){
			//System.out.println("in horizontal top corridor");
			umove = false;
			dmove = false;
			decideud = true;
			notinsquare = true;
			
			if(decidelr){
				if(Math.random() < 0.49){
					rmove = false;
					lmove = true;
					decidelr = false;
				}else{
					lmove = false;
					rmove = true;
					decidelr = false;
				}
			}
		}else if(horizontalcorridorClash(Game.corridorbottom).equals("horizontal")){
			//System.out.println("in horizontal bottom corridor");
			umove = false;
			dmove = false;
			decideud = true;
			notinsquare = true;
			
			if(decidelr){
				if(Math.random() < 0.49){
					rmove = false;
					lmove = true;
					decidelr = false;
				}else{
					lmove = false;
					rmove = true;
					decidelr = false;
				}
			}
		}else if(verticalcorridorClash(Game.corridorright).equals("vertical")){
			//System.out.println("in vertical right corridor");
			lmove = false;
			rmove = false;
			decidelr = true;
			notinsquare = true;
			
			if(decideud){
				if(Math.random() < 0.49){
					umove = false;
					dmove = true;
					decideud = false;
				}else{
					dmove = false;
					umove = true;
					decideud = false;
				}
			}
		}else if(verticalcorridorClash(Game.corridorleft).equals("vertical")){
			//System.out.println("in vertical left corridor");
			lmove = false;
			rmove = false;
			decidelr = true;
			notinsquare = true;
			
			if(decideud){
				if(Math.random() < 0.49){
					umove = false;
					dmove = true;
					decideud = false;
				}else{
					dmove = false;
					umove = true;
					decideud = false;
				}
			}
		}
		
		
	}
	//takes care of movement of the ghost
	public void move(boolean moving){
		
		decideMoveDirection();
		
		 if (moving == true){
			if(benignmode){
				speed = 1;
			}
			else{
				speed = 2;
			}
			
			if(rmove){
				x+=speed;
			}else if(lmove){
				x-=speed;
			}else if(umove){
				y-=speed;
			}else if(dmove){
				y+=speed;
			}
		}
	}
	//accessor methods to get info such as name, xvelo, yvelo, x, and y
	public String getName(){return name;}
	public int getx(){return x;}	
	public int gety(){return y;}
	
	//collisions methods between pacman and the ghost
	public boolean collision(Pacman r, GhostWithAI a){
	        double distance = Math.sqrt(((a.getx() - r.getx()) * (a.getx() - r.getx()))+ ((a.gety() - r.gety()) * (a.gety() - r.gety())));
	        
	        if (benignmode){
	        	 if (distance <= (a.height / 2) + (r.height / 2)){
	 	            //balls have collided
	 	        	System.out.println("Collision!!!!");
	 	        	try{
	 	        	//adds to your score, adds the ghost to the arraylist of dead ghosts, and removes it from screen
	 	        	Game.score += 100;	
	 	        	Game.deadghosts.add(a);
	 	        	Game.aighosts.remove(a);
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
	
	public String horizontalcorridorClash(Corridor a){
		//checks to see if aighost is within bounds of corridor entered (a)
		if((getcy() > a.horizontalcorridorTop()) && (getcy() + 15 < a.horizontalcorridorBottom()) && (getcx() > a.horizontalcorridorLeft() + 40) && (getcx() < a.horizontalcorridorRight() - 60)){
			
			//System.out.println("it is in horz");
			return "horizontal";
		}
		else{
			return "";
		}
	}
	public String verticalcorridorClash(Corridor a){
		//checks to see if pacman is within bounds of corridor entered (a)
		if((getcy() > a.verticalcorridorTop() + 40) && (getcy() + 15 < a.verticalcorridorBottom() -20) && (getcx() > a.verticalcorridorLeft() + 19) && (getcx() < a.verticalcorridorRight() - 15)){
			return "vertical";
		}
		else{
			return "";
		}
	}
	public String bottomrightSquare(Corridor hor, Corridor vert){
			
		if(getcy() > hor.horizontalcorridorTop() && getcx() > vert.verticalcorridorLeft() + 18){
			//System.out.println("in bottom right corner");
			return "bottomright";
		}else{
			return "";
		}
	}
	public String upperrightSquare(Corridor hor, Corridor vert){
		
		if(getcy() < hor.horizontalcorridorBottom() - 15 && getcx() > vert.verticalcorridorLeft() + 19){
			//System.out.println("in upper right corner");
			return "upperright";
		}else{
			return "";
		}
	}
	public String bottomleftSquare(Corridor hor, Corridor vert){ 
		if(getcy() > hor.horizontalcorridorTop() && getcx() < vert.verticalcorridorRight() - 14){
			//System.out.println("in bottom left square!!!");
			return "bottomleft";
		}else{
			return "";
		}
	}
	public String upperleftSquare(Corridor hor, Corridor vert){
		//checks to see if pacman is within bounds of square entered (crosssection between 2 corridors)
		if (getcy() < hor.horizontalcorridorBottom() - 15 && getcx() < vert.verticalcorridorRight() - 14){
			//System.out.println("in upper left square");
			return "upperleft";
		}else{
			return "";
		}
	}

	
	public int getcy(){
		return y + (height / 2);
	}
	public int getcx(){
		return x + (width / 2);
	}
}
