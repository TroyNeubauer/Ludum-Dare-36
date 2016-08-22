package com.troy.dungeonlooter.entity;

import com.troy.dungeonlooter.graphics.*;
import com.troy.troyberry.math.*;

public abstract class Entity {

	public Vector2f position, velocity;
	public Sprite sprite;

	public Entity(Vector2f position, Sprite sprite) {
		this.position = position;
		this.velocity = new Vector2f();
		this.sprite = sprite;
		
		this.onSpawn();
	}
	
	public abstract void onSpawn();

	public void update() {
		this.position.add(velocity);
	}

	public void render(Screen screen) {
		screen.renderEntity(sprite, Maths.floor(position.x), Maths.floor(position.y));
	}

}
