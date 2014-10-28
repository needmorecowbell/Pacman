package AaronCoplanPacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
//
public class Game extends JPanel{
	//THIS IS A TEST
	private static final long serialVersionUID = 1L;
	public static final int SCALE = 3;	
	
	public static BallTest ball = new BallTest();
	public VerticalWalls leftwall = new VerticalWalls(0, 0);
	public VerticalWalls rightwall = new VerticalWalls(((160 * SCALE) - 15), 0);
	public HorizontalWalls topwall = new HorizontalWalls(0, 0);
	public HorizontalWalls bottomwall = new HorizontalWalls(0, ((160 * SCALE) - 38));
	
	public static boolean ghostmove = true;
	public static ArrayList<TestGhost> ghosts = new ArrayList<TestGhost>();
	public static ArrayList<TestGhost> deadghosts = new ArrayList<TestGhost>();
	public static ArrayList<Pill> pills = new ArrayList<Pill>();
	public static ArrayList<Dots> dots = new ArrayList<Dots>();
	public static ArrayList<Corridor> corridors = new ArrayList<Corridor>();
	

	public static final int WIDTH = (160 * SCALE);
	public static final int HEIGHT = (160 * SCALE);
	public boolean running = false;
	static int ballx,bally;
	public int movelrud = 0;
	public static char direction = 0;
	public static JFrame frame = new JFrame();
	public static int score = 0;
	public static int benignmodetimer = 0;
	public static final String NAME = "Pacman";
	public static boolean hasnotcollided = true;
	public static Corridor corridortop = new Corridor(235, 37, 400, 40);//235 is center of x value
	public static Corridor corridorbottom = new Corridor(235, 417, 400, 40);//recentered corridor values
	public static Corridor corridorleft = new Corridor(55, 227, 40, 420);
	public static Corridor corridorright = new Corridor(415, 227, 40, 420);
	
	
	
	
	public Game(){
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
	
	
	public static void start(){
		hasnotcollided= true;
		
		ghosts.add(new TestGhost("Adam M", 50,50, Color.pink,1,-1));
		ghosts.add(new TestGhost("Aaron", 250, 150, Color.cyan,-1,1));
		ghosts.add(new TestGhost("Adam Ack", 50, 200, Color.magenta,1,1));
		ghosts.add(new TestGhost("Eugene Crabs", 250, 250, Color.green, -1, -1));
		pills.add(new Pill(30, 410));
		pills.add(new Pill(30, 30));
		pills.add(new Pill(420, 30));
		pills.add(new Pill(420, 410));
		
		int dotsy = 34;
		//increment x location by 39 for each pill.
		while (dotsy <= 420){
		
		for (int start = 62; start < 420; start += 38){
			dots.add(new Dots(start, dotsy));
		}
		
		dotsy += 38;
		}
		
		
		
		Game game = new Game();		
		
		frame = new JFrame(NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.pack();	
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		
		while (hasnotcollided){

			game.move();
			game.repaint();
			frame.setTitle(NAME + ": " + score);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ballx = ball.getx();
			bally = ball.gety();
		
		
			ballcollisions();
			
			for(int x=0;x<ghosts.size();x++){
				ghostcollisions(ghosts.get(x), ghosts.get(x).getx(), ghosts.get(x).gety());
			
			}
			
			
			
			for(int x=0;x<ghosts.size();x++){
				hasnotcollided=ghosts.get(x).collision(ball, ghosts.get(x));		
				if(hasnotcollided==false){break;}
			}	
			
			
			
			for (int x=0; x<pills.size();x++){
				if(pills.get(x).pillEaten(ball) == false){
					score += 50;
					pills.remove(x);
					benignmodetimer = 0;
					
					for(int X = 0; X < ghosts.size(); X++){
						ghosts.get(X).setBenignMode(true);
					}
					
				}
			}
			
			for (int x = 0; x < dots.size(); x++){
				
				if (dots.get(x).dotEaten(ball) == false){
					score += 10;
					dots.remove(x);
				}
			}
			
			if (ghosts.size() == 0){
				if(deadghosts.get(0).getBenignMode()){
					benignmodetimer++;
				}
			}
			
			else{	
			try{	
			if (ghosts.get(0).getBenignMode() || deadghosts.get(0).getBenignMode()){
			benignmodetimer++;
			}
			}
			catch (IndexOutOfBoundsException e){
				
			}
			
		}
			if(benignmodetimer >= 500){
			
				for (int x = 0; x<ghosts.size(); x++){
					ghosts.get(x).setBenignMode(false);
				}
				
				for(int x=0; x<deadghosts.size(); x++){
					deadghosts.get(x).paintincenter();
					ghosts.add(deadghosts.get(x));
				}
				
				deadghosts.clear();
			}
			
		}
	}
	
	public static void ghostcollisions(TestGhost g, int ghostX, int ghostY){
		
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
		
	@Override
	public void paint(Graphics g){
		
		super.paint(g);
		Graphics g2d = (Graphics2D)g;
		g2d.setColor(Color.gray);
		g2d.fillRect(0, 0, (160 * SCALE), (160 * SCALE));
		ball.paint(g2d);
		leftwall.paint(g2d);
		rightwall.paint(g2d);
		topwall.paint(g2d);
		bottomwall.paint(g2d);
		corridortop.paint(g2d);
		corridorbottom.paint(g2d);
		corridorleft.paint(g2d);
		corridorright.paint(g2d);
		
		for(int x=0; x<dots.size();x++){dots.get(x).paint(g2d);}
		for(int x=0; x<pills.size();x++){pills.get(x).paint(g2d);}
		for(int x=0;x<ghosts.size();x++){ghosts.get(x).paint(g2d);}
		

	}

	private void move(){
		
		ball.move(direction, movelrud);
		for(int x=0;x<ghosts.size();x++){
			ghosts.get(x).move(ghostmove,ghosts.get(x).getxvelo(),ghosts.get(x).getyvelo());
		}
		
	}
	
	public void KeyPressed(KeyEvent k) 
    {
        if(k.getKeyCode()==KeyEvent.VK_LEFT){
            if(ball.verticalcorridorClash(corridorleft) == 1){
            	
            }
            else{
        	direction = 'x';
        	movelrud = 0;
            }
        }
        if(k.getKeyCode()==KeyEvent.VK_RIGHT){
        	if(ball.verticalcorridorClash(corridorleft) == 1){
        		
        	}
        	else{
        	direction = 'x';
        	movelrud = 1;
        	}
        }
        if(k.getKeyCode()==KeyEvent.VK_UP){
            if(ball.horizontalcorridorClash(corridortop) == 1){
            	
            }
            else{
            	direction = 'y';
            	movelrud = 1;
            }
        }
        if(k.getKeyCode()==KeyEvent.VK_DOWN){
            if(ball.horizontalcorridorClash(corridortop) == 1){
            
            }
            else{
            	direction = 'y';
            	movelrud = 0;
            }
        }
        if(k.getKeyCode()==KeyEvent.VK_SPACE){
        	direction = 'b';
        	movelrud = 0;
        	ghostmove = !ghostmove;
        }

    }
	
	
}
