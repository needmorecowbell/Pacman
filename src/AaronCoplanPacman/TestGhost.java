package AaronCoplanPacman;

import java.awt.*;

public class TestGhost {

	int x,y;
	int width=30;
	int height=30;
	Color c;
	final Color temp;
	int xvelo,yvelo;
	int  speed=2;
	boolean benignmode = false;
	String name = "";
	
	public TestGhost(String name, int x, int y, Color c, int xvelo, int yvelo){
		
		this.name = name;
		this.x = x;
		this.y = y;
		this.c = c;
		this.xvelo=xvelo;
		this.yvelo=yvelo;
		this.temp = c;
	}
	
	public TestGhost(int x, int y, Color c, int xvelo, int yvelo,int speed){
		
		this.x = x;
		this.y = y;
		this.c = c;
		this.xvelo=xvelo;
		this.yvelo=yvelo;
		this.speed=speed;
		this.temp = c;
	}
	
	public void setBenignMode(boolean benignmode){
		
		this.benignmode = benignmode;
		
		if(benignmode){
			c = Color.blue;
		}
		else{
			c=temp;
		}
	}
	
	public void paint (Graphics g){
		//g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(c);
		g.fillOval(x, y, width, height);
	}
	
	public boolean getBenignMode(){
		
		return benignmode;
	}
	
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
	
	public String getName(){return name;}
	
	public int getxvelo(){return xvelo;}
	public int getyvelo(){return yvelo;}
	public int getx(){return x;}	
	public int gety(){return y;}
	public void setxvelo(int xvelo){this.xvelo=xvelo;}
	public void setyvelo(int yvelo){this.yvelo=yvelo;}
	
	  public boolean collision(BallTest r, TestGhost a){
	        double distance = Math.sqrt(((a.getx() - r.getx()) * (a.getx() - r.getx()))+ ((a.gety() - r.gety()) * (a.gety() - r.gety())));
	        
	        if (benignmode){
	        	 if (distance <= (a.height / 2) + (r.height / 2)){
	 	            //balls have collided
	 	        	System.out.println("Collision!!!!");
	 	        	try{
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
}
