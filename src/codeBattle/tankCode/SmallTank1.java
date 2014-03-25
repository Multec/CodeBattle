package codeBattle.tankCode;

import processing.core.PApplet;
import codeBattle.Tank;

public class SmallTank1 extends Tank{
	
	private int rot;
	public SmallTank1(PApplet app, String name) {
		super(app, name);
		// TODO Auto-generated constructor stub
	}

	public void run(){
		super.setRotation(rot);
		super.speed = 2;
		super.shoot();
		super.run();
		rot++;
	}
}
