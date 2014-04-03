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
	private float speed = 10;
	private float xpos;
	private float ypos;
	private PApplet app;
	
	// *********************************************************************************************
	// Constructors:
	// ---------------------------------------------------------------------------------------------
	
	public Bullet(float xpos, float ypos, float angle, PApplet app) {
		this.xpos = xpos;
		this.ypos = ypos;
		this.angle = angle;
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
		app.fill(0);
		app.pushMatrix();
		app.translate(xpos, ypos);
		app.rotate(angle);
		app.rect(-1, -1, 2, 6);
		app.popMatrix();
	}
	
//	public void die() {
//		alive = false;
//	}
	
}
