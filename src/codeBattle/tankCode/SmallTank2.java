package codeBattle.tankCode;

import processing.core.PApplet;
import codeBattle.Tank;

public class SmallTank2 extends Tank{
	
	private int rot;
	public SmallTank2(PApplet app, String name) {
		super(app, name);
		// TODO Auto-generated constructor stub
	}

	public void run(){
		super.setRotation(20);
		super.shoot();
		super.speed = 4;
		super.run();
	}
}
