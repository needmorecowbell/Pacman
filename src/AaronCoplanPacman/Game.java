package AaronCoplanPacman;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
//
public class Game extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	public static BallTest ball = new BallTest();
	public VerticalWalls leftwall = new VerticalWalls(0, 0);
	public VerticalWalls rightwall = new VerticalWalls(((160 * 3) - 15), 0);
	public HorizontalWalls topwall = new HorizontalWalls(0, 0);
	public HorizontalWalls bottomwall = new HorizontalWalls(0, ((160 * 3) - 38));
	
	static boolean ghostmove = true;
	
	public static TestGhost ghost = new TestGhost(50, 50, Color.blue);
	public static TestGhost ghost2 = new TestGhost(250, 70, Color.cyan);
	public static TestGhost ghost3 = new TestGhost(50, 200, Color.white);
	
	public static int ghost1xvelo = 1;
	public static int ghosty1velo = -1;
	public static int ghost2xvelo = -1;
	public static int ghost2yvelo = 1;
	public static int ghost3xvelo = 1;
	public static int ghost3yvelo = 1;
	
	public static final int SCALE = 3;
	public static final int WIDTH = (160 * SCALE);
	public static final int HEIGHT = (160 * SCALE);
	
	public boolean running = false;
	
	static int ballx = 100;
	static int bally = 100;
	
	static int ghost1x = 100, ghost1y = 100;
	static int ghost2x = 100, ghost2y = 100;
	static int ghost3x = 100, ghost3y = 100;
	
	public int movelrud = 0;
	public static char direction = 0;
	
	public static JFrame frame = new JFrame();
	public static int score = 0;
	
	public static final String NAME = "Pacman";
	
	
	public static void main(String[] args) throws Exception{
		
		//initialWindow menu = new initialWindow();
		//menu.loadJFrame();
		
		
		Game game = new Game();
		
		
		
		frame = new JFrame(NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(game);
		frame.pack();
		
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		while (ghost.collision(ball) && ghost2.collision(ball) && ghost3.collision(ball)){
		game.move();
		game.repaint();
		score++;
		frame.setTitle(NAME + ": " + score);
		Thread.sleep(10);
		ballx = ball.getx();
		bally = ball.gety();
		
		//System.out.println("X: " + x + ", Y: " + y);
		
		ballcollisions();
		ghost1collisions(ghost, ghost.getx(), ghost.gety());
		ghost2collisions(ghost2, ghost2.getx(), ghost2.gety());
		ghost3collisions(ghost3, ghost3.getx(), ghost3.gety());
		
		
		
		
		}
	}
	
	public static void ghost1collisions(TestGhost g, int ghostX, int ghostY){
		
		if (!((ghostX > 10) && (ghostX < ((160 * 3) - 45)) && (ghostY > 10) && (ghostY < ((160 * 3) - 68)))){
			if (!(ghostX > 10)){
				System.out.println("Collision with left wall.");
				ghost1xvelo = -ghost1xvelo;
				
			}
			if (!(ghostX < ((160 * 3) - 45))){
					System.out.println("Collision with right wall.");
					ghost1xvelo = -ghost1xvelo;
			
			}
			if (!(ghostY > 10)){
				System.out.println("Collision with top wall.");
				ghosty1velo = -ghosty1velo;
			}
			if (!(ghostY < ((160 * 3) - 68))){
				System.out.println("Collision with bottom wall.");
				ghosty1velo = -ghosty1velo;
			}
			
		}
	}
	
public static void ghost2collisions(TestGhost g, int ghostX, int ghostY){
		
		if (!((ghostX > 10) && (ghostX < ((160 * 3) - 45)) && (ghostY > 10) && (ghostY < ((160 * 3) - 68)))){
			if (!(ghostX > 10)){
				System.out.println("Collision with left wall.");
				ghost2xvelo = -ghost2xvelo;
				
			}
			if (!(ghostX < ((160 * 3) - 45))){
					System.out.println("Collision with right wall.");
					ghost2xvelo = -ghost2xvelo;
			
			}
			if (!(ghostY > 10)){
				System.out.println("Collision with top wall.");
				ghost2yvelo = -ghost2yvelo;
			}
			if (!(ghostY < ((160 * 3) - 68))){
				System.out.println("Collision with bottom wall.");
				ghost2yvelo = -ghost2yvelo;
			}
			
		}
	}
public static void ghost3collisions(TestGhost g, int ghostX, int ghostY){
	
	if (!((ghostX > 10) && (ghostX < ((160 * 3) - 45)) && (ghostY > 10) && (ghostY < ((160 * 3) - 68)))){
		if (!(ghostX > 10)){
			System.out.println("Collision with left wall.");
			ghost3xvelo = -ghost3xvelo;
			
		}
		if (!(ghostX < ((160 * 3) - 45))){
				System.out.println("Collision with right wall.");
				ghost3xvelo = -ghost3xvelo;
		
		}
		if (!(ghostY > 10)){
			System.out.println("Collision with top wall.");
			ghost3yvelo = -ghost3yvelo;
		}
		if (!(ghostY < ((160 * 3) - 68))){
			System.out.println("Collision with bottom wall.");
			ghost3yvelo = -ghost3yvelo;
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
	
	public Game(){
		
		addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            	
            }

            @Override
            public void keyPressed(KeyEvent e) {
                 KeyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
        setFocusable(true);
        
	}
	@Override
	public void paint(Graphics g){
		
		super.paint(g);
		Graphics g2d = (Graphics2D)g;
		g2d.setColor(Color.red);
		g2d.fillRect(0, 0, (160 * 3), (160 * 3));
		ball.paint(g2d);
		leftwall.paint(g2d);
		rightwall.paint(g2d);
		topwall.paint(g2d);
		bottomwall.paint(g2d);
		ghost.paint(g2d);
		ghost2.paint(g2d);
		ghost3.paint(g2d);
	}


	private void move(){
		
		ball.move(direction, movelrud);
		ghost.move(ghostmove, ghost1xvelo, ghosty1velo);
		ghost2.move(ghostmove, ghost2xvelo, ghost2yvelo);
		ghost3.move(ghostmove, ghost3xvelo, ghost3yvelo);
	}
	
	public void KeyPressed(KeyEvent k)
    {
        if(k.getKeyCode()==KeyEvent.VK_LEFT){
            direction = 'x';
        	movelrud = 0;
        }
        if(k.getKeyCode()==KeyEvent.VK_RIGHT){
            direction = 'x';
        	movelrud = 1;
        }
        if(k.getKeyCode()==KeyEvent.VK_UP){
            direction = 'y';
        	movelrud = 1;
        }
        if(k.getKeyCode()==KeyEvent.VK_DOWN){
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