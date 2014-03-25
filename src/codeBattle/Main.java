package codeBattle;
import codeBattle.tankCode.*;
import processing.core.*;

public class Main extends PApplet {
	int rot = 0;
	
	SmallTank1 tank1 = new SmallTank1(this, "tank1");
	SmallTank2 tank2 = new SmallTank2(this, "tank2");
	public RunTanks T1;
	public RunTanks T2;
	public void setup(){
		size(1024, 768);
		T1 = new RunTanks( "Thread-1", tank1);
	    T1.start();
		T2 = new RunTanks( "Thread-2", tank2);
	    T2.start();
	    tank2.xpos = 200;
	}
	public void draw(){
		background(255);
		T1.run();
		T2.run();
		if(tank1.shot){
			if(tank1.b.xpos > tank2.xpos && tank1.b.xpos < tank2.xpos+40 && tank1.b.ypos > tank2.ypos && tank1.b.ypos < tank2.ypos+35){
				tank2.health-=51;
				System.out.println("hit one");
				tank1.b.die();
				tank1.shot =false;
			}
		}
		if(tank2.shot){
			if(tank2.b.xpos > tank1.xpos && tank2.b.xpos < tank1.xpos+40 && tank2.b.ypos > tank1.ypos && tank2.b.ypos < tank1.ypos+35){
				tank1.health-=51;
				System.out.println("hit one");
				tank2.b.die();
				tank2.shot =false;
			}
		}
		
	}
}
