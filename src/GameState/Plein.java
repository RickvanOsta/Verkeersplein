package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.MenuElement;

import Entity.*;
import Main.Game;
import Main.GamePanel;

public class Plein extends GameState{
	
	private ArrayList<Auto> autos;
	
	public Plein(GameStateManager gsm) {
		this.gsm = gsm;
		init();
		
		autos = new ArrayList<Auto>();

	}

	public void init() {
		
	}
	
	public void voegAutoToe(){
		Auto a = new Auto();
		autos.add(a);
	}
	
	public void voegRaceAutoToe(){
		Raceauto r = new Raceauto();
		autos.add(r);
	}
	
	public void voegVrachtWagenToe(){
		Vrachtwagen v = new Vrachtwagen();
		autos.add(v);
	}
	
	public void checkRemove(){
		for(int i = 0; i < autos.size(); i++){
			if(autos.get(i).getTime() > 5000){
			if(autos.get(i).getx() < -50 ||
					autos.get(i).getx() > GamePanel.width + 50 ||
					autos.get(i).gety() < -50 ||
					autos.get(i).gety() > GamePanel.height + 50){
				autos.remove(i);
		}
		}
		}
	}
	
	public void checkCollision(){
			for(int i = 0; i < autos.size(); i++){
				for(int j = 0; j < autos.size(); j++){
					Auto deze = autos.get(i);
					Auto andere = autos.get(j);
					if(!(deze == (andere))){
						if(!deze.isAfgeslagen() || (deze.isAfgeslagen() && andere.isAfgeslagen())){
							if(!(deze.isOpRotonde() && !andere.isOpRotonde())){
								if(!(!deze.isOpRotonde() && andere.isAfgeslagen())){
									if(deze.afstandTussen(andere) < deze.getZichtVeld()){
										deze.remmen(deze.afstandTussen(andere));
									}
								}
							}
						}
					}
				}
			}
		}

	public void update() {
		if(Game.addAuto){
			voegAutoToe();
			Game.addAuto = false;
		}
		if(Game.addRaceAuto){
			voegRaceAutoToe();
			Game.addRaceAuto = false;
			}
		if(Game.addVrachtwagen){
			voegVrachtWagenToe();
			Game.addVrachtwagen = false;
			}
		if(Game.nieuwGame){
			autos.clear();
			Game.nieuwGame = false;
		}
		
		if(Game.isAutomatic){
		if(Math.random() < 0.003){
			voegAutoToe();
		}
		if(Math.random() < 0.003){
			voegVrachtWagenToe();
		}
		if(Math.random() < 0.003){
			voegRaceAutoToe();
		}
		}
		
		checkCollision();
		checkRemove();
		
		for(int i = 0; i < autos.size(); i++){
			autos.get(i).update();
		}
		
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN.darker());
		g.fillRect(0, 0, GamePanel.width, GamePanel.height);
		g.setColor(Color.GRAY);
		g.fillRect(0, 100, GamePanel.width, 50);
		g.fillRect(130, 0, 50, GamePanel.height);
		g.setColor(Color.WHITE);
		g.fillRect(0, 124, GamePanel.width, 2);
		g.fillRect(154, 0, 2, GamePanel.height);
		g.setColor(Color.GRAY);
		g.fillRoundRect(75, 45, 160, 160, 160, 160);
		g.setColor(Color.LIGHT_GRAY.darker());
		g.fillRoundRect(120, 90, 70, 70, 70, 70);
		g.setColor(Color.LIGHT_GRAY);
		g.fill3DRect(135, 115, 40, 20, true);
		
		for(int i = 0; i < autos.size(); i++){
			autos.get(i).draw(g);
		}
	}

	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

}
