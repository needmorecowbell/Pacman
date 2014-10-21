package AaronCoplanPacman;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class InputOutput4Scores {

	public InputOutput4Scores(){
		
		try {
			buildfile();
		} catch (IOException e) {
			System.out.println("Error Building File!");
		}
	}
	
	public void buildfile() throws IOException{
		
		FileWriter fw = new FileWriter("E:\\Java\\PacmanGame\\NamesandScores.txt", true);
		PrintWriter file = new PrintWriter(fw);
		file.close();
	}
	
	public void writeToFile(String str) throws IOException{
		
		FileWriter fw = new FileWriter("E:\\Java\\PacmanGame\\NamesandScores.txt", true);
		PrintWriter file = new PrintWriter(fw);
		
		file.println(str);
		file.close();
	}
	
	public ArrayList<Player> readFromFile() throws FileNotFoundException{
		
		ArrayList<Player> players = new ArrayList<Player>();
		
		String name = "";
		String Score = "";
		int score = 0;
		
		File file = new File("E:\\Java\\PacmanGame\\NamesandScores.txt");
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
