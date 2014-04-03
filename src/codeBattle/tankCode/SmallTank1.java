package codeBattle.tankCode;

import processing.core.PApplet;
import codeBattle.Tank;
import codeBattle.TankMove;

public class SmallTank1 extends Tank {
	
	private float t = 0;
	
	public SmallTank1(PApplet app, String name) {
		super(app, name);
		// TODO Auto-generated constructor stub
	}
	
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
		move.shoot();
	}
}
