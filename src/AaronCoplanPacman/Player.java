package AaronCoplanPacman;

public class Player {

	private String name;
	private int score;
	
	public Player(String NAME, int SCORE){
		
		name = NAME;
		score = SCORE;
	}
	
	public String getName(){
		
		return name;
	}
	
	public int getScore(){
		
		return score;
	}
	
	public String printPlayer(){
		
		return("Name: " + name + ", Score: " + score);
	}
}
