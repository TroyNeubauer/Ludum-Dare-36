package com.troy.ludumdare.tile;

import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.util.*;
import com.troy.ludumdare.world.*;


public class AnimatedTile extends Tile {
	
	public final AnimatedSprite animatedSprite;

	public AnimatedTile(int id, AnimatedSprite sprite, boolean isSolid) {
		super(id, sprite.getBasicSprite(), isSolid);
		this.animatedSprite = sprite;
	}

	@Override
	public void render(Screen screen, int x, int y, World world) {
		screen.drawSprite(Assets.tile_1, x, y, world);
		screen.drawSprite(animatedSprite.getCurrentSprite(this.sprite), x, y, world);
	}

	@Override
	public void update(int updateCount) {
		animatedSprite.update(updateCount);
	}

}
