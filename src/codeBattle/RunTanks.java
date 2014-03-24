package codeBattle;

public class RunTanks extends Thread{
	private Tank curTank;
	private int rot;
	RunTanks( String name, Tank tank){
		System.out.println("Creating " +  name + " with tank " + tank.getName());
		curTank = tank;
		rot = tank.getRot();
	}
	public void run() {
		System.out.println("running with tank " + curTank.getName());
		
		curTank.run();
		
	}
	 public void start (){
		System.out.println("starting thread");
		curTank.wait = 30;
		 
	 }
}
