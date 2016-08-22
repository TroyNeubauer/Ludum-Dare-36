package com.troy.dungeonlooter.cilent;

public class Controls {
	
	private static boolean classicControls = false;
	
	public static final KeyBinding UP = new KeyBinding(Keyboard.KEY_W), DOWN = new KeyBinding(Keyboard.KEY_S), LEFT = new KeyBinding(Keyboard.KEY_A),
		RIGHT = new KeyBinding(Keyboard.KEY_D), TOGGLE_FULLSCREEN = new KeyBinding(Keyboard.KEY_BACK_SLASH),
			PLAY = new KeyBinding(Keyboard.KEY_ENTER);
	
	public static void init(){
		
	}

	public static boolean isUsingClassicControls() {
		return Controls.classicControls;
	}

	public static void setClassicControls(boolean classicControls) {
		Controls.classicControls = classicControls;
	}

}
