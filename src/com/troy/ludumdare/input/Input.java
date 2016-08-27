package com.troy.ludumdare.input;

import java.awt.event.*;
import com.troy.ludumdare.*;

public class Input implements MouseListener, MouseMotionListener, KeyListener {

	private static boolean[] keys = new boolean[1024];
	public static int mouseX, mouseY;

	protected void setKey(int keyId, boolean pressed) {
		if (keyId <= 0 || keyId > keys.length - 1) {
			System.err.println("Key not supported!!! Key code: " + keyId);
			return;
		}
		keys[keyId] = pressed;
	}

	public static boolean isKeyDown(int keyId) {
		return keys[keyId];
	}
	
	public void keyPressed(KeyEvent e) {
		setKey(e.getKeyCode(), true);
	}
	
	public void keyReleased(KeyEvent e) {
		setKey(e.getKeyCode(), false);
	}
	
	public void mouseMoved(MouseEvent e) {
		mouseX = (int) ((e.getX()) / ((double)Game.game.frame.getWidth() / (double)Game.screen.width)+ (e.getX() * 0.004));
		mouseY = (int) ((e.getY()) / ((double)Game.game.frame.getHeight() / (double)Game.screen.height) + (e.getY() * 0.02));
		
	}

	public void mouseClicked(MouseEvent e) {
		System.exit(1);
	}

	public static String getKeyName(int keyId) {
		return KeyEvent.getKeyText(keyId);
	}

	public static final int KEY_ENTER = '\n';
	public static final int KEY_BACK_SPACE = '\b';
	public static final int KEY_TAB = '\t';
	public static final int KEY_CANCEL = 0x03;
	public static final int KEY_CLEAR = 0x0C;
	public static final int KEY_SHIFT = 0x10;
	public static final int KEY_CONTROL = 0x11;
	public static final int KEY_ALT = 0x12;
	public static final int KEY_PAUSE = 0x13;
	public static final int KEY_CAPS_LOCK = 0x14;
	public static final int KEY_ESCAPE = 0x1B;
	public static final int KEY_SPACE = 0x20;
	public static final int KEY_PAGE_UP = 0x21;
	public static final int KEY_PAGE_DOWN = 0x22;
	public static final int KEY_END = 0x23;
	public static final int KEY_HOME = 0x24;

	/**
	 * Constant for the non-numpad <b>left</b> arrow key.
	 * 
	 * @see #KEY_KP_LEFT
	 */
	public static final int KEY_LEFT = 0x25;

	/**
	 * Constant for the non-numpad <b>up</b> arrow key.
	 * 
	 * @see #KEY_KP_UP
	 */
	public static final int KEY_UP = 0x26;

	/**
	 * Constant for the non-numpad <b>right</b> arrow key.
	 * 
	 * @see #KEY_KP_RIGHT
	 */
	public static final int KEY_RIGHT = 0x27;

	/**
	 * Constant for the non-numpad <b>down</b> arrow key.
	 * 
	 * @see #KEY_KP_DOWN
	 */
	public static final int KEY_DOWN = 0x28;

	/**
	 * Constant for the comma key, ","
	 */
	public static final int KEY_COMMA = 0x2C;

	/**
	 * Constant for the minus key, "-"
	 * 
	 */
	public static final int KEY_MINUS = 0x2D;

	/**
	 * Constant for the period key, "."
	 */
	public static final int KEY_PERIOD = 0x2E;

	/**
	 * Constant for the forward slash key, "/"
	 */
	public static final int KEY_SLASH = 0x2F;

	/** KEY_0 thru KEY_9 are the same as ASCII '0' thru '9' (0x30 - 0x39) */
	public static final int KEY_0 = 0x30;
	public static final int KEY_1 = 0x31;
	public static final int KEY_2 = 0x32;
	public static final int KEY_3 = 0x33;
	public static final int KEY_4 = 0x34;
	public static final int KEY_5 = 0x35;
	public static final int KEY_6 = 0x36;
	public static final int KEY_7 = 0x37;
	public static final int KEY_8 = 0x38;
	public static final int KEY_9 = 0x39;

	/**
	 * Constant for the semicolon key, ";"
	 */
	public static final int KEY_SEMICOLON = 0x3B;

	/**
	 * Constant for the equals key, "="
	 */
	public static final int KEY_EQUALS = 0x3D;

	/** KEY_A thru KEY_Z are the same as ASCII 'A' thru 'Z' (0x41 - 0x5A) */
	public static final int KEY_A = 0x41;
	public static final int KEY_B = 0x42;
	public static final int KEY_C = 0x43;
	public static final int KEY_D = 0x44;
	public static final int KEY_E = 0x45;
	public static final int KEY_F = 0x46;
	public static final int KEY_G = 0x47;
	public static final int KEY_H = 0x48;
	public static final int KEY_I = 0x49;
	public static final int KEY_J = 0x4A;
	public static final int KEY_K = 0x4B;
	public static final int KEY_L = 0x4C;
	public static final int KEY_M = 0x4D;
	public static final int KEY_N = 0x4E;
	public static final int KEY_O = 0x4F;
	public static final int KEY_P = 0x50;
	public static final int KEY_Q = 0x51;
	public static final int KEY_R = 0x52;
	public static final int KEY_S = 0x53;
	public static final int KEY_T = 0x54;
	public static final int KEY_U = 0x55;
	public static final int KEY_V = 0x56;
	public static final int KEY_W = 0x57;
	public static final int KEY_X = 0x58;
	public static final int KEY_Y = 0x59;
	public static final int KEY_Z = 0x5A;

