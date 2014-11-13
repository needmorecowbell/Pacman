package copAckMusPacman;

import javax.swing.*;




import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
//called in driver class
//Most of this is pretty self explanatory, credit windows and start game etc
public class initialWindow {
	
	 JFrame menuframe = new JFrame("");
	 JFrame creditsframe = new JFrame("");
	 JFrame scoresframe = new JFrame("");
	 JButton btnAddVoice= new JButton("Add/Remove Voice");
	 boolean open = true;
	 boolean voiceBtnClicked= false;
	

	public initialWindow(){}
	
	public void loadJFrame() throws IOException{
		
		open = true;
		menuframe.setTitle("Welcome to Pacman");
		menuframe.setResizable(false);
		menuframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton playgame = new JButton("Play Pacman");
		JButton viewscores = new JButton("View High Scores");
		JButton viewcredits = new JButton("Credits");
		JButton settings = new JButton("Settings");
		
		playgame.addActionListener(new playGame());
		viewscores.addActionListener(new viewScores());
		viewcredits.addActionListener(new viewCredits());
		settings.addActionListener(new changeSettings());
		
		Container cp = menuframe.getContentPane();
		
		cp.setLayout(new GridLayout(4, 0));
		cp.add(playgame);
		cp.add(viewscores);
		cp.add(settings);
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
			//System.out.println("open");
			
		}
		System.out.println("closed");
		Game.start();
	
	}
	
	private class changeSettings implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			menuframe.setVisible(false);
			
			 JFrame settingframe = new JFrame("");
			 boolean open = true;
			 
			 
			 
				open = true;
				settingframe.setTitle("Pacman Settings");
				settingframe.setResizable(false);
				settingframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
			
				
				btnAddVoice.addActionListener(new addVoice());
				
				Container cp = settingframe.getContentPane();
				
				cp.setLayout(new GridLayout(1, 0));
				cp.add(btnAddVoice);
			
				settingframe.pack();
				settingframe.setSize(600, 450);
				
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				double width = screenSize.getWidth();
				double height = screenSize.getHeight();
				
				int locationx = (int)((width / 2) - 300);
				int locationy = (int)((height / 2) - 225);
				
				settingframe.setLocation(locationx, locationy);
				
				settingframe.setVisible(true);
				
				
				
				
		}
	}
	private class addVoice implements ActionListener{
		public void actionPerformed(ActionEvent e){
			voiceBtnClicked= !voiceBtnClicked;
			if(voiceBtnClicked){
				btnAddVoice.setBackground(Color.green);
			}else{
				btnAddVoice.setBackground(null);
			}
			
			
		}
	}
	private class playGame implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
			menuframe.setVisible(false);
			open = false;
			System.out.println("hit first method");
			
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
				JOptionPane.showMessageDialog(null, "Error when reading in from file.", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			int size = playerlist.size();
			
			Container cont = scoresframe.getContentPane();
			cont.setLayout(new GridLayout(size + 1, 0));
			
			for (int start = 0; start < size; start++){
				
				Player player = playerlist.get(start);
				String playerinfo = player.toString();
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
			
			//Kill active process from other frams no longer needed when the menu button is pressed
			scoresframe.dispose();
			creditsframe.dispose();
			menuframe.setVisible(true);
			
		}
	}
}
