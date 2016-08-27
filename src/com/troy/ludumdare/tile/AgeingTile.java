package com.troy.ludumdare.tile;

import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.world.*;


public class AgeingTile extends Tile {
	
	AgeingSprite ageingSprite;
	
	public AgeingTile(int id, AgeingSprite sprite, boolean isSolid) {
		super(id, sprite.getBasicSprite(), isSolid);
		ageingSprite = sprite;
	}

	@Override
	public void render(Screen screen, int x, int y, World world) {
		screen.drawSprite(ageingSprite.getCurrentSprite(), x, y, world, true);
	}

	@Override
	public void update(int updateCount) {

	}

}
