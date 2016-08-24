package com.troy.ludumdare.entity;

import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.input.*;
import com.troy.ludumdare.util.*;
import com.troy.ludumdare.world.*;

/** Represents the player **/
public class EntityPlayer extends EntityLiving {

	public EntityPlayer(int x, int y, AnimatedSprite player, float health) {
		super(x, y, player.getBasicSprite(), health);
	}

	@Override
	public void update(World world) {
		if(isDead())return;
		
		if (Controls.UP.isPressed()) y -= 1;
		if (Controls.DOWN.isPressed()) y += 1;
		if (Controls.LEFT.isPressed()) x -= 1;
		if (Controls.RIGHT.isPressed()) x += 1;
	}

	public void render(Screen screen, World world) {
		screen.drawSprite(Assets.player.getCurrentSprite(), x, y, world);

	}

	@Override
	public void onDeath() {

	}

	@Override
	public void onSpawn() {
	}

}
