package com.troy.dungeonlooter.cilent;

import java.util.*;

public class KeyHandler {

	protected static List<KeyBinding> keybinds = new ArrayList<KeyBinding>();

	private KeyHandler() {
	}

	public static void update() {
		Iterator<KeyBinding> i = keybinds.iterator();
		while (i.hasNext()) {
			KeyBinding key = (KeyBinding) i.next();
			if (key.isPressed() && key.lastPress == false) {
				key.pressed = true;
			}
			key.lastPress = key.isPressed();
		}
	}

	public static void resetAllKeys() {
		Iterator<KeyBinding> i = keybinds.iterator();
		while (i.hasNext()) {
			((KeyBinding) i.next()).resetKey();

		}
	}

	public static void unPressAllKeys() {
		Iterator<KeyBinding> i = keybinds.iterator();
		while (i.hasNext()) {
			KeyBinding key = (KeyBinding) i.next();
			key.pressed = false;
			key.lastPress = false;
		}
	}
}
