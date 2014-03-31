package codeBattle;

public class RunTanks extends Thread{
	private Tank curTank;
	private int rot;
	RunTanks( String name, Tank tank){
		System.out.println("Creating " +  name + " with " + tank.getName());
		curTank = tank;
		setRot(tank.getRot());
	}
	public void run() {
		curTank.run();
		
	}
	 public void start (){
		System.out.println("starting thread");
		curTank.wait = 30;
		 
	 }
	public int getRot() {
		return rot;
	}
	public void setRot(int rot) {
		this.rot = rot;
	}
	public float getPosX(){
		return curTank.xpos;
	}
	public float getPosY(){
		return curTank.ypos;
	}
}
