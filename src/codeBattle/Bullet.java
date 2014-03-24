package codeBattle;

import processing.core.*;

public class Bullet {
	private double dir;
	private int speed = 10;
	public int xpos;
	public int ypos;
	public PApplet app;
	
	public Bullet(int xpos, int ypos, double dir, PApplet app){
		//this.dir = (dir * Math.PI / 180);
		this.dir = dir;
		this.xpos = xpos;
		this.ypos = ypos;
		this.app = app;
	}
	
	public void draw(){
		xpos = (int) (Math.round(Math.cos(dir) * speed + xpos));
		ypos = (int) (Math.round(Math.sin(dir) * speed + ypos));
		this.app.fill(0);
		this.app.rect(this.xpos, this.ypos, 3, 3);
		
	}
}