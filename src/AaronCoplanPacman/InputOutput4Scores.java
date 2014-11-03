package AaronCoplanPacman;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

// This class will need to be revised and automated, the E drive will be different on separate computers.
public class InputOutput4Scores {

	public InputOutput4Scores(){
		
		try {
			buildfile();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error Building File!", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void buildfile() throws IOException{
		
		FileWriter fw = new FileWriter("src/scoreRes/ScoreList", true);
		PrintWriter file = new PrintWriter(fw);
		file.close();
	}
	
	public void writehighscores(int score) throws IOException{
		
		FileWriter fw = new FileWriter("src/scoreRes/ScoreList", false);
		PrintWriter file = new PrintWriter(fw);
		
		ArrayList<Player> highscorelist = figureoutifHighScore(score);
		
		file.println(highscorelist.get(0).getName());
		file.println(highscorelist.get(0).getScore());
		file.println(highscorelist.get(1).getName());
		file.println(highscorelist.get(1).getScore());
		file.println(highscorelist.get(2).getName());
		file.println(highscorelist.get(2).getScore());
		file.println(highscorelist.get(3).getName());
		file.println(highscorelist.get(3).getScore());
		
		file.close();
	}
	
	public String getName(){
		
		String name = "";
		
		while(name.equals("")){
			
			name = JOptionPane.showInputDialog("Congratulations! Enter your name:");
		}
		
		return name;
	}
	
	public ArrayList<Player> figureoutifHighScore(int score) throws IOException{
		
		ArrayList<Player> players = readFromFile();
		int score1 = players.get(0).getScore();
		int score2 = players.get(1).getScore();
		int score3 = players.get(2).getScore();
		int score4 = players.get(3).getScore();
		
		if(score > score1){
			ArrayList<Player> newtop = new ArrayList<Player>();
			newtop.add(new Player(getName(), score));
			newtop.add(players.get(0));
			newtop.add(players.get(1));
			newtop.add(players.get(2));
			return newtop;
		}else if (score > score2){
			ArrayList<Player> newsecond = new ArrayList<Player>();
			newsecond.add(players.get(0));
			newsecond.add(new Player(getName(), score));
			newsecond.add(players.get(1));
			newsecond.add(players.get(2));
			return newsecond;
		}else if(score > score3){
			ArrayList<Player> newthird = new ArrayList<Player>();
			newthird.add(players.get(0));
			newthird.add(players.get(1));
			newthird.add(new Player(getName(), score));
			newthird.add(players.get(2));
			return newthird;
		}else if (score > score4){
			ArrayList<Player> newfourth = new ArrayList<Player>();
			newfourth.add(players.get(0));
			newfourth.add(players.get(1));
			newfourth.add(players.get(2));
			newfourth.add(new Player(getName(), score));
			return newfourth;
		} else{
			return players;
		}
					
	}
	
	public ArrayList<Player> readFromFile() throws FileNotFoundException{
		
		ArrayList<Player> players = new ArrayList<Player>();
		
		String name = "";
		String Score = "";
		int score = 0;
		
		File file = new File("src/scoreRes/ScoreList");
		Scanner inputFile = new Scanner(file);
		
		while (inputFile.hasNext()){
			
			name = inputFile.nextLine();
			Score = inputFile.nextLine();
			score = Integer.parseInt(Score);
			
			Player player = new Player(name, score);
			players.add(player);
			
		}
		inputFile.close();
		
		return players;
	}
}
