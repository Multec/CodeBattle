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
	public boolean shot = false;
	public int health = 100;
	public float xpos;
	public float ypos;
	public int wait = 0;
	public boolean alive;
	public Bullet b;
	private String name;
	
	TankImg t;
	
	public Tank(PApplet app, String name) {
		this.app = app;
		alive = true;
		xpos = 0;
		ypos = 0;
		this.name = name;
		t = new TankImg(this.app);

		speed = 1;
		
	}
	public void draw(){
		
	}
	public void run(){
		if(alive){
			if(health<0){
				alive = false;
			}
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
					b.die();
				}
			}
			t.draw(rot, 0, xpos, ypos);
			
		}
		
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
		float x = (Math.round(Math.cos(rot) * speed + xpos));
		if(x>980){
			this.speed = 0;
			return 980;
		} else if(x<0){
			this.speed = 0;
			return 0;
		} else {
			return x;
		}
	}
	private float calculateYpos(){
		float y = (Math.round(Math.sin(rot) * speed + ypos));
		if(y>768){
			this.speed = 0;
			return 768;
		} else if(y<0){
			this.speed = 0;
			return 0;
		} else {
			return y;
		}
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
