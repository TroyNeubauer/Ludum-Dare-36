package com.troy.ludumdare.entity;

import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.world.*;

/** The entity class represents a base of an entity with a position and texture. Many sub classes add on to this calss **/
public abstract class Entity {

	public int x, y;
	public Sprite sprite;

	public Entity(int x, int y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		
		this.onSpawn();
	}
	
	/** An abstract method that is called when an entity spawns **/
	public abstract void onSpawn();
	
	/** called 60 times per second to update this entity **/
	public abstract void update(World world);
	
	/** Called as often as possible to draw this entity to the screen **/
	public void render(Screen screen, World world) {
		screen.drawSprite(sprite, x, y, world);
	}

}
