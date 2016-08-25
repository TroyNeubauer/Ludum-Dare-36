package com.troy.ludumdare.tile;

import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.world.*;


public class AnimatedTile extends Tile {
	
	public final AnimatedSprite animatedSprite;
	public Sprite backGroundSprite;

	public AnimatedTile(int id, AnimatedSprite sprite, boolean isSolid, Sprite backGroundSprite) {
		super(id, sprite.getBasicSprite(), isSolid);
		this.animatedSprite = sprite;
		this.backGroundSprite = backGroundSprite;
	}
	
	
	public AnimatedTile(int id, AnimatedSprite sprite, boolean isSolid) {
		this(id, sprite, isSolid, null);
	}

	@Override
	public void render(Screen screen, int x, int y, World world) {
		if(backGroundSprite != null)screen.drawSprite(backGroundSprite, x, y, world);
		screen.drawSprite(animatedSprite.getCurrentSprite(this.sprite), x, y, world);
	}

	@Override
	public void update(int updateCount) {
		animatedSprite.update(updateCount);
	}

}
