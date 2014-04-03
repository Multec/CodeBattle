package codeBattle.tankCode;

import processing.core.PApplet;
import codeBattle.Tank;
import codeBattle.TankMove;

public class SmallTank2 extends Tank {
	
	private float t = 0;
	
	public SmallTank2(PApplet app, String name) {
		super(app, name);
	}
	
	protected void move(TankMove move) {
		t -= .02;
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
