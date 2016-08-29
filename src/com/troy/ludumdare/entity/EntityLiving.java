package com.troy.ludumdare.entity;

import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.world.*;
import com.troy.troyberry.math.*;

/** This class represents a living entity witch has health **/
public abstract class EntityLiving extends Entity {
	
	public WalkingSprite walkingSprite;
	public int hitCountDown;
	
	/** This entity's health **/
	private float health;
	public boolean dead;

	public EntityLiving(int x, int y, WalkingSprite sprite, float health) {
		super(x, y, sprite.getBasicSprite());
		this.walkingSprite = sprite;
		this.health = health;
		this.dead = false;
	}

	/** Called when this entity is killed **/
	public abstract void onDeath();

	public abstract void update(World world, int updateCount);

	public void render(Screen screen, World world) {
		if (this.isAlive()) {
			super.render(screen, world);
		}
	}

	/** @return true is this entity is alive, false other wise **/
	public boolean isAlive() {
		return Maths.isPosative(health);
	}

	/** @return false is this entity is alive, true other wise **/
	public boolean isDead() {
		return !isAlive();
	}

	/** Kills this entity  **/
	public void kill(World world) {
		if (dead) return;
		this.health = 0f;
		this.dead = true;
		world.removeEntity(this);
		this.onDeath();
	}

	/** Damages this entity the desired amount **/
	public void damage(float amount) {
		if (dead) return;
		this.health -= amount;
		if (isDead()) {
			this.dead = true;
			onDeath();
		}
	}

	/** Sets this entity's health to the desired float **/
	public void setHealth(float newHealth) {
		if (dead) return;
		this.health = newHealth;
		if (isDead()) {
			this.dead = true;
			onDeath();
		}
	}

	/** @return this entity's health **/
	public float getHealth() {
		return (health < 0f) ? 0f : health;
	}

}
