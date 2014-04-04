package codeBattle;

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
	
	private TankApp app;
	
	// ---------------------------------------------------------------------------------------------
	// private:
	
	private String name;
	
	private float xpos;
	private float ypos;
	
	private float speed;
	private float speedInc = .5f;
	private float speedMax = 5;
	
	private float angle = 0;
	private float angleInc = PI / 100;
	
	private int health = 0;
	private int healthDec = 10;
	
	private boolean alive = false;
	
	private int chargeLevel = 0;
	private int charged = 60;
	
	private PImage img;

	private String tankNaam = "naam";
	
	private Tank enemy;
	
	// *********************************************************************************************
	// Constructors:
	// ---------------------------------------------------------------------------------------------
	
	public Tank(TankApp app, String name) {
		this.app = app;
		this.name = name;
	}
	
	final void init(float xpos, float ypos, float angle, PImage img, Tank enemy) {
		this.xpos = xpos;
		this.ypos = ypos;
		this.angle = angle;
		this.img = img;
		this.enemy = enemy;
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
	
	final public TankApp getApp() {
		return app;
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
	
	final public boolean isAlive() {
		return alive;
	}
	
	// ---------------------------------------------------------------------------------------------
	
	final public float getEnemyXPos() {
		return enemy.xpos;
	}
	
	final public float getEnemyYPos() {
		return enemy.ypos;
	}
	
	// *********************************************************************************************
	// Modifiers for concrete Tank classes:
	// ---------------------------------------------------------------------------------------------
	// Call the methods
	
	/**
	 * Call this method to increase the speed of the tank. In each move the speed can be increased
	 * or decreased only once.
	 */
	final protected void increaseSpeed() {
		move_increaseSpeed = true;
		move_decreaseSpeed = false;
	}
	
	/**
	 * Call this method to decrease the speed of the tank. In each move the speed can be increased
	 * or decreased only once.
	 */
	final protected void decreaseSpeed() {
		move_increaseSpeed = false;
		move_decreaseSpeed = true;
	}
	
	/**
	 * Call this method to rotate the tank to the left.
	 */
	final protected void rotateLeft() {
		move_rotateLeft = true;
		move_rotateRight = false;
	}
	
	/**
	 * Call this method to rotate the tank to the right.
	 */
	final protected void rotateRight() {
		move_rotateLeft = false;
		move_rotateRight = true;
	}
	
	/**
	 * Call this method to fire a bullet when bullets are available.
	 */
	final protected void fire() {
		if (canFire()) {
			move_fire = true;
		}
	}
	final public void setNaam(String naam){
		this.name = naam;
	}
	
	// *********************************************************************************************
	// Methods to implement in concrete Tank class:
	// ---------------------------------------------------------------------------------------------
	
	protected void move() {
		// implement in concrete Tank class
	}
	
	// *********************************************************************************************
	// Modifiers:
	// ---------------------------------------------------------------------------------------------
	
	private boolean move_increaseSpeed = false;
	private boolean move_decreaseSpeed = false;
	private boolean move_rotateLeft = false;
	private boolean move_rotateRight = false;
	private boolean move_fire = false;
	
	/**
	 * @return True when the tanks wants to fire a shot and can fire a shot.
	 */
	final boolean fireBullet() {
		return move_fire;
	}
	
	final void applyMove() {
		if(speed>5){
			speed = 5;
		} else if(speed <-5){
			speed = -5;
		}
		if (move_increaseSpeed) speed = app.min(speed + speedInc, speedMax);
		else if (move_decreaseSpeed) speed = app.max(speed - speedInc, 0);
		if (move_rotateLeft) angle -= angleInc;
		else if (move_rotateRight) angle += angleInc;
	}
	
	final void resetMove() {
		move_increaseSpeed = false;
		move_decreaseSpeed = false;
		move_rotateLeft = false;
		move_rotateRight = false;
		move_fire = false;
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
			} else if(Math.abs(ypos-enemy.ypos)<70 && Math.abs(xpos-enemy.xpos)<70){
				speed = -1;
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
			app.textSize(16);
			app.noStroke();
			app.text(name, 0, 50);
			app.noFill();
			app.stroke(255, 204, 0);
			// app.ellipse(0, 0, 32, 32);
			app.popMatrix();
		}
	}
	
	final void firedShot() {
		chargeLevel = 0;
	}
	
}
