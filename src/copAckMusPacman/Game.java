package copAckMusPacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//
public class Game extends JPanel{
	//THIS IS A TEST
	static int value = 0;
	private static final long serialVersionUID = 1L;
	public static final int SCALE = 3;	
	
	//declares pacman and outside walls
	public static Pacman ball = new Pacman();
	public VerticalWalls leftwall = new VerticalWalls(0, 0);
	public VerticalWalls rightwall = new VerticalWalls(((160 * SCALE) - 15), 0);
	public HorizontalWalls topwall = new HorizontalWalls(0, 0);
	public HorizontalWalls bottomwall = new HorizontalWalls(0, ((160 * SCALE) - 38));
	
	//sets up arrays for the ghosts, deadghosts, pills, dots, corridors, and coverups
	public static boolean ghostmove = false;
	public static ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
	public static ArrayList<GhostWithAI> deadghosts = new ArrayList<GhostWithAI>();
	public static ArrayList<Pill> pills = new ArrayList<Pill>();
	public static ArrayList<Dots> dots = new ArrayList<Dots>();
	public static ArrayList<Corridor> corridors = new ArrayList<Corridor>();
	public static ArrayList<PaintOver> coverup = new ArrayList<PaintOver>();
	public static ArrayList<GhostWithAI> aighosts = new ArrayList<GhostWithAI>();
	
	//data for width of the JFrame and height of the JFrame
	public static final int WIDTH = (160 * SCALE);
	public static final int HEIGHT = (160 * SCALE);

	//boolean for whether or not pac is moving
	public boolean running = false;
	
	//ints for pacmans x, y location as well as int for left, right, up, down, and char for direction
	static int ballx,bally;
	public int movelrud = 0;
	public static char direction = 0;
	
	//sets up the JFrame
	public static JFrame frame = new JFrame();
	
	//ints for the score and benign (eatable) mode timer
	public static int score = 0;
	public static int benignmodetimer = 0;
	
	//Name of the JFrame is Pacman
	public static final String NAME = "Pacman";
	
	//boolean for whether or not the ghosts have eaten pacman
	public static boolean hasnotcollided = true;
	
	//sets up the corridors
	public static Corridor corridortop = new Corridor(235, 37, 400, 40);//235 is center of x value
	public static Corridor corridorbottom = new Corridor(235, 417, 400, 40);//recentered corridor values
	public static Corridor corridorleft = new Corridor(55, 227, 40, 420);
	public static Corridor corridorright = new Corridor(415, 227, 40, 420);
	public static Corridor corridormiddle = new Corridor(235, 188, 400, 40);
	
	//Constructor for the Game 
	public Game(){
		//Adds key listener so pacman is controlled using arrow keys
		addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                 KeyPressed(e);
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        setFocusable(true);  
	}
	