	/**
	 * Constant for the open bracket key, "["
	 */
	public static final int KEY_OPEN_BRACKET = 0x5B;

	/**
	 * Constant for the back slash key, "\"
	 */
	public static final int KEY_BACK_SLASH = 0x5C;

	/**
	 * Constant for the close bracket key, "]"
	 */
	public static final int KEY_CLOSE_BRACKET = 0x5D;

	public static final int KEY_NUMPAD0 = 0x60;
	public static final int KEY_NUMPAD1 = 0x61;
	public static final int KEY_NUMPAD2 = 0x62;
	public static final int KEY_NUMPAD3 = 0x63;
	public static final int KEY_NUMPAD4 = 0x64;
	public static final int KEY_NUMPAD5 = 0x65;
	public static final int KEY_NUMPAD6 = 0x66;
	public static final int KEY_NUMPAD7 = 0x67;
	public static final int KEY_NUMPAD8 = 0x68;
	public static final int KEY_NUMPAD9 = 0x69;
	public static final int KEY_MULTIPLY = 0x6A;
	public static final int KEY_ADD = 0x6B;

	/**
	 * This constant is obsolete, and is included only for backwards
	 * compatibility.
	 * 
	 * @see #KEY_SEPARATOR
	 */
	public static final int KEY_SEPARATER = 0x6C;

	/**
	 * Constant for the Numpad Separator key.
	 * 
	 */
	public static final int KEY_SEPARATOR = KEY_SEPARATER;

	public static final int KEY_SUBTRACT = 0x6D;
	public static final int KEY_DECIMAL = 0x6E;
	public static final int KEY_DIVIDE = 0x6F;
	public static final int KEY_DELETE = 0x7F; /* ASCII DEL */
	public static final int KEY_NUM_LOCK = 0x90;
	public static final int KEY_SCROLL_LOCK = 0x91;

	/** Constant for the F1 function key. */
	public static final int KEY_F1 = 0x70;

	/** Constant for the F2 function key. */
	public static final int KEY_F2 = 0x71;

	/** Constant for the F3 function key. */
	public static final int KEY_F3 = 0x72;

	/** Constant for the F4 function key. */
	public static final int KEY_F4 = 0x73;

	/** Constant for the F5 function key. */
	public static final int KEY_F5 = 0x74;

	/** Constant for the F6 function key. */
	public static final int KEY_F6 = 0x75;

	/** Constant for the F7 function key. */
	public static final int KEY_F7 = 0x76;

	/** Constant for the F8 function key. */
	public static final int KEY_F8 = 0x77;

	/** Constant for the F9 function key. */
	public static final int KEY_F9 = 0x78;

	/** Constant for the F10 function key. */
	public static final int KEY_F10 = 0x79;

	/** Constant for the F11 function key. */
	public static final int KEY_F11 = 0x7A;

	/** Constant for the F12 function key. */
	public static final int KEY_F12 = 0x7B;

	/**
	 * Constant for the F13 function key.
	 * 
	 */
	/* F13 - F24 are used on IBM 3270 keyboard; use random range for constants. */
	public static final int KEY_F13 = 0xF000;

	/**
	 * Constant for the F14 function key.
	 * 
	 */
	public static final int KEY_F14 = 0xF001;

	/**
	 * Constant for the F15 function key.
	 * 
	 */
	public static final int KEY_F15 = 0xF002;

	/**
	 * Constant for the F16 function key.
	 * 
	 */
	public static final int KEY_F16 = 0xF003;

	/**
	 * Constant for the F17 function key.
	 * 
	 */
	public static final int KEY_F17 = 0xF004;

	/**
	 * Constant for the F18 function key.
	 * 
	 */
	public static final int KEY_F18 = 0xF005;

	/**
	 * Constant for the F19 function key.
	 * 
	 */
	public static final int KEY_F19 = 0xF006;

	/**
	 * Constant for the F20 function key.
	 * 
	 */
	public static final int KEY_F20 = 0xF007;

	/**
	 * Constant for the F21 function key.
	 * 
	 */
	public static final int KEY_F21 = 0xF008;

	/**
	 * Constant for the F22 function key.
	 * 
	 */
	public static final int KEY_F22 = 0xF009;

	/**
	 * Constant for the F23 function key.
	 * 
	 */
	public static final int KEY_F23 = 0xF00A;

	/**
	 * Constant for the F24 function key.
	 * 
	 */
	public static final int KEY_F24 = 0xF00B;

