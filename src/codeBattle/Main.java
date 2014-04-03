package codeBattle;

import codeBattle.tankCode.*;
import processing.core.*;

public class Main extends PApplet {
	int rot = 0;
	
	private SmallTank1 tank1 = new SmallTank1(this, "tank_k");
	private SmallTank2 tank2 = new SmallTank2(this, "tank_u");
	private RunTanks T1;
	private RunTanks T2;
	private int framesEllapsed = 0;
	private int min = 0;
	private int sec = 0;
	PImage back = null;
	PImage icon = null;
	PFont kremlin_32 = null;
	PFont kremlin_16 = null;
	
	public void setup(){
		size(1024, 768);
		
		T1 = new RunTanks( "Thread-1", tank1);
	    T1.start();
		T2 = new RunTanks( "Thread-2", tank2);
	    T2.start();
	    back = this.loadImage("/bg.png");
	    back.resize(1024, 768);
	    icon = this.loadImage("/tanklogo.png");
	    frameRate(30);
	    kremlin_32 = loadFont("/Kremlin-32.vlw");
	    kremlin_16 = loadFont("/Kremlin-16.vlw");
	   
	}
	public void draw(){
		
		background(back);
		if(framesEllapsed>=30){
			sec++;
			framesEllapsed = 0;
		}
		if(sec>=60){
			min++;
			sec = 0;
		}
		if(tank1.getAlive() && tank2.getAlive()){
			framesEllapsed++;
			T1.run();
			T2.run();
			tank1.setOtherX(T2.getPosX());
			tank1.setOtherY(T2.getPosY());
			tank2.setOtherX(T1.getPosX());
			tank2.setOtherY(T1.getPosY());
			
			if(tank2.shot){
				if(tank2.getB().xpos > tank1.getXpos()-32 && tank2.getB().xpos < tank1.getXpos()+32 && tank2.getB().ypos > tank1.getYpos()-25 && tank2.getB().ypos < tank1.getYpos()+25){
					tank1.decreaseHealth();
					System.out.println("hit tank1");
					tank2.getB().die();
					tank2.shot =false;
				}
			}
			if(tank1.shot){
				if(tank1.getB().xpos > tank2.getXpos()-32 && tank1.getB().xpos < tank2.getXpos()+32 && tank1.getB().ypos > tank2.getYpos()-25 && tank1.getB().ypos < tank2.getYpos()+25){
					tank2.decreaseHealth();
					System.out.println("hit tank2");
					tank1.getB().die();
					tank1.shot =false;
				}
			}
		} else {
			
			textFont(kremlin_32);
			imageMode(CENTER);
			image(icon, width/2, height/2);
			if(tank1.getHealth()>0){
				fill(32, 32, 32);
				textSize(30);
				textAlign(CENTER);
				text("tank 1 won", 512, 520);
			}
			if(tank2.getHealth()>0){
				fill(32, 32, 32);
				textSize(30);
				textAlign(CENTER);
				text("tank 2 won", 512, 520);
			}
			
		}

		textFont(kremlin_16);
		fill(32, 32, 32);
		noStroke();
		rect(10, 10, (float) (tank1.getHealth()*4.6), 20);
		rect((float) ((width-10)-tank2.getHealth()*4.6), 10, (float) (tank2.getHealth()*4.6), 20);
		textSize(20);
		text(min+":", 490, 28);
		text(sec, 520, 28);
		
		
	}
	public boolean sketchFullScreen() {
		  return true;
		}
}
