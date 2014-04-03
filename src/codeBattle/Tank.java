package codeBattle;

import processing.core.PApplet;
import processing.core.PConstants;

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
	
	private TankImg tankImg;
	
	// *********************************************************************************************
	// Constructors:
	// ---------------------------------------------------------------------------------------------
	
	public Tank(PApplet app, String name) {
		this.app = app;
		this.name = name;
		tankImg = new TankImg(this.app);
	}
	
	void init(float xpos, float ypos, float angle) {
		this.xpos = xpos;
		this.ypos = ypos;
		this.angle = angle;
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
	
	final public boolean canShoot() {
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

	final void decreaseHealth(){
		this.health -= healthDec;
	}
	
	// *********************************************************************************************
	// Methods:
	// ---------------------------------------------------------------------------------------------
	
	final void update() {
		if (alive) {
			if (health <= 0) {
				alive = false;
			}
			this.xpos = calculateXpos();
			this.ypos = calculateYpos();
			
			if (chargeLevel < charged) chargeLevel++;
		}
	}
	
	final void draw() {
		if (alive) {
			tankImg.draw(angle, 0, xpos, ypos, name);
		}
	}
	
	void firedShot() {
		chargeLevel = 0;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	private float calculateXpos() {
		float x = (Math.round(Math.cos(angle) * speed + xpos));
		if (x > 980) {
			this.speed = 0;
			return 980;
		}
		else if (x < 0) {
			this.speed = 0;
			return 0;
		}
		else {
			return x;
		}
	}
	
	private float calculateYpos() {
		float y = (Math.round(Math.sin(angle) * speed + ypos));
		if (y > 768) {
			this.speed = 0;
			return 768;
		}
		else if (y < 0) {
			this.speed = 0;
			return 0;
		}
		else {
			return y;
		}
	}
	
	public Bullet getB() {
		return b;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
}
