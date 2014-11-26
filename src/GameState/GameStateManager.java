package GameState;

import java.awt.Graphics2D;

public class GameStateManager {
	
	private GameState[] gameStates;
	private int currentState;
	public static final int numberOfGameStates = 2;
	
	public static int menuState = 0;
	public static int pleinState = 1;


	public GameStateManager() {
		gameStates = new GameState[numberOfGameStates];
		
		currentState = menuState;
		loadState(currentState);
	}
	
	
	private void loadState(int state){
		
		if(state == menuState){
			gameStates[state] = new Menu(this);
		}
		if(state == pleinState){
			gameStates[state] = new Plein(this);
		}
	}
	
	private void unloadState(int state){
		gameStates[state] = null;
	}
	
	public void setState(int state){
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
	}
	
	public void update(){
		if(gameStates[currentState] != null){
		gameStates[currentState].update();
		}
	}
	
	public void draw(java.awt.Graphics2D g){
		if(gameStates[currentState] != null){
		gameStates[currentState].draw(g);
		}
	}
	
	public void keyPressed(int k){
		if(gameStates[currentState] != null){
		gameStates[currentState].keyPressed(k);
		}
	}
	
	public void keyReleased(int k){
		if(gameStates[currentState] != null){
		gameStates[currentState].keyReleased(k);
		}
	}
}
