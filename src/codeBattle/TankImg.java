package codeBattle;


import processing.core.*;

public class TankImg {
	private PApplet app;
	PImage img_k = null;
	PImage img_u = null;
	
	public TankImg(PApplet app) {
		this.app = app;
		try {
			   img_k = app.loadImage("/tank_korea.png");
			   img_u = app.loadImage("/tank_usa.png");

			} catch (NullPointerException e) {
			    e.printStackTrace();
			   
			}
	}
	public void draw(double rot, int col, float xpos, float ypos, String name){
		app.pushMatrix();
		app.translate(xpos, ypos);
		app.rotate((float) rot+app.HALF_PI);
		if(name == "tank_k"){
			app.image(img_k, -32, -25, 51, 64);
		} else {
			app.image(img_u, -32, -25, 51, 64);
		}
		//app.fill(0, 50);
		//app.rect(-32, -25, 51, 64);
		app.popMatrix();
	}
}
