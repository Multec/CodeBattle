package codeBattle.tankCode;

import processing.core.PApplet;
import codeBattle.Tank;
import codeBattle.TankMove;

public class SmallTank1 extends Tank {

	// *********************************************************************************************
	// Attributes:
	// ---------------------------------------------------------------------------------------------
	
	private float t = 0;

	// *********************************************************************************************
	// Constructors:
	// ---------------------------------------------------------------------------------------------
	
	/**
	 * @param app pass this app to the super constructor
	 * @param name pass this name to the super constructor
	 */
	public SmallTank1(PApplet app, String name) {
		super(app, name);
	}
	
	// *********************************************************************************************
	// Methods:
	// ---------------------------------------------------------------------------------------------
	
	/*
	 * @see codeBattle.Tank#move(codeBattle.TankMove)
	 */
	protected void move(TankMove move) {
		t += .01;
		if (app.noise(t) < .5) {
			move.rotateLeft();
		}
		else {
			move.rotateRight();
		}
		if (move.getThisSpeed() < 4) {
			move.increaseSpeed();
		}
		if (canFire()) {
			move.fire();
		}
	}
}
