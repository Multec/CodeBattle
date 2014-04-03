package codeBattle;

/**
 * @author Wouter Van den Broeck
 * @author Jan Everaert
 */
public class TankMove {
	
	// *********************************************************************************************
	// Attributes:
	// ---------------------------------------------------------------------------------------------
	
	private boolean rotateLeft = false;
	private boolean rotateRight = false;
	private boolean increaseSpeed = false;
	private boolean decreaseSpeed = false;
	private boolean shoot = false;
	private Tank thisTank;
	private Tank otherTank;
	
	// *********************************************************************************************
	// Constructors:
	// ---------------------------------------------------------------------------------------------
	
	public TankMove(Tank thisTank, Tank otherTank) {
		this.thisTank = thisTank;
		this.otherTank = otherTank;
	}
	
	// *********************************************************************************************
	// Methods to get information from this tank.
	// ---------------------------------------------------------------------------------------------
	
	public float getThisSpeed() {
		return thisTank.getSpeed();
	}
	
	public float getThisAngle() {
		return thisTank.getAngle();
	}
	
	public float getThisXPos() {
		return thisTank.getXPos();
	}
	
	public float getThisYPos() {
		return thisTank.getYPos();
	}
	
	// *********************************************************************************************
	// Methods to get information from this tank.
	// ---------------------------------------------------------------------------------------------
	
	public float getOtherXPos() {
		return otherTank.getXPos();
	}
	
	public float getOtherYPos() {
		return otherTank.getYPos();
	}

	// public float getOtherSpeed() { return otherTank.getSpeed(); }
	
	// public float getOtherAngle() { return otherTank.getAngle(); }
	
	// *********************************************************************************************
	// Methods for manipulating this tank.
	// ---------------------------------------------------------------------------------------------
	
	/**
	 * Call this method to rotate the tank to the left.
	 */
	public void rotateLeft() {
		rotateLeft = true;
		rotateRight = false;
	}
	
	/**
	 * Call this method to rotate the tank to the right.
	 */
	public void rotateRight() {
		rotateLeft = false;
		rotateRight = true;
	}
	
	/**
	 * Call this method to increase the speed of the tank. In each move the speed can be increased
	 * or decreased only once.
	 */
	public void increaseSpeed() {
		increaseSpeed = true;
		decreaseSpeed = false;
	}
	
	/**
	 * Call this method to decrease the speed of the tank. In each move the speed can be increased
	 * or decreased only once.
	 */
	public void decreaseSpeed() {
		increaseSpeed = false;
		decreaseSpeed = true;
	}
	
	public void shoot() {
		shoot = true;
	}
	
	// *********************************************************************************************
	// Methods to be called by the system.
	// ---------------------------------------------------------------------------------------------
	
	void applyMove() {
		
		// apply the move;
		if (rotateLeft) thisTank.rotateLeft();
		else if (rotateRight) thisTank.rotateRight();
		if (increaseSpeed) thisTank.increaseSpeed();
		else if (decreaseSpeed) thisTank.decreaseSpeed();
		
		// reset move:
		rotateLeft = false;
		rotateRight = false;
		increaseSpeed = false;
		decreaseSpeed = false;
		shoot = false;
	}
	
	/**
	 * @return True when the tanks wants to fire a shot and can fire a shot.
	 */
	boolean fireBullet() {
		return shoot && thisTank.canShoot();
	}
	
	Tank getThisTank() {
		return thisTank;
	}
	
}
