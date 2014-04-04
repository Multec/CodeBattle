package codeBattle.tankCode;

import processing.core.PApplet;
import codeBattle.Main;
import codeBattle.Tank;

public class DemoTankJan extends Tank {
	
	// *********************************************************************************************
	// Constructors:
	// ---------------------------------------------------------------------------------------------
	
	/**
	 * @param app pass this app to the super constructor
	 * @param name pass this name to the super constructor
	 */
	public DemoTankJan(Main app, String name) {
		super(app, name);
	}
	
	// *********************************************************************************************
	// Attributes:
	// ---------------------------------------------------------------------------------------------
	
	private float t = 0;
	private float lastPosX;
	private float lastPosY;
	float ox;
	float oy;
	private float ellapsed;
	
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
		ellapsed++;
		if(ellapsed>30){
			lastPosY = getEnemyYPos();
			lastPosX = getEnemyXPos();

			ox = getEnemyXPos();
			oy = getEnemyYPos();
		} else {
			float movedX = (getEnemyXPos()/ellapsed)-lastPosX;
			float movedY = (getEnemyYPos()/ellapsed)-lastPosY;


			ox = getEnemyXPos()+movedX;
			oy = getEnemyYPos()+movedY;

		}

		float angleToShoot = 90-(float) (Math.atan2(ox-getXPos(), oy-getYPos()) * 180 / Math.PI);
		
		System.out.println(angleToShoot  + ", " + Math.toDegrees(getAngle()));
		if(angleToShoot>Math.toDegrees(getAngle())){
			rotateRight();
		} else if(angleToShoot<Math.toDegrees(getAngle())){
			rotateLeft();
		} else {
			
		}
		//setRotation(angleToShoot);
		if (getSpeed() < 4) {
			increaseSpeed();
		}
		if (canFire()) {
			fire();
		}
	}
}
