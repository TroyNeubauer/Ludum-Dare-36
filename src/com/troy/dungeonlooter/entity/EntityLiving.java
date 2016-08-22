package com.troy.dungeonlooter.entity;

import com.troy.dungeonlooter.graphics.*;
import com.troy.troyberry.math.*;


public abstract class EntityLiving extends Entity {
	
	private float health;
	private boolean dead;

	public EntityLiving(Vector2f position, Sprite sprite, float health) {
		super(position, sprite);
		this.health = health;
		this.dead = false;
	}
	
	public abstract void onDeath();
	
	public void update(){
		if(this.isAlive()){
			super.update();
		}
	}
	
	public void render(Screen screen){
		if(this.isAlive()){
			super.render(screen);
		}
	}
	
	public boolean isAlive(){
		return Maths.isPosative(health);
	}
	
	public boolean isDead(){
		return !isAlive();
	}
	
	public void kill(){
		if(dead) return;
		this.health = 0f;
		this.dead = true;
		this.onDeath();
	}
	
	public void damage(float amount){
		if(dead) return;
		this.health -= amount;
		if(isDead()){
			this.dead = true;
			onDeath();
		}
	}
	
	public void setHealth(float newHealth){
		if(dead) return;
		this.health = newHealth;
		if(isDead()){
			this.dead = true;
			onDeath();
		}
	}
	
	public float getHealth(){
		return (health < 0f) ? 0f : health;
	}

}
