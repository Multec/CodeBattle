/**
 * 
 */
package codeBattle;
import processing.core.*;

/**
 
 * @author janeveraert
 *
 */
public class Tank {
	PApplet app;
	public float speed;
	public double rot;
	private boolean shot = false;
	public int health;
	public float xpos;
	public float ypos;
	public int wait = 0;
	public boolean alive;
	private boolean found = false;
	private Bullet b;
	PShape tank = new PShape();
	private String name;
	
	public Tank(PApplet app, String name) {
		this.app = app;
		alive = true;
		xpos = 0;
		ypos = 0;
		this.name = name;

		speed = 1;
		try{
			tank = app.loadShape("data/tank.svg");
			found = true;
		} catch (NullPointerException e){
			System.out.println("tank doesn't exist");
		}
	}
	public void draw(){
		
	}
	public void run(){
		if(alive){
			if(wait == 0){
				if(this.speed <3){
					speed = 3;
				}
				this.xpos = calculateXpos();
				this.ypos = calculateYpos();
			} else {
				wait--;
			}
			if(shot){
				b.draw();
				if(b.xpos <0 || b.ypos<0 || b.xpos >1024 || b.ypos>768){
					shot = false;
				}
			}
			if(found){
				app.shape(tank, this.xpos, this.ypos);
			} else {
				this.app.fill(0);
				this.app.rect(this.xpos, this.ypos, 35, 45);
			}
		}
		System.out.println("running");
	}
	public void shoot(){
		if(shot){
			
		} else {
			shot = true;
			b = new Bullet((int)(xpos), (int)(ypos), rot, app);
			
		}
		
	}
	public void setRotation(float rot){
		this.rot = rot * Math.PI / 180;
		//this.rot = rot/100;
	}
	
	public void setSpeed(float speed){
		this.speed = speed;
	}
	private float calculateXpos(){
		return  (Math.round(Math.cos(rot) * speed + xpos));
	}
	private float calculateYpos(){
		return (Math.round(Math.sin(rot) * speed + ypos));
	}
	public void setWait(int wait){
		this.wait = wait;
	}
	public int getRot(){
		return (int)(Math.round(rot));
	}
	public String getName(){
		return name;
	}
	
}
