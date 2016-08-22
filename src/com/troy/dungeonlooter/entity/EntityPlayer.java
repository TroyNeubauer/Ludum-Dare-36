package com.troy.dungeonlooter.entity;

import com.troy.dungeonlooter.graphics.*;
import com.troy.troyberry.math.*;


public class EntityPlayer extends EntityLiving {

	public EntityPlayer(Vector2f position, Sprite sprite, float health) {
		super(position, sprite, health);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update() {
		super.update();
	}
	
	
	public void render(Screen screen) {
		super.render(screen);

	}

	@Override
	public void onDeath() {
		
	}

	@Override
	public void onSpawn() {
		System.out.println("Player spawned at " + position);
	}

}
