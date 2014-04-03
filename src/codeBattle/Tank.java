package codeBattle;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

/**
 * @author Jan Everaert
 * @author Wouter Van den Broeck
 * 
 */
public class Tank implements PConstants {
	
	// *********************************************************************************************
	// Attributes:
	// ---------------------------------------------------------------------------------------------
	
	protected PApplet app;
	
	// ---------------------------------------------------------------------------------------------
	// private:
	
	private String name;
	
	private float xpos;
	private float ypos;
	
	private float speed;
	
	private float angle = 0;
	private float angleInc = PI / 50;
	
	private int health = 0;
	private int healthDec = 10;
	
	private boolean alive = false;
	
	private int chargeLevel = 0;
	private int charged = 30;
	
	// ---------------------------------------------------------------------------------------------
	
	private Bullet b;
	
	private PImage img;
	
	// *********************************************************************************************
	// Constructors:
	// ---------------------------------------------------------------------------------------------
	
	public Tank(PApplet app, String name) {
		this.app = app;
		this.name = name;
	}
	
	void init(float xpos, float ypos, float angle, PImage img) {
		this.xpos = xpos;
		this.ypos = ypos;
		this.angle = angle;
		this.img = img;
		alive = true;
		speed = 0;
		health = 100;
		chargeLevel = 0;
	}
	
	// *********************************************************************************************
	// Accessors:
	// ---------------------------------------------------------------------------------------------
	
	final public String getName() {
		return name;
	}
	
	final public float getXPos() {
		return xpos;
	}
	
	final public float getYPos() {
		return ypos;
	}
	
	final public float getSpeed() {
		return speed;
	}
	
	final public float getAngle() {
		return angle;
	}
	
	final public int getHealth() {
		return this.health;
	}
	
	final public boolean canFire() {
		return chargeLevel == charged;
	}
	
	// *********************************************************************************************
	// Methods to implement in concrete Tank class:
	// ---------------------------------------------------------------------------------------------
	
	protected void move(TankMove move) {
		// implement in concrete Tank class
	}
	
	// *********************************************************************************************
	// Modifiers:
	// ---------------------------------------------------------------------------------------------
	
	final void increaseSpeed() {
		speed++;
	}
	
	final void decreaseSpeed() {
		speed--;
	}
	
	/**
	 * Call this method to rotate the tank to the left.
	 */
	final void rotateLeft() {
		angle -= angleInc;
	}
	
	/**
	 * Call this method to rotate the tank to the right.
	 */
	final void rotateRight() {
		angle += angleInc;
	}
	
	final void decreaseHealth() {
		this.health -= healthDec;
	}
	
	// *********************************************************************************************
	// Methods:
	// ---------------------------------------------------------------------------------------------
	
	final void update(int width, int height) {
		if (alive) {
			if (health <= 0) {
				alive = false;
			}
			
			// this.xpos = calculateXpos();
			xpos += app.cos(angle) * speed;
			if (xpos < 32) {
				xpos = 32;
				speed = 0;
			}
			else if (xpos > width - 32) {
				xpos = width - 32;
				speed = 0;
			}
			
			ypos += app.sin(angle) * speed;
			if (ypos < 32) {
				ypos = 32;
				speed = 0;
			}
			else if (ypos > height - 32) {
				ypos = height - 32;
				speed = 0;
			}
			
			if (chargeLevel < charged) {
				chargeLevel++;
			}
		}
	}
	
	final void draw() {
		if (alive) {
			app.pushMatrix();
			app.translate(xpos, ypos);
			app.rotate(angle + HALF_PI);
			app.image(img, -25, -39);
			app.noFill();
			app.stroke(255, 204, 0);
			//app.ellipse(0, 0, 32, 32);
			app.popMatrix();
		}
	}
	
	void firedShot() {
		chargeLevel = 0;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
}
