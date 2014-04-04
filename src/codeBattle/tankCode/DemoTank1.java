package codeBattle.tankCode;

import processing.core.PApplet;
import codeBattle.TankApp;
import codeBattle.Tank;

public class DemoTank1 extends Tank {
	
	// *********************************************************************************************
	// Constructors:
	// ---------------------------------------------------------------------------------------------
	
	/**
	 * @param app pass this app to the super constructor
	 * @param name pass this name to the super constructor
	 */
	public DemoTank1(TankApp app, String name) {
		super(app, name);
	}
	
	// *********************************************************************************************
	// Attributes:
	// ---------------------------------------------------------------------------------------------
	
	private float t = 0;
	
	// *********************************************************************************************
	// Methods:
	// ---------------------------------------------------------------------------------------------

	/**
	 * Use the following methods to manipulate this tank:
	 * <ul>
	 * <li>increaseSpeed</li>
	 * <li>decreaseSpeed</li>
	 * <li>rotateLeft</li>
	 * <li>rotateRight</li>
	 * <li>fire</li>
	 * </ul>
	 * 
	 * Use the following accessors to get information about this tank:
	 * <ul>
	 * <li>getSpeed</li>
	 * <li>getAngle</li>
	 * <li>getXPos</li>
	 * <li>getYPos</li>
	 * <li>canFire</li>
	 * <li>getHealth</li>
	 * <li>etc.</li>
	 * </ul>
	 * 
	 * Use the following accessors to get information about the enemy tank:
	 * <ul>
	 * <li>getEnemyXPos</li>
	 * <li>getEnemyYPos</li>
	 * </ul>
	 * 
	 * @see codeBattle.Tank#move()
	 */
	protected void move() {
		PApplet app = getApp();
		
		t += .01;
		if (app.noise(t) < .5) {
			rotateLeft();
		}
		else {
			rotateRight();
		}
		if (getSpeed() < 4) {
			increaseSpeed();
		}
		if (canFire()) {
			fire();
		}
	}
}
