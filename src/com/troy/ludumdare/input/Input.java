package com.troy.ludumdare.input;

import java.awt.event.*;
import com.troy.ludumdare.*;

public class Input implements MouseListener, MouseMotionListener, KeyListener {

	public static int mouseX, mouseY;
	
	public void keyPressed(KeyEvent e) {
		Keyboard.setKey(e.getKeyCode(), true);
	}
	
	public void keyReleased(KeyEvent e) {
		Keyboard.setKey(e.getKeyCode(), false);
	}
	
	public void mouseMoved(MouseEvent e) {
		mouseX = (int) ((e.getX()) / ((double)Game.frame.getWidth() / (double)Game.screen.width)+ (e.getX() * 0.004));
		mouseY = (int) ((e.getY()) / ((double)Game.frame.getHeight() / (double)Game.screen.height) + (e.getY() * 0.02));
	}

	public void mouseClicked(MouseEvent e) {
		Game.frame.dispose();
		try {
			Game.setupDisplay();
			Thread.sleep(500);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public static String getKeyName(int keyId) {
		return KeyEvent.getKeyText(keyId);
	}

	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
}
