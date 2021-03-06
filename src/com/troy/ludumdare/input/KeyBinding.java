package com.troy.ludumdare.input;
/** A keybinding represents a key on the keyboard that is configurable **/
public class KeyBinding {
	
	/** The current key code of this keybinding **/
	public int keyId;
	
	/** The default key code of this keybinding **/
	public final int defaultKeyId;
	
	/** weather or not the key has just been presses **/
	protected boolean pressed;
	
	/** Was this key pressed last update **/
	protected boolean lastPress;

	public KeyBinding(int id) {
		this.keyId = id;
		this.defaultKeyId = id;
		this.pressed = false;
		this.lastPress = false;
		KeyHandler.keybinds.add(this);
	}

	/**
	 * @return true if the key on the keyboard is physically down otherwise, false
	 */
	public boolean isPressed(){
		return Keyboard.isKeyDown(keyId);
	}
	
	/**
	 * @return true if this is the first time that this method has been called since the keybinnding was pressed
	 */
	public boolean hasBeenPressed(){
		if(pressed){
			pressed = false;
			return true;
		}
		return false;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("KeyBinding:\n");
		sb.append("current key " + Keyboard.getKeyName(keyId) + " code is " + this.keyId + "\n");
		sb.append("default key " + Keyboard.getKeyName(keyId) + " code is " + this.keyId + "\n");
		
		return sb.toString();
	}
	
	/** Gets the human readable name associated with this key binding **/
	public String getKeyName(){
		return Keyboard.getKeyName(keyId);
	}
	
	/** Resets this keybinding to the default key **/
	public void resetKey(){
		this.keyId = defaultKeyId;
	}

}
