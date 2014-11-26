package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Raceauto extends Auto {

	
	
	public Raceauto(){
		super();
		
		moveSpeed = 1;
		maxSpeed = 1;
		
		
		try {
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Autos/raceauto.gif"));
			sprites = new BufferedImage[1];
			for(int i = 0; i < sprites.length; i++){
				sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
			}

			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(400);
		
	}
	
	
	public void draw(Graphics2D g) {
		super.draw(g);
	}

}
