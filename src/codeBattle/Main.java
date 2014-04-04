package codeBattle;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import codeBattle.tankCode.DemoTank1;
import codeBattle.tankCode.DemoTank2;

public class Main extends PApplet {
	
	// *********************************************************************************************
	// Attributes:
	// ---------------------------------------------------------------------------------------------
	
	private Tank tank1;
	private Tank tank2;
	
	private PImage bgImg;
	private PImage iconImg;
	
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private ArrayList<Tank> tanks = new ArrayList<Tank>();
	
	public int screenWidth = 1024;
	public int screenHeight = 768;
	
	private int framesEllapsed = 0;
	private int min = 0;
	private int sec = 0;
	
	private PFont kremlin_32 = null;
	private PFont kremlin_16 = null;
	
	// *********************************************************************************************
	// Methods:
	// ---------------------------------------------------------------------------------------------
	
	public void setup() {
		size(screenWidth, screenHeight);
		frameRate(30);
		
		bgImg = this.loadImage("bg.png");
		bgImg.resize(screenWidth, screenHeight);
		iconImg = this.loadImage("tanklogo.png");
		
		kremlin_32 = loadFont("Kremlin-32.vlw");
		kremlin_16 = loadFont("Kremlin-16.vlw");
		
		// initialize the tanks:
		tank1 = new DemoTank1(this, "Kim");
		tank2 = new DemoTank2(this, "Barack");
		
		tank1.init(100, 100, 0, loadImage("tank_korea.png"), tank2);
		tank2.init(width - 100, height - 100, PI, loadImage("tank_usa.png"), tank1);
		
		tanks.add(tank1);
		tanks.add(tank2);
	}
	
	public void draw() {
		background(bgImg);
		
		if (framesEllapsed >= 30) {
			sec++;
			framesEllapsed = 0;
		}
		if (sec >= 60) {
			min++;
			sec = 0;
		}
		
		if (tank1.isAlive() && tank2.isAlive()) {
			
			framesEllapsed++;
			
			// update the tanks:
			tank1.move();
			tank2.move();
			applyMove(tank1);
			applyMove(tank2);
			
			tank1.update(width, height);
			tank2.update(width, height);
			updateBullets();
			
			tank1.draw();
			tank2.draw();
			for (Bullet bullet : bullets) {
				bullet.draw();
			}
		}
		else {
			textFont(kremlin_32);
			imageMode(CENTER);
			image(iconImg, width / 2, height / 2);
			if (tank1.getHealth() > 0) {
				fill(32, 32, 32);
				textSize(30);
				textAlign(CENTER);
				text("tank 1 won", 512, 520);
			}
			if (tank2.getHealth() > 0) {
				fill(32, 32, 32);
				textSize(30);
				textAlign(CENTER);
				text("tank 2 won", 512, 520);
			}
		}
		
		textFont(kremlin_16);
		fill(32, 32, 32);
		noStroke();
		rect(10, 10, tank1.getHealth() * 4.6f, 20);
		rect((width - 10) - tank2.getHealth() * 4.6f, 10, tank2.getHealth() * 4.6f, 20);
		textSize(20);
		text(min + ":", 490, 28);
		text(sec, 520, 28);
	}
	
	private void applyMove(Tank tank) {
		tank.applyMove();
		if (tank.fireBullet()) {
			Bullet bullet = new Bullet(tank, this);
			bullets.add(bullet);
			tank.firedShot();
		}
		tank.resetMove();
	}
	
	private void updateBullets() {
		int i = 0;
		while (i < bullets.size()) {
			Bullet bullet = bullets.get(i);
			bullet.update();
			if (outsideScreen(bullet.getXpos(), bullet.getYpos())) {
				// the bullet is outside the screen: remove
				bullets.remove(i);
				continue;
			}
			
			boolean hit = false;
			for (Tank tank : tanks) {
				if (dist(bullet.getXpos(), bullet.getYpos(), tank.getXPos(), tank.getYPos()) < 30) {
					// the bullet hit the tank:
					// println("- Tank " + tank.getName() + " was hit");
					tank.decreaseHealth();
					hit = true;
				}
			}
			if (hit) {
				bullets.remove(i);
				continue;
			}
			bullet.draw();
			i++;
		}
	}
	
	public boolean sketchFullScreen() {
		return true;
	}
	
	private boolean outsideScreen(float x, float y) {
		return x < 0 || x > width || y < 0 || y > height;
	}
}
