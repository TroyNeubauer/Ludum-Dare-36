package com.troy.ludumdare.entity;

import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.input.*;
import com.troy.ludumdare.world.*;
import com.troy.troyberry.math.*;

/** Represents the player **/
public class EntityPlayer extends EntityLiving {

	public EntityPlayer(int x, int y, WalkingSprite walkingSprite, float health) {
		super(x, y, walkingSprite, health);
	}

	@Override
	public void update(World world, int updateCount) {
		if (isDead()) return;
		int xx = 0;
		int yy = 0;
		if (Controls.UP.isPressed()) yy -= 1;
		if (Controls.DOWN.isPressed()) yy += 1;
		if (Controls.LEFT.isPressed()) xx -= 1;
		if (Controls.RIGHT.isPressed()) xx += 1;
		this.walkingSprite.update(updateCount, new Vector2i(xx, yy));

		velocity = world.checkCollision(xx, yy, this);
		xx = velocity.x;
		yy = velocity.y;
		x += xx;
		y += yy;
	}

	public void render(Screen screen, World world) {
		screen.drawSprite(this.walkingSprite.getCurrentSprite(), x, y, world);

	}

	@Override
	public void onDeath() {

	}

	@Override
	public void onSpawn() {
	}

}
