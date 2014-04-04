package codeBattle.tankCode;

import processing.core.PApplet;
import codeBattle.Main;
import codeBattle.Tank;
import codeBattle.TankApp;

public class DemoTankJan extends Tank {
	
	// *********************************************************************************************
	// Constructors:
	// ---------------------------------------------------------------------------------------------
	
	/**
	 * @param app pass this app to the super constructor
	 * @param name pass this name to the super constructor
	 */
	public DemoTankJan(TankApp app, String name) {
		super(app, name);
	}
	
	// *********************************************************************************************
	// Attributes:
	// ---------------------------------------------------------------------------------------------
	
	private float t = 0;
	private float lastPosX;
	private float lastPosY;
	private float lastPosXS;
	private float lastPosYS;
	float ox;
	float oy;
	private float ellapsed;
	private boolean timeToMove = false;
	
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
		setNaam("mcAwesome");
		PApplet app = getApp();
		ellapsed++;
		if(ellapsed>60){
			lastPosY = getEnemyYPos();
			lastPosX = getEnemyXPos();
			ox = getEnemyXPos();
			oy = getEnemyYPos();
			ellapsed = 0;
		} else {
			float movedX = (getEnemyXPos()/ellapsed)-lastPosX;
			float movedY = (getEnemyYPos()/ellapsed)-lastPosY;


			ox = getEnemyXPos()+movedX;
			oy = getEnemyYPos()+movedY;

		}

		if(timeToMove){
			rotateLeft();
			timeToMove = false;
			if (canFire()) {
				fire();
			}
		} else {
		float angleToShoot = 90-(float) (Math.atan2(ox-getXPos(), oy-getYPos()) * 180 / Math.PI);
		
		System.out.println(angleToShoot  + ", " + Math.toDegrees(getAngle()));
		if(angleToShoot>Math.toDegrees(getAngle())){
			rotateTurretRight();
			rotateRight();
		} else if(angleToShoot<Math.toDegrees(getAngle())){
			rotateLeft();
			rotateTurretLeft();
		} else {
			
		}
		//setRotation(angleToShoot);
		if (getSpeed() < 5) {
			increaseSpeed();
		}
		if (canFire()) {
			fire();
		}
		}
	}
}
