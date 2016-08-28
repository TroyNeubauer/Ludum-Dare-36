package com.troy.ludumdare.sound;

import javax.sound.sampled.*;

public class Sound {

	public final String name;

	public Sound(String name) {

		this.name = name;

	}

	public synchronized void play() {
		new Thread(new Runnable() {
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(Class.class.getResourceAsStream("/sounds/" + name + ".wav"));
					clip.open(inputStream);
					clip.start();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}).start();
	}
}
