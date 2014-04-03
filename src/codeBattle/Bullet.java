package codeBattle;

import processing.core.*;

public class Bullet {
	private double dir;
	private int speed = 10;
	public int xpos;
	public int ypos;
	public PApplet app;
	private boolean alive = true;
	public Bullet(int xpos, int ypos, double dir, PApplet app){
		this.dir = dir;
		this.xpos = xpos;
		this.ypos = ypos;
		this.app = app;
	}
	
	public void draw(){
		if(alive){
			xpos = (int) (Math.round(Math.cos(dir) * speed + xpos));
			ypos = (int) (Math.round(Math.sin(dir) * speed + ypos));
			this.app.fill(0);
			this.app.rect(this.xpos, this.ypos, 3, 3);
		}
		
	}
	public void die(){
		alive = false;
	}
}
