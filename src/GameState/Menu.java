package GameState;

import java.awt.*;
import java.awt.event.KeyEvent;

import Main.GamePanel;

public class Menu extends GameState {
	
	Font titleFont = new Font("Arial", 20, 20);
	Font optionsFont = new Font("Verdana", 20, 17);
	String[] options = {"Start", "Quit"};
	int currentChoice = 0;

	public Menu(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	public void init() {

	}

	public void update() {
		
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, GamePanel.width, GamePanel.height);
		g.setColor(Color.WHITE);
		g.setFont(titleFont);
		g.drawString("Verkeersplein", 97, 50);
		
		for(int i = 0; i < options.length; i++){
			if(currentChoice == i){
				g.setColor(Color.YELLOW);
				g.setFont(optionsFont);
				g.drawString("-", 120, 100 + (i * 20));
				g.drawString("-", 188, 100 + (i * 20));
			}
			else{
				g.setColor(Color.WHITE);
			}
			g.setFont(optionsFont);
			g.drawString(options[i], 140, 100 + (i * 20));
		}
		
	}
	
	public void select(){
		if(currentChoice == 0){
			gsm.setState(GameStateManager.pleinState);
		}
		if(currentChoice == 1){
			System.exit(0);
		}
	}

	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			select();
		}
		if(k == KeyEvent.VK_UP){
			currentChoice--;
			if(currentChoice == -1){
				currentChoice = options.length - 1;
			}
		}
		if(k == KeyEvent.VK_DOWN){
			currentChoice++;
			if(currentChoice == options.length){
				currentChoice = 0;
			}
		}
	}

	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

}
