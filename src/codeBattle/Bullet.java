package codeBattle;

import processing.core.PApplet;

/**
 * @author Jan Everaert
 * @author Wouter Van den Broeck
 */
final public class Bullet {
	
	// *********************************************************************************************
	// Attributes:
	// ---------------------------------------------------------------------------------------------
	
	private float angle;
	private float speed = 10f;
	private float xpos;
	private float ypos;
	private PApplet app;
	
	// *********************************************************************************************
	// Constructors:
	// ---------------------------------------------------------------------------------------------
	
	public Bullet(Tank tank, PApplet app) {
		angle = tank.getAngle();
		xpos = tank.getXPos() + app.cos(angle) * 35;
		ypos = tank.getYPos() + app.sin(angle) * 35;
		this.app = app;
	}
	
	// *********************************************************************************************
	// Accessors:
	// ---------------------------------------------------------------------------------------------
	
	/**
	 * @return the xpos
	 */
	public float getXpos() {
		return xpos;
	}
	
	/**
	 * @return the ypos
	 */
	public float getYpos() {
		return ypos;
	}
	
	// *********************************************************************************************
	// Methods:
	// ---------------------------------------------------------------------------------------------
	
	/**
	 * Update the position of the bullet.
	 */
	final void update() {
		xpos += Math.cos(angle) * speed;
		ypos += Math.sin(angle) * speed;
	}
	
	/**
	 * Draw the bullet.
	 */
	final void draw() {
		app.fill(0, 0, 0);
		app.noStroke();
		app.pushMatrix();
		app.translate(xpos, ypos);
		app.rotate(angle);
		//app.rect(-4,  -1, 8, 2);
		
		app.fill(255, 0, 0);
		app.rect(-4, -3, 8, -1);
		app.fill(255, 255, 0);
		app.rect(-4, -1, 8, -1);
		app.fill(0, 255, 255);
		app.rect(-4, 1, 8, -1);
		app.fill(0);
		app.popMatrix();
	}
	
}
