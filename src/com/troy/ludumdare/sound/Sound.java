package com.troy.ludumdare.sound;

import java.io.*;
import javax.sound.sampled.*;

public class Sound {
	private final String name;
	private Clip clip;
	public Sound(String name) {
		this.name = "/sounds/" + name + ".wav";
	}

	public void play() {
		try {
			if(clip != null){
				clip.close();
			}
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Class.class.getResourceAsStream(name)));
			clip.start();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			System.err.println("couldnt play sound! " + name);
		}
	}
}
