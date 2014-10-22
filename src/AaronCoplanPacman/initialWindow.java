package AaronCoplanPacman;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class initialWindow {
	
	 JFrame menuframe = new JFrame("");
	 JFrame creditsframe = new JFrame("");
	 JFrame scoresframe = new JFrame("");
	 boolean open = true;
	

	public initialWindow(){}
	
	public void loadJFrame() throws IOException{
		
		menuframe.setTitle("Welcome to Pacman");
		menuframe.setResizable(false);
		menuframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton playgame = new JButton("Play Pacman");
		JButton viewscores = new JButton("View High Scores");
		JButton viewcredits = new JButton("Credits");
		
		playgame.addActionListener(new playGame());
		viewscores.addActionListener(new viewScores());
		viewcredits.addActionListener(new viewCredits());
		
		Container cp = menuframe.getContentPane();
		
		cp.setLayout(new GridLayout(3, 0));
		cp.add(playgame);
		cp.add(viewscores);
		cp.add(viewcredits);
		
		menuframe.pack();
		menuframe.setSize(600, 450);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		
		int locationx = (int)((width / 2) - 300);
		int locationy = (int)((height / 2) - 225);
		
		menuframe.setLocation(locationx, locationy);
		
		menuframe.setVisible(true);
		
		while(open){
			
			
		}
		
	}
	
	private class playGame implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
			menuframe.setVisible(false);
			open = false;
			Game.start();
			
		}
		
	}
	
	private class viewScores implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
			menuframe.setVisible(false);
			
			scoresframe = new JFrame("");
			scoresframe.setTitle("High Scores");
			scoresframe.setResizable(false);
			scoresframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			InputOutput4Scores io = new InputOutput4Scores();
			
			ArrayList<Player> playerlist = null;
			try {
				playerlist = io.readFromFile();
			} catch (FileNotFoundException e1) {
				System.out.println("Error when reading in from file.");
			}
			int size = playerlist.size();
			
			Container cont = scoresframe.getContentPane();
			cont.setLayout(new GridLayout(size + 1, 0));
			
			for (int start = 0; start < size; start++){
				
				Player player = playerlist.get(start);
				String playerinfo = player.printPlayer();
				JLabel label = new JLabel(playerinfo);
				cont.add(label);
				
			}	
			JButton menu = new JButton("Menu");
			menu.addActionListener(new menubuttonlistener());
			cont.add(menu);
			
			scoresframe.pack();
			scoresframe.setSize(600, 450);
			
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			double width = screenSize.getWidth();
			double height = screenSize.getHeight();
			
			int locationx = (int)((width / 2) - 300);
			int locationy = (int)((height / 2) - 225);
			
			scoresframe.setLocation(locationx, locationy);
			
			scoresframe.setVisible(true);
			
			
			
		}
	}
	
	private class viewCredits implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
			menuframe.setVisible(false);
			
			creditsframe = new JFrame("");
			creditsframe.setTitle("Credits");
			creditsframe.setResizable(false);
			creditsframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			String credits = "This game was created by Aaron Coplan, Adam Ackerman, and Adam Musciano.";
			JLabel label = new JLabel(credits, JLabel.CENTER);
			JButton menu = new JButton("Menu");
			menu.addActionListener(new menubuttonlistener());
			
			Container cp = creditsframe.getContentPane();
			cp.setLayout(new GridLayout(2, 0));
			cp.add(label);
			cp.add(menu);
			
			creditsframe.pack();
			creditsframe.setSize(600, 450);
			
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			double width = screenSize.getWidth();
			double height = screenSize.getHeight();
			
			int locationx = (int)((width / 2) - 300);
			int locationy = (int)((height / 2) - 225);
			
			creditsframe.setLocation(locationx, locationy);
			
			creditsframe.setVisible(true);
			
			
		}
	}
	
	private class menubuttonlistener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
			scoresframe.dispose();
			creditsframe.dispose();
			menuframe.setVisible(true);
			
		}
	}
}
