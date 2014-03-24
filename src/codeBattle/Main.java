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
	}
	public void draw(){
		background(255);
		T1.run();
		T2.run();
		
	}
}
