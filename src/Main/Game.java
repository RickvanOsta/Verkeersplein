package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Game {
	
	public static boolean addAuto = false;
	public static boolean addRaceAuto = false;
	public static boolean addVrachtwagen = false;
	public static boolean isAutomatic = false;
	public static boolean nieuwGame = false;
	public static boolean showMarkers = false;

	public static void main(String[] args) {
		
		JFrame window = new JFrame("Verkeersplein Simulator");
		JMenuBar menu = new JMenuBar();
		
		
		JMenu file = new JMenu("File");
        JMenu nieuw = new JMenu("New");
        JMenu about = new JMenu("About"); 
        
        menu.add(file);
        menu.add(nieuw);
        menu.add(about);
        
        JMenuItem newGame = new JMenuItem("New Game");
        JCheckBoxMenuItem showmarks = new JCheckBoxMenuItem("Show markers");
        JMenuItem exit = new JMenuItem("Exit");
        
        file.add(newGame);
        file.addSeparator();
        file.add(showmarks);
        file.addSeparator();
        file.add(exit);
        
        JMenuItem auto = new JMenuItem("Auto");
        JMenuItem raceauto = new JMenuItem("Raceauto");
        JMenuItem vrachtwagen = new JMenuItem("Vrachtwagen");
        JCheckBoxMenuItem automatic = new JCheckBoxMenuItem("Automatic");
        
        nieuw.add(auto);
        nieuw.add(raceauto);
        nieuw.add(vrachtwagen);
        nieuw.addSeparator();
        nieuw.add(automatic);
        
		
        about.addSeparator();
        about.addSeparator();
        about.addSeparator();
        about.add("Verkeersplein Simulator");
        about.add("By Dudebag Games");
        about.addSeparator();
        about.addSeparator();
        about.addSeparator();
        
        
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("New Game");
                nieuwGame = true;
            }
        });
        
        showmarks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
              	if(showMarkers){
            		System.out.println("Show markers off");
            		showMarkers = false;
            	}
            	else {
            		System.out.println("Show markers on");
            		showMarkers = true;
            	}
                
            }
            }
        );
        
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        
        auto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Auto toegevoegd");
                addAuto = true;
            }
        });
        
        raceauto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Raceauto toegevoegd");
                addRaceAuto = true;
            }
        });
        
        vrachtwagen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Vrachtwagen toegevoegd");
                addVrachtwagen = true;
            }
        });
        
        automatic.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	if(isAutomatic){
            		System.out.println("Automatic off");
            		isAutomatic = false;
            	}
            	else {
            		System.out.println("Automatic on");
                    isAutomatic = true;
            	}
                
            }
        });
        
        
		window.setJMenuBar(menu);

		window.setContentPane(new GamePanel(menu));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
		
		
	}

}
