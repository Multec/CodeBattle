package codeBattle;


import processing.core.*;

public class TankImg {
	private PApplet app;
	PImage img = null;
	
	public TankImg(PApplet app) {
		this.app = app;
		try {
			   img = app.loadImage("/tank.png");

			} catch (NullPointerException e) {
			    e.printStackTrace();
			   
			}
	}
	public void draw(double rot, int col, float xpos, float ypos){
		app.pushMatrix();
		app.translate(xpos, ypos);
		app.rotate((float) rot+app.HALF_PI);
		app.image(img, -32, -25, 51, 64);
		app.fill(0, 50);
		app.rect(-32, -25, 51, 64);
		app.popMatrix();
	}
}
