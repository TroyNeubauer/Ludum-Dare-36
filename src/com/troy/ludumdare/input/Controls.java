package com.troy.ludumdare.input;

/** This class will contain all of the controls for the game**/
public class Controls {
	
	/** A keybinding that is used to control movements **/
	public static final KeyBinding UP = new KeyBinding(Keyboard.KEY_W), DOWN = new KeyBinding(Keyboard.KEY_S), LEFT = new KeyBinding(Keyboard.KEY_A),
		RIGHT = new KeyBinding(Keyboard.KEY_D), TOGGLE_FULLSCREEN = new KeyBinding(Keyboard.KEY_BACK_SLASH),
			PLAY = new KeyBinding(Keyboard.KEY_ENTER);
	
	public static void init(){
		
	}

	public static boolean isPressingMoreThenAmount(KeyBinding[] keyBindings, int amount) {
		if(amount < 0){
			throw new IllegalArgumentException("Amount cant be negative! " + amount);
		}
		int count = 0;
		for(KeyBinding i: keyBindings){
			if(i.isPressed()) count++;
		}
		return count > amount;
	}

}
