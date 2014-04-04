package codeBattle.eventHandlers;

import java.awt.Frame;

import codeBattle.AppWindow;

/**
 * An event dispatched by the AppWindow class.
 * 
 * @see AppWindow
 * 
 * @author Wouter Van den Broeck
 */
public abstract class SGWindowEventHandler {
	
	// *********************************************************************************************
	// Methods:
	// ---------------------------------------------------------------------------------------------
	
	/**
	 * Called when the underlying Java AWT Frame object has been initialized but before it will be
	 * made visible. This method can be overridden in order to apply frame configurations that must
	 * be applied before the frame is made visible.
	 * 
	 * @param frame the underlying Java AWT Frame object
	 */
	public void frameInitialized(Frame frame) {}
	
	/**
	 * Called the first time a window is made visible, before the application is started, i.e.
	 * before the setup() method is called. This method can be overridden.
	 */
	public void windowOpened(AppWindow app) {}
	
	/**
	 * Called when the window becomes the active window.
	 */
	public void windowActivated(AppWindow app) {}
	
	/**
	 * Called when the window is no longer the active window.
	 */
	public void windowDeactivated(AppWindow app) {}
	
	/**
	 * Called when the window is changed from a normal to a minimized state. For many platforms, a
	 * minimized window is displayed as the icon specified in the window's iconImage property.
	 * 
	 * TODO iconImage?
	 */
	public void windowIconified(AppWindow app) {}
	
	/**
	 * Called when the window is changed from a minimized to a normal state.
	 */
	public void windowDeiconified(codeBattle.AppWindow app) {}
	
	/**
	 * Called when the user attempts to close the window from the window's close button or from the
	 * window's system menu.
	 * 
	 * @return true if the window can be effectively close; or false when the user's attempt should
	 *         be ignored.
	 */
	public boolean windowClosing(AppWindow app) {
		return true;
	}
	
	/**
	 * Called when the window was closed.
	 */
	public void windowClosed(AppWindow app) {}
	
}
