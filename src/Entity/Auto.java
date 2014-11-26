package Entity;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Main.Game;

public class Auto {

		protected String kenteken;
		protected double xmap;
		protected double ymap;
		
		public BufferedImage[] sprites;
		
		protected double x;
		protected double y;
		protected double dx;
		protected double dy;
		
		protected double xtemp;
		protected double ytemp;
		
		protected int width = 30;
		protected int height = 15;
		
		protected Animation animation;
		
		protected double richting = 45;
		protected boolean afgeslagen;
		protected boolean ingevoegd;
		protected boolean oprotonde;
		
		protected double zichtveld;
		
		protected float startTimer;
		
		protected double moveSpeed;
		protected double maxSpeed;
		protected double stopSpeed;
		protected double nextx;
		protected double nexty;
		
		protected int[] polxpoints;
		protected int[] polypoints;
		
		public Auto(){
			
			moveSpeed = 0.8;
			maxSpeed = 0.8;
			stopSpeed = 0;
			
			polxpoints = new int[3];
			polypoints = new int[3];
			
			startTimer = System.nanoTime();
			
			try {
				BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Autos/auto.gif"));
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
			
			double random;
			random = Math.random();
			if(random <= 0.25){
				setPosition(-50,130);
				setRichting(0);
			}
			else if(random > 0.25 && random <= 0.5){
				setPosition(320,107);
				setRichting(180);
			}
			else if(random > 0.5 && random <= 0.75){
				setPosition(123,-70);
				setRichting(90);
			}
			else {
				setPosition(150,310);
				setRichting(270);
			}
			
			zichtveld = 35;
			
		}
		
		public boolean isAfgeslagen() {
			return afgeslagen;
		}

		public void setAfgeslagen(boolean afgeslagen) {
			this.afgeslagen = afgeslagen;
		}

		public boolean isIngevoegd() {
			return ingevoegd;
		}

		public void setIngevoegd(boolean ingevoegd) {
			this.ingevoegd = ingevoegd;
		}
		
		public boolean isOpRotonde() {
			return oprotonde;
		}

		public boolean isDichtbij(Auto o){
			Rectangle r1 = getBoundaries();
			Rectangle r2 = o.getBoundaries();
			return r1.intersects(r2);
		}
		
		
		public Rectangle getBoundaries(){
			return new Rectangle((int)x - height - 15, (int)y - (height) - 15, height + 30, height + 30);
		}
		
		public double afstandTussen(Auto auto){
			double afstand = 0;
			double xverschil = nextx - (auto.getx() + width / 2);
			double yverschil = nexty - (auto.gety() + height / 2);
			polxpoints[0] = (int) nextx;
			polxpoints[1] = auto.getx() + width / 2;
			polxpoints[2] = (int) (nextx - xverschil);
			polypoints[0] = (int) nexty;
			polypoints[1] = auto.gety() + height / 2;
			polypoints[2] = (int) nexty;
			afstand = Math.sqrt(xverschil*xverschil + yverschil*yverschil);
			return afstand;
		}
		
		
		public boolean intersects(Auto o){
			Rectangle r1 = getRectangle();
			Rectangle r2 = o.getRectangle();
			return r1.intersects(r2);
		}
		
		public Rectangle getRectangle(){
			return new Rectangle((int)x - width, (int)y - height, width, height);
		}
		
		public float getTime(){
			float elapsed = (System.nanoTime() - startTimer) / 1000000;
			return (float) (elapsed * maxSpeed);
		}
		
		public int getx() { 
			return (int)x; 
			}
		
		public int gety() {
			return (int)y;
		}
		
		public int getWidth() {
			return width;
		}
		
		public int getHeight() {
			return height;
		}
		
		public void setRichting(int richting){
			this.richting = richting;
		}
		
		public void setMovement(){
			xtemp += moveSpeed*Math.cos(Math.toRadians(richting));
			ytemp += moveSpeed*Math.sin(Math.toRadians(richting));
		}
		
		public void setPosition(double x, double y) {
			xtemp = x;
			ytemp = y;
			this.x = x;
			this.y = y;
		}
		
		private void getNextPosition() {
			nextx = xtemp + width / 2 + 30*Math.cos(Math.toRadians(richting));
			nexty = ytemp + height / 2 + 30*Math.sin(Math.toRadians(richting));
		}
		
		public void draw(java.awt.Graphics2D g){
			AffineTransform affineTransform = new AffineTransform();
		    affineTransform.rotate(Math.toRadians(richting), x + width - 10, y + height / 2);
		    affineTransform.translate(x, y);
		   
			g.drawImage(animation.getImage(), affineTransform, null);
			if(Game.showMarkers){
			g.setColor(Color.RED);
			g.fillRect((int) nextx + 1, (int) nexty, 1, 3);
			g.fillRect((int) nextx, (int) nexty + 1, 3, 1);
			
			g.setColor(Color.YELLOW);
			g.drawPolygon(polxpoints, polypoints, 3);
			}
			
		}
		
		public void update() {
			getNextPosition();
			setMovement();
			setPosition(xtemp, ytemp);
			if(!ingevoegd){
				if(getx() < 235 &&
					getx() > 43 &&
					gety() > 22 &&
					gety() < 208){
					oprotonde = true;
					
				richting += 1 * moveSpeed;
				}
				if(getx() < 187 &&
					getx() > 89 &&
					gety() > 68 &&
					gety() < 163){
					zichtveld = 20;
					ingevoegd = true;
				}
			}

			if((int)richting == 305 && Math.random() < 0.4 && ingevoegd){
				afgeslagen = true;
			}
			if((int)richting == 215 && Math.random() < 0.4 && ingevoegd){
				afgeslagen = true;
			}
			if((int)richting == 125 && Math.random() < 0.4 && ingevoegd){
				afgeslagen = true;
			}
			if((int)richting == 35 && Math.random() < 0.4 && ingevoegd){
				afgeslagen = true;
			}
			if(!afgeslagen && ingevoegd){
				richting -= 1 * moveSpeed;
			}
			if(afgeslagen) {
				if(		(int)richting != 0 &&
						(int)richting != 90 &&
						(int)richting != 180 &&
						(int)richting != 270){
				richting += 1 * moveSpeed;
				}
			}
			if(richting >= 360){
				richting = 0;
			}
			if(richting < 0){
				richting = 360;
			}
			
			if(moveSpeed < maxSpeed){
				moveSpeed += 0.02;
			}
			
			if(moveSpeed > maxSpeed){
				moveSpeed = maxSpeed;
			}
			
			if(moveSpeed <= 0){
				moveSpeed = 0;
			}

			animation.update();
			animation.setDelay(1000);
			
		}

		public void vertraag() {
			if(!(moveSpeed <= 0)){
			moveSpeed -= 0.04;
			}
		}
		
		public void remmen(double afstand) {
			int numTicks = (int) (afstand * maxSpeed);
			//if(numTicks <= 30){
				moveSpeed = maxSpeed / numTicks;
			//}
			
		}

		public double getZichtVeld() {
			return zichtveld;
		}

		
}