	//this is the game method which runs the majority of the game
	public static void start(){
		/*
		 * sets boolean hasnotcollided(which is for collisions between pacman and the ghosts) equal to true, 
		 * meaning the game will run
		*/
		hasnotcollided= true;
		
		//adds four ghosts, each with different info, to the arraylist of ghosts 
		//ghosts.add(new Ghost("Adam M", 150, 180, "pink",1,-1));
		//ghosts.add(new Ghost("Aaron", 185, 180, "teal",-1,1));
		//ghosts.add(new Ghost("Adam Ack", 220, 180, "red",1,1));
		//ghosts.add(new Ghost("Eugene Crabs", 255, 180, "orange", -1, -1));
		//ghosts.add(new ReproducerGhost(40,60,"pink",1,1,2));
		aighosts.add(new GhostWithAI("Adam Ack", 220, 177, "red"));
		aighosts.add(new GhostWithAI("Aaron", 218, 177, "teal"));
		aighosts.add(new GhostWithAI("Adam M", 222, 177, "pink"));
		aighosts.add(new GhostWithAI("Eugene Crabs", 220, 177, "orange"));
		
		
		//adds four pills, one in each corner, to the arraylist of pills
		pills.add(new Pill(50, 410));
		pills.add(new Pill(50, 30));
		pills.add(new Pill(410, 30));
		pills.add(new Pill(410, 410));
		
		//adds eight coverups, two per corner, in order to cover the extra rectangles, to the arraylist of coverups
		coverup.add(new PaintOver(75, 23, 6, 34));
		coverup.add(new PaintOver(41, 57, 34, 6));
		coverup.add(new PaintOver(395, 23, 6, 34));
		coverup.add(new PaintOver(401, 57, 34, 6));
		coverup.add(new PaintOver(401, 397, 34, 6));
		coverup.add(new PaintOver(41, 397, 34, 6));
		coverup.add(new PaintOver(75, 403, 6, 34));
		coverup.add(new PaintOver(395, 403, 6, 34));
		
		
		//starting y location of the dots
		int dotsy = 34;
		//increment x location by 40 for each pill.
		while (dotsy <= 430){
		//this while loop adds all the dots to the map
		for (int start = 55; start < 438; start += 40){
			dots.add(new Dots(start, dotsy));
		}
		
		dotsy += 38;
		}
		
		
		//calls the constructor for a new game, which sets up the keylistener
		Game game = new Game();		
		
		/*
		 * sets up frame with the following:
		 * title, exitonclose, sets size, setresizable is false, sets visible true, adds the game to the frame, and packs
		 */
		
		frame = new JFrame(NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.pack();	
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		//Game loops that runs until the pacman collides with a ghost
		while (hasnotcollided){
			
			//the next line does nothing b/c it returns a string but it is not assigned to anything
			ball.square1(corridortop, corridorleft);
			//the above line does nothing b/c it returns a string but it is not assigned to anything
			
			//the game moves and repaints
			game.move();
			game.repaint();
			
			//it sets the title as the score
			frame.setTitle(NAME + ": " + score);
			
			//thread sleeps for ten milliseconds
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//sets the ball xlocation equal to ballx and ball ylocation equal to bally
			ballx = ball.getx();
			bally = ball.gety();
		
			//calls the ball collisions method, which handles collisions with the four outside walls and ensures pac stays inside them
			ballcollisions();
			
			//calls the ghostcollisions method for each of the ghosts in the ghosts array, which handles bouncing off the outside walls
			/*for(int x=0;x<ghosts.size();x++){
				ghostcollisions(ghosts.get(x), ghosts.get(x).getx(), ghosts.get(x).gety());
			
			}*/
			
			//for each of the ghosts it checks to see if the ghost has collided with pacman
			for(int x=0;x<aighosts.size();x++){
				//hasnotcollided=aighosts.get(x).collision(ball, aighosts.get(x));		
				if(hasnotcollided==false){break;}
			}	
			
			/*checks to see if pac is colliding with a pill: 
			 * if a pill is eaten:
			 * 50 is added to the score, the pill is removed from the map, the benign mode timer is set to zero
			 * all the ghosts in the ghosts arraylist are set into benign mode when the pill is eaten
			 */
			for (int x=0; x<pills.size();x++){
				if(pills.get(x).pillEaten(ball) == false){
					score += 50;
					pills.remove(x);
					benignmodetimer = 0;
					
					for(int X = 0; X < /*aighosts...but that creates problems*/ghosts.size(); X++){
						aighosts.get(X).setBenignMode(true);
					}
				}
			}
			
			//checks to see if pac is colliding with a dot. if dot is eaten, 10 is added to score and the dot is removed from the arraylist
			for (int x = 0; x < dots.size(); x++){
				
				if (dots.get(x).dotEaten(ball) == false){
					score += 10;
					dots.remove(x);
				}
			}
			stayInCorridors();
			
			//if all the ghosts are eaten, it checks the dead ghosts to see if in benign mode and if so, adds 1 to the benign mode timer
			if (aighosts.size() == 0){
				try{
					if(deadghosts.get(0).getBenignMode()){
						benignmodetimer++;
					}
				}catch(IndexOutOfBoundsException e0){
					
				}
			}
			//if they are not all eaten, it checks to see if the alive ones are in benign mode and if so, adds 1 to the benign mode timer
			else{	
				try{	
					if (aighosts.get(0).getBenignMode() || deadghosts.get(0).getBenignMode()){
						benignmodetimer++;
					}
				}catch (IndexOutOfBoundsException e){
					//does nothing
				}
			}
			
			//edible/benign ghosts flash when they are about to switch back to normal ghosts
			if(benignmodetimer >= 380){
				
				System.out.println("FLASH");
				for(int x=0;x<aighosts.size();x++){
					aighosts.get(x).flash(benignmodetimer);
				}
			}
			
			//if the benign mode timer reaches 500m the ghosts switch back to regular and any eaten ghosts get repainted in the center
			if(benignmodetimer >= 500){
			
				for (int x = 0; x<ghosts.size(); x++){
					aighosts.get(x).setBenignMode(false);
					try{
					deadghosts.get(x).setBenignMode(false);
					} catch (IndexOutOfBoundsException e){
						//do nothing
					}
					
				}
				
				for(int x=0; x<deadghosts.size(); x++){
					
					aighosts.add(deadghosts.get(x));
					deadghosts.get(x).paintincenter();
				}
				
				deadghosts.clear();
				benignmodetimer = 0;
			}
			
		}
		/* (this is outside the while loop)
		 * if you got a highscore, it will ask you for your name, otherwise it just rewrites the old highscores
		 */
		InputOutput4Scores io = new InputOutput4Scores();
		try {
			io.writehighscores(score);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error Writing to High Score List!", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//method for ghosts collisions with outside walls which allows them to bounce
	public static void ghostcollisions(Ghost g, int ghostX, int ghostY){
		
		if (!((ghostX > 10) && (ghostX < ((160 * 3) - 45)) && (ghostY > 10) && (ghostY < ((160 * 3) - 68)))){
			if (!(ghostX > 10)){
				System.out.println("Collision with left wall.");
				g.setxvelo(-g.getxvelo());
				
			}
			if (!(ghostX < ((160 * 3) - 45))){
					System.out.println("Collision with right wall.");
					g.setxvelo(-g.getxvelo());
			
			}
			if (!(ghostY > 10)){
				System.out.println("Collision with top wall.");
				g.setyvelo(-g.getyvelo());
			}
			if (!(ghostY < ((160 * 3) - 68))){
				System.out.println("Collision with bottom wall.");
				g.setyvelo(-g.getyvelo());
			}		
		}
	}
	
	//method for pacman's collisions with outside walls which makes it stop
	public static void ballcollisions(){
		
		if (!((ballx > 10) && (ballx < ((160 * 3) - 45)) && (bally > 10) && (bally < ((160 * 3) - 68))))
			direction = 'b';
		
		if (!(ballx > 10)){
			System.out.println("Collision with left wall.");
			ball.moveballx(1);
		}
		if (!(ballx < ((160 * 3) - 45))){
				System.out.println("Collision with right wall.");
				ball.moveballx(-1);
		}
 		if (!(bally > 10)){
			System.out.println("Collision with top wall.");
			ball.movebally(1);
		}
		if (!(bally < ((160 * 3) - 68))){
			System.out.println("Collision with bottom wall.");
			ball.movebally(-1);
		}
	}
		
	//paint method which paints all visual objects on the map
	@Override
	public void paint(Graphics g){
		
		super.paint(g);
		Graphics g2d = (Graphics2D)g;
		g2d.setColor(Color.gray);
		g2d.fillRect(0, 0, (160 * SCALE), (160 * SCALE));
		leftwall.paint(g2d);
		rightwall.paint(g2d);
		topwall.paint(g2d);
		bottomwall.paint(g2d);
		corridortop.paint(g2d);
		corridorbottom.paint(g2d);
		corridorleft.paint(g2d);
		corridorright.paint(g2d);
		corridormiddle.paint(g2d);
		for(int x=0;x<coverup.size();x++){coverup.get(x).paint(g2d);}
		for(int x=0;x<dots.size();x++){dots.get(x).paint(g2d);}
		for(int x=0;x<pills.size();x++){pills.get(x).paint(g2d);}
		//for(int x=0;x<ghosts.size();x++){ghosts.get(x).paint(g2d);}
		for(int x=0;x<aighosts.size();x++){aighosts.get(x).paint(g2d);}
		ball.paint(g2d);
	}

	//move method which has ghosts and ball move
	private void move(){
		
		ball.move(direction, movelrud);
		//for(int x=0;x<ghosts.size();x++){
			//ghosts.get(x).move(ghostmove,ghosts.get(x).getxvelo(),ghosts.get(x).getyvelo());
		//}
		
		for(int x = 0; x < aighosts.size(); x++){
			
			aighosts.get(x).move(ghostmove);
		}
	}
	
	public static void stayInCorridors(){//This is going to be a huge method, has to check for every corridor
		value = 0;
		
		String leftvert = ball.verticalcorridorClash(corridorleft);
		if(leftvert.equals("vertical")){
			//disable the left right keys
			value = 2;
		}
		
		String rightvert = ball.verticalcorridorClash(corridorright);
		if(rightvert.equals("vertical")){
			//disable the left right keys
			value = 2;
		}
		
		String tophorz = ball.horizontalcorridorClash(corridortop);
		if(tophorz.equals("horizontal")){
			//disable the up down keys
			value = 3;
		}
		
		String bottomhorz = ball.horizontalcorridorClash(corridorbottom);
		if(bottomhorz.equals("horizontal")){
			//disable the up down keys
			value = 3;
		}
		
		String square1 = ball.square1(corridortop, corridorleft);
		if(square1.equals("upperleft")){
			//disable the left and up keys: only allow the right and down keys to work
			System.out.println("UPPER LEFT ACK");
			if(ball.getSpriteDirection() == 'u' || ball.getSpriteDirection() == 'l'){
				
				direction = 'b';
			}
			value = 1;
		}
		
		String square2 = ball.square2(corridorbottom, corridorleft);
		if(square2.equals("bottomleft")){
			//disable the down and left keys
			if(ball.getSpriteDirection() == 'd' || ball.getSpriteDirection() == 'l'){
				direction = 'b';
			}
			value = 5;
		}
		
		String square3 = ball.square3(corridorbottom, corridorright);
		if(square3.equals("bottomright")){
			//disable the down and right keys
			if(ball.getSpriteDirection() == 'r' || ball.getSpriteDirection() == 'd'){
				direction = 'b';
			}
			value = 6;
		}
		
		String square4 = ball.square4(corridortop, corridorright);
		if(square4.equals("upperright")){
			//disable the up and right keys
			if(ball.getSpriteDirection() == 'r' || ball.getSpriteDirection() == 'u'){
				direction = 'b';
			}
			value = 7;
		}
		
	}
	
	//key pressed method handles what happens for each keypress(left, right, up, down)
	public void KeyPressed(KeyEvent k) 
    {
        if(k.getKeyCode()==KeyEvent.VK_LEFT && (value != 2)&&(value !=1)&&(value !=5)){
        	
            ball.spriteDirection('l');	
        	direction = 'x';
        	movelrud = 0;
        }
        if(k.getKeyCode()==KeyEvent.VK_RIGHT &&(value != 2) && (value!=6) && (value != 7)){
        	
        	ball.spriteDirection('r');
        	direction = 'x';
        	movelrud = 1;
        }
        if(k.getKeyCode()==KeyEvent.VK_UP && (value !=3) && (value !=1) &&(value !=7)){
        	
        	
        		
            	ball.spriteDirection('u');
            	direction = 'y';
            	movelrud = 1;
        	
        }
        if(k.getKeyCode()==KeyEvent.VK_DOWN && (value !=3)&&(value != 5) && (value != 6)){
        	
            	ball.spriteDirection('d');
            	direction = 'y';
            	movelrud = 0;
        	
            	
        }
        if(k.getKeyCode()==KeyEvent.VK_SPACE){
        	direction = 'b';
        	movelrud = 0;
        	ghostmove = !ghostmove;
        }

    }
	
	
}
