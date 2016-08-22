package com.troy.dungeonlooter.util;

import java.awt.event.*;
import com.troy.dungeonlooter.main.*;

public class WindowHandler implements WindowListener, WindowFocusListener {
	
	private static boolean hasFocous = false;

	@Override
	public void windowGainedFocus(WindowEvent e) {
		hasFocous = true;
		
	}

	@Override
	public void windowLostFocus(WindowEvent e) {
		hasFocous = false;
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		Game.running = false;
		
	}
	
	public static boolean hasFocous() {
		return hasFocous;
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
