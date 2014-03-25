package codeBattle;

import processing.core.PApplet;
import processing.core.PShape;

public class TankImg {
	private PApplet app;
	private boolean found = false;
	PShape tank = new PShape();
	
	public TankImg(PApplet app) {
		this.app = app;
		try{
			tank = app.loadShape("/tank.svg");
			found = true;
		} catch (NullPointerException e){
			System.out.println("tank doesn't exist");
		}
	}
	public void draw(double rot, int col, float xpos, float ypos){
		if(found){
			app.shape(tank, xpos, ypos);
			tank.rotate((float)rot);
		} else {
			app.fill(0);
			app.rect(xpos, ypos, 35, 45);
		}
	}
}
