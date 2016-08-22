package com.troy.dungeonlooter.entity;

import com.troy.dungeonlooter.cilent.*;
import com.troy.dungeonlooter.main.*;
import com.troy.troyberry.math.*;

public class Camera {

	private Game game;
	public Vector2f position, velocity;
	public boolean snapCamera;

	public Camera(Game game, int x, int y, boolean snapCamera) {
		this.game = game;
		this.position = new Vector2f(x, y);
		this.velocity = new Vector2f();
		this.snapCamera = snapCamera;
	}

	public void update() {
		if (!snapCamera) {
			
			if (Controls.UP.isPressed()) {
				velocity.y -= 0.01f;
			}if (Controls.DOWN.isPressed()) {
				velocity.y += 0.01f;
			}if (Controls.LEFT.isPressed()) {
				velocity.x -= 0.01f;
			}if (Controls.RIGHT.isPressed()) {
				velocity.x += 0.01f;
			}
			this.position.add(velocity);
		} else {
			
		}

	}

}
