package AaronCoplanPacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

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
	
	public static boolean ghostmove = true;
	
	public static ArrayList<TestGhost> ghosts = new ArrayList<TestGhost>();
	
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
		
		boolean hasCollided= true;
		
		//initialWindow menu = new initialWindow();
		//menu.loadJFrame();
		ghosts.add(new TestGhost(50,50, Color.blue,1,-1));
		ghosts.add(new TestGhost(250, 70, Color.cyan,-1,1));
		ghosts.add(new TestGhost(50, 200, Color.white,1,1));
	
		
		Game game = new Game();
		
		
		
		frame = new JFrame(NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(game);
		frame.pack();
		
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		while (hasCollided){
		game.move();
		game.repaint();
		score++;
		frame.setTitle(NAME + ": " + score);
		Thread.sleep(10);
		ballx = ball.getx();
		bally = ball.gety();
		
		//System.out.println("X: " + x + ", Y: " + y);
		
		ballcollisions();
		for(int x=0;x<ghosts.size();x++){
			ghostcollisions(ghosts.get(x), ghosts.get(x).getx(), ghosts.get(x).gety());
			
		}
		for(int x=0;x<ghosts.size();x++){
			hasCollided=ghosts.get(x).collision(ball);
			
			if(hasCollided==false){break;}
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
		
		for(int x=0;x<ghosts.size();x++){
			ghosts.get(x).paint(g2d);
		}

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