	public static final int KEY_PRINTSCREEN = 0x9A;
	public static final int KEY_INSERT = 0x9B;
	public static final int KEY_HELP = 0x9C;
	public static final int KEY_META = 0x9D;

	public static final int KEY_BACK_QUOTE = 0xC0;
	public static final int KEY_QUOTE = 0xDE;

	/**
	 * Constant for the numeric keypad <b>up</b> arrow key.
	 * 
	 * @see #KEY_UP
	 * 
	 */
	public static final int KEY_KP_UP = 0xE0;

	/**
	 * Constant for the numeric keypad <b>down</b> arrow key.
	 * 
	 * @see #KEY_DOWN
	 * 
	 */
	public static final int KEY_KP_DOWN = 0xE1;

	/**
	 * Constant for the numeric keypad <b>left</b> arrow key.
	 * 
	 * @see #KEY_LEFT
	 * 
	 */
	public static final int KEY_KP_LEFT = 0xE2;

	/**
	 * Constant for the numeric keypad <b>right</b> arrow key.
	 * 
	 * @see #KEY_RIGHT
	 * 
	 */
	public static final int KEY_KP_RIGHT = 0xE3;

	/* For European keyboards */

	public static final int KEY_DEAD_GRAVE = 0x80;

	public static final int KEY_DEAD_ACUTE = 0x81;

	public static final int KEY_DEAD_CIRCUMFLEX = 0x82;

	public static final int KEY_DEAD_TILDE = 0x83;

	public static final int KEY_DEAD_MACRON = 0x84;

	public static final int KEY_DEAD_BREVE = 0x85;

	public static final int KEY_DEAD_ABOVEDOT = 0x86;

	public static final int KEY_DEAD_DIAERESIS = 0x87;

	public static final int KEY_DEAD_ABOVERING = 0x88;

	public static final int KEY_DEAD_DOUBLEACUTE = 0x89;

	public static final int KEY_DEAD_CARON = 0x8a;

	public static final int KEY_DEAD_CEDILLA = 0x8b;

	public static final int KEY_DEAD_OGONEK = 0x8c;

	public static final int KEY_DEAD_IOTA = 0x8d;

	public static final int KEY_DEAD_VOICED_SOUND = 0x8e;

	public static final int KEY_DEAD_SEMIVOICED_SOUND = 0x8f;

	public static final int KEY_AMPERSAND = 0x96;

	public static final int KEY_ASTERISK = 0x97;

	public static final int KEY_QUOTEDBL = 0x98;

	public static final int KEY_LESS = 0x99;

	public static final int KEY_GREATER = 0xa0;

	public static final int KEY_BRACELEFT = 0xa1;

	public static final int KEY_BRACERIGHT = 0xa2;

	/**
	 * Constant for the "@" key.
	 * 
	 */
	public static final int KEY_AT = 0x0200;

	/**
	 * Constant for the ":" key.
	 * 
	 */
	public static final int KEY_COLON = 0x0201;

	/**
	 * Constant for the "^" key.
	 * 
	 */
	public static final int KEY_CIRCUMFLEX = 0x0202;

	/**
	 * Constant for the "$" key.
	 * 
	 */
	public static final int KEY_DOLLAR = 0x0203;

	/**
	 * Constant for the Euro currency sign key.
	 * 
	 */
	public static final int KEY_EURO_SIGN = 0x0204;

	/**
	 * Constant for the "!" key.
	 * 
	 */
	public static final int KEY_EXCLAMATION_MARK = 0x0205;

	/**
	 * Constant for the inverted exclamation mark key.
	 * 
	 */
	public static final int KEY_INVERTED_EXCLAMATION_MARK = 0x0206;

	/**
	 * Constant for the "(" key.
	 * 
	 */
	public static final int KEY_LEFT_PARENTHESIS = 0x0207;

	/**
	 * Constant for the "#" key.
	 * 
	 */
	public static final int KEY_NUMBER_SIGN = 0x0208;

	/**
	 * Constant for the "+" key.
	 * 
	 */
	public static final int KEY_PLUS = 0x0209;

	/**
	 * Constant for the ")" key.
	 * 
	 */
	public static final int KEY_RIGHT_PARENTHESIS = 0x020A;

	/**
	 * Constant for the "_" key.
	 * 
	 */
	public static final int KEY_UNDERSCORE = 0x020B;

	/**
	 * Constant for the Microsoft Windows "Windows" key. It is used for both the
	 * left and right version of the key.
	 * 
	 * @see #getKeyLocation()
	 * 
	 */
	public static final int KEY_WINDOWS = 0x020C;

	/**
	 * Constant for the Microsoft Windows Context Menu key.
	 * 
	 */
	public static final int KEY_CONTEXT_MENU = 0x020D;

	/**
	 * Constant for the Compose function key.
	 * 
	 */
	public static final int KEY_COMPOSE = 0xFF20;

	/**
	 * Constant for the AltGraph function key.
	 * 
	 */
	public static final int KEY_ALT_GRAPH = 0xFF7E;

	/**
	 * Constant for the Begin key.
	 * 
	 */
	public static final int KEY_BEGIN = 0xFF58;

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
