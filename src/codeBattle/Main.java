package codeBattle;

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
		
		kremlin_32 = loadFont("/Kremlin-32.vlw");
		kremlin_16 = loadFont("/Kremlin-16.vlw");
		
		// initialize the tanks:
		tank1 = new SmallTank1(this, "tank1");
		tank1.init(50, 50, 0);
		
		tank2 = new SmallTank2(this, "tank2");
		tank2.init(width - 50, height - 50, PI);
		
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
			
			// update the tanks:
			tank1.move(tankMove1);
			tank2.move(tankMove2);
			tankMove1.applyMove();
			tankMove2.applyMove();
			
			framesEllapsed++;
			
			if (tank1.shot) {
				if (tank1.getB().xpos > tank2.getXPos() - 32
						&& tank1.getB().xpos < tank2.getXPos() + 32
						&& tank1.getB().ypos > tank2.getYPos() - 25
						&& tank1.getB().ypos < tank2.getYPos() + 25)
				{
					tank2.decreaseHealth();
					System.out.println("hit tank2");
					tank1.getB().die();
					tank1.shot = false;
				}
			}
			if (tank2.shot) {
				if (tank2.getB().xpos > tank1.getXPos() - 32
						&& tank2.getB().xpos < tank1.getXPos() + 32
						&& tank2.getB().ypos > tank1.getYPos()
						&& tank2.getB().ypos < tank1.getYPos() + 35)
				{
					tank2.decreaseHealth();
					System.out.println("hit tank2");
					tank1.getB().die();
					tank1.shot = false;
				}
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
			
			tank1.draw();
			tank2.draw();
		}
		
		textFont(kremlin_16);
		fill(32, 32, 32);
		noStroke();
		rect(10, 10, (float) (tank1.getHealth() * 4.6), 20);
		rect((float) ((width - 10) - tank2.getHealth() * 4.6), 10,
				(float) (tank2.getHealth() * 4.6), 20);
		textSize(20);
		text(min + ":", 490, 28);
		text(sec, 520, 28);
		
	}
	
	public boolean sketchFullScreen() {
		return true;
	}
}
