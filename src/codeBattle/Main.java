package codeBattle;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import codeBattle.tankCode.SmallTank1;
import codeBattle.tankCode.SmallTank2;

public class Main extends PApplet {
	
	// *********************************************************************************************
	// Attributes:
	// ---------------------------------------------------------------------------------------------
	
	private Tank tank1;
	private Tank tank2;
	
	// private TankRunner T1;
	// private TankRunner T2;
	
	private TankMove tankMove1;
	private TankMove tankMove2;
	
	private PImage bgImg;
	private PImage iconImg;
	
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private ArrayList<Tank> tanks = new ArrayList<Tank>();
	
	public int screenWidth = 1024;
	public int screenHeight = 768;
	
	private int framesEllapsed = 0;
	private int min = 0;
	private int sec = 0;
	
	PFont kremlin_32 = null;
	PFont kremlin_16 = null;
	
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
		tank1 = new SmallTank1(this, "Kim");
		tank1.init(100, 100, 0, loadImage("tank_korea.png"));
		tanks.add(tank1);
		
		tank2 = new SmallTank2(this, "Barack");
		tank2.init(width - 100, height - 100, PI, loadImage("tank_usa.png"));
		tanks.add(tank2);
		
		tankMove1 = new TankMove(tank1, tank2);
		tankMove2 = new TankMove(tank2, tank1);
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
			tank1.move(tankMove1);
			tank2.move(tankMove2);
			applyMove(tankMove1);
			applyMove(tankMove2);
			
			tank1.update(width, height);
			tank2.update(width, height);
			updateBullets();
			
//			if (tank1.shot) {
//				if (tank1.getB().xpos > tank2.getXPos() - 32
//						&& tank1.getB().xpos < tank2.getXPos() + 32
//						&& tank1.getB().ypos > tank2.getYPos() - 25
//						&& tank1.getB().ypos < tank2.getYPos() + 25)
//				{
//					tank2.decreaseHealth();
//					System.out.println("hit tank2");
//					tank1.getB().die();
//					tank1.shot = false;
//				}
//			}
			// if (tank2.shot) {
			// if (tank2.getB().xpos > tank1.getXPos() - 32
			// && tank2.getB().xpos < tank1.getXPos() + 32
			// && tank2.getB().ypos > tank1.getYPos()
			// && tank2.getB().ypos < tank1.getYPos() + 35)
			// {
			// tank2.decreaseHealth();
			// System.out.println("hit tank2");
			// tank1.getB().die();
			// tank1.shot = false;
			// }
			// }
			
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
	
	private void applyMove(TankMove move) {
		move.apply();
		if (move.fireBullet()) {
			Tank tank = move.getThisTank();
			Bullet bullet = new Bullet(tank.getXPos(), tank.getYPos(), tank.getAngle(), this);
			bullets.add(bullet);
			tank.firedShot();
		}
		move.reset();
	}
	
	private void updateBullets() {
		int i = 0;
		while (i < bullets.size()) {
			Bullet bullet = bullets.get(i);
			println(bullet);
			bullet.update();
			if (bullet.getXpos() < 0 || bullet.getXpos() > width || 
					bullet.getYpos() < 0 || bullet.getYpos() > height) {
				// the bullet is outside the screen: remove
				bullets.remove(i);
				continue;
			}
			
			boolean hit = false;
			for (Tank tank : tanks) {	
				if (dist(bullet.getXpos(), bullet.getYpos(), tank.getXPos(), tank.getYPos()) < 30) {
					// the bullet hit the tank:
					tank.decreaseHealth();
					println("- Tank " + tank.getName() + " was hit");
					hit = true;
				}
			}
			if (hit) {
				bullets.remove(i);
				continue;
			}
//			if (tank1.getB().xpos > tank2.getXPos() - 32
//					&& tank1.getB().xpos < tank2.getXPos() + 32
//					&& tank1.getB().ypos > tank2.getYPos() - 25
//					&& tank1.getB().ypos < tank2.getYPos() + 25)
//			{
//				tank2.decreaseHealth();
//				System.out.println("hit tank2");
//				tank1.getB().die();
//				tank1.shot = false;
//			}
			bullet.draw();
		}
	}
	
	public boolean sketchFullScreen() {
		return true;
	}
}
