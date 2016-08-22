package com.troy.dungeonlooter.cilent;

public class KeyBinding {
	
	public int keyId;
	public final int defaultKeyId;
	protected boolean pressed;
	protected boolean lastPress;

	public KeyBinding(int id) {
		this.keyId = id;
		this.defaultKeyId = id;
		this.pressed = false;
		this.lastPress = false;
		KeyHandler.keybinds.add(this);
	}

	/**
	 * @return true if the key on the keyboard is physically down othersise, false
	 */
	public boolean isPressed(){
		return Keyboard.isKeyDown(keyId);
	}
	
	/**
	 * @return true if this is the first time that this method has been called since is was pressed
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
	
	public String getKeyName(){
		return Keyboard.getKeyName(keyId);
	}
	
	public void resetKey(){
		this.keyId = defaultKeyId;
	}

}
