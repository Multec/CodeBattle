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
	
	private float angle;
	
	private float angleInc = PI / 20;
	
	// ---------------------------------------------------------------------------------------------
	
	protected boolean shot = false;
	protected int health = 100;
	public int wait = 0;
	private boolean alive;
	private Bullet b;
	
	private TankImg t;
	
	// *********************************************************************************************
	// Constructors:
	// ---------------------------------------------------------------------------------------------
	
	public Tank(PApplet app, String name) {
		this.app = app;
		alive = true;
		xpos = app.random(980);
		ypos = app.random(700);
		this.name = name;
		t = new TankImg(this.app);
		
		speed = 1;
	}
	
	void init(float xpos, float ypos, float angle) {
		this.xpos = xpos;
		this.ypos = ypos;
		this.angle = angle;
	}
	
	// *********************************************************************************************
	// Accessors:
	// ---------------------------------------------------------------------------------------------
	
	public float getSpeed() {
		return speed;
	}
	
	public void increaseSpeed() {
		speed++;
	}
	
	public void decreaseSpeed() {
		speed--;
	}
	
	/**
	 * Call this method to rotate the tank to the left.
	 */
	public void rotateLeft() {
		angle -= angleInc;
	}
	
	/**
	 * Call this method to rotate the tank to the right.
	 */
	public void rotateRight() {
		angle += angleInc;
	}
	
	// *********************************************************************************************
	// Methods to implement in concrete Tank class:
	// ---------------------------------------------------------------------------------------------
	
	protected void move(TankMove move) {
		// implement in concrete Tank class
	}

	// *********************************************************************************************
	// Methods:
	// ---------------------------------------------------------------------------------------------
	
	public void draw() {
		if (alive) {
			if (health < 0) {
				alive = false;
			}
			if (wait == 0) {
				if (this.speed < 3) {
					speed = 3;
				}
				this.xpos = calculateXpos();
				this.ypos = calculateYpos();
			}
			else {
				wait--;
			}
			if (shot) {
				b.draw();
				if (b.xpos < 0 || b.ypos < 0 || b.xpos > 1024 || b.ypos > 768) {
					shot = false;
					b.die();
				}
			}
			
			t.draw(angle, 0, xpos, ypos, name);
		}
		
	}
	
	public void shoot() {
		if (shot) {
			
		}
		else {
			shot = true;
			b = new Bullet((int) (xpos), (int) (ypos), angle, app);			
		}
	}
	
//	public void setRotation(float rot) {
//		this.angle = (rot) * Math.PI / 180;
//		// this.rot = rot/100;
//	}
	
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
	
	public void setWait(int wait) {
		this.wait = wait;
	}
	
	public int getRot() {
		return (int) (Math.round(angle));
	}
	
	public String getName() {
		return name;
	}
	
	// public float getOtherTankX(){
	// return otherTankX;
	// }
	// public float getOtherTankY(){
	// return otherTankY;
	// }
	// public void setOtherX(float otherX){
	// this.otherTankX = otherX;
	// }
	// public void setOtherY(float otherY){
	// this.otherTankY = otherY;
	// }
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public void decreaseHealth(){
		this.health-=11;
	}
	
	public float getXPos() {
		return xpos;
	}
	
	public float getYPos() {
		return ypos;
	}
	
	public boolean getShot() {
		return this.shot;
	}
	
	public Bullet getB() {
		return b;
	}
	
	public boolean isAlive() {
		return alive;
	}
}
