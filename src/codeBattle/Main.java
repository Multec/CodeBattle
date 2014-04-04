package codeBattle;

import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import processing.core.PMatrix;
import processing.core.PMatrix2D;
import processing.core.PMatrix3D;

public class Main {
	
	// *********************************************************************************************
	// Attributes:
	// ---------------------------------------------------------------------------------------------
	
	public static Color bgColor = new Color(0);
	
	public static int screenWidth = 1024;
	public static int screenHeight = 768;
	
	// *********************************************************************************************
	// Main method:
	// ---------------------------------------------------------------------------------------------
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TankApp app = new TankApp();
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		System.out.println("\nDisplays info:");
		int displayCnt = ge.getScreenDevices().length;
		System.out.println("- There are " + displayCnt + " displays.");
		for (int i = 0; i < displayCnt; i++) {
			GraphicsDevice display = ge.getScreenDevices()[i];
			GraphicsConfiguration gc = display.getDefaultConfiguration();
			System.out.println("- Display " + i + ":");
			printBounds("  - bounds: ", gc.getBounds());
			System.out.println("  - isFullScreenSupported: " + display.isFullScreenSupported());
		}
		if (displayCnt > 1) {
			app.openFullscreen(1, bgColor);
		}
		else {
			app.open("TANKS - CODE TO LIVE", screenWidth, screenHeight, bgColor);
		}
		app.getFrame().toFront();
	}

	// *********************************************************************************************
	// Debug utilities:
	// ---------------------------------------------------------------------------------------------
	
	protected void printMatrix(PMatrix matrix) {
		if (matrix.getClass() == PMatrix2D.class) ((PMatrix2D) matrix).print();
		else if (matrix.getClass() == PMatrix3D.class) ((PMatrix3D) matrix).print();
	}
	
	public static void printBounds(Rectangle bounds) {
		printBounds("- bounds: ", bounds);
	}
	
	public static void printBounds(String prefix, Rectangle bounds) {
		System.out.println(prefix + bounds.x + ", " + bounds.y + ", " + bounds.width + ", "
				+ bounds.height);
	}
	
}
