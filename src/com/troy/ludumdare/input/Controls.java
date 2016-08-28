package com.troy.ludumdare.input;

/** This class will contain all of the controls for the game**/
public class Controls {
	
	/** A keybinding that is used to control movements **/
	public static final KeyBinding INVENTORY = new KeyBinding(Keyboard.KEY_E), SHOOT = new KeyBinding(Keyboard.KEY_SPACE);
	
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
