package codeBattle;
import codeBattle.tankCode.*;
import processing.core.*;

public class Main extends PApplet {
	int rot = 0;
	
	public SmallTank1 tank1 = new SmallTank1(this, "tank1");
	public SmallTank2 tank2 = new SmallTank2(this, "tank2");
	public RunTanks T1;
	public RunTanks T2;
	PImage back = null;
	PImage icon = null;
	public void setup(){
		size(1024, 768);
		T1 = new RunTanks( "Thread-1", tank1);
	    T1.start();
		T2 = new RunTanks( "Thread-2", tank2);
	    T2.start();
	    back = this.loadImage("/bg.png");
	    back.resize(1024, 768);
	    icon = this.loadImage("/tanklogo.png");
	}
	public void draw(){
		background(back);
		createShape(RECT, 0, 0, 51, 64);
		if(tank1.getAlive() && tank2.getAlive()){
			T1.run();
			T2.run();
			tank1.setOtherX(T2.getPosX());
			tank1.setOtherY(T2.getPosY());
			tank2.setOtherX(T1.getPosX());
			tank2.setOtherY(T1.getPosY());
			if(tank1.shot){
				if(tank1.getB().xpos > tank2.getXpos() && tank1.getB().xpos < tank2.getXpos()+40 && tank1.getB().ypos > tank2.getYpos() && tank1.getB().ypos < tank2.getYpos()+35){
					tank2.health-=51;
					System.out.println("hit one");
					tank1.getB().die();
					tank1.shot =false;
				}
			}
			if(tank2.shot){
				if(tank2.getB().xpos > tank1.getXpos() && tank2.getB().xpos < tank1.getXpos()+40 && tank2.getB().ypos > tank1.getYpos() && tank2.getB().ypos < tank1.getYpos()+35){
					tank1.health-=51;
					System.out.println("hit one");
					tank2.getB().die();
					tank2.shot =false;
				}
			}
		} else {
			imageMode(CENTER);
			image(icon, width/2, height/2);
			
		}
		
		
	}
}
