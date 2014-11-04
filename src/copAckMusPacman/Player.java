package copAckMusPacman;

public class Player {

	private String name;//user
	private int score;//score of game
	
	public Player(String name, int score){
		
		this.name = name;
		this.score = score;
	}
	
	public String getName(){
		
		return name;
	}
	
	public int getScore(){
		
		return score;
	}
	
	@Override
	public String toString(){
		
		return("Name: " + name + ", Score: " + score);
	}
}
