package Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

import javax.swing.JMenuBar;
import javax.swing.JPanel;

import GameState.GameStateManager;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener {

	public static int width = 320;
	public static int height = 240;
	public int scale = 2;
	public static JMenuBar menu;
	
	private Thread thread;
	private boolean running;
	private int fps = 60;
	private long targetTime = 1000/fps;
	
	private BufferedImage image;
	private Graphics2D g;
	private GameStateManager gsm;
	
	public GamePanel(JMenuBar menu) {
		super();
		GamePanel.menu = menu;
		
		setPreferredSize(new Dimension(width * scale, height * scale));
		setFocusable(true);
		requestFocus();
		setBackground(Color.green.darker());
	}
	
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}
	
	private void init() {
		
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
	g = (Graphics2D) image.getGraphics();
	
	running = true;
	
	gsm = new GameStateManager();
	}
	
	public void run(){
		
		init();
		
		long start;
		long elapsed;
		long wait;
		
			while(running){
				
				start = System.nanoTime();

				if(checkFocus()){
				update();
				draw();
				drawToScreen();
				}
				
				elapsed = System.nanoTime() - start;
				
				wait = targetTime - elapsed / 1000000;
				if(wait < 0) { 
					wait = 5;
				}
				
				try {
					Thread.sleep(wait);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			
		}
		
		
	}
	
	public boolean checkFocus(){
		return this.hasFocus();
	}
	
	private void update() {
		gsm.update();
	}
	private void draw() {
		gsm.draw(g);
	}
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, width * scale, height * scale, null);
		g2.dispose();
	}
	
	public void keyTyped(KeyEvent key) {}
	public void keyPressed(KeyEvent key) {
		if(key != null){
		gsm.keyPressed(key.getKeyCode());
		}
	}
	public void keyReleased(KeyEvent key) {
		if(key != null){
		gsm.keyReleased(key.getKeyCode());
		}
	}
	
}
