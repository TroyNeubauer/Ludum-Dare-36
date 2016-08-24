package com.troy.ludumdare.tile;

import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.world.*;

/** Represents a basic tile with no animations **/
public class BasicTile extends Tile {

	public BasicTile(int id, Sprite sprite, boolean isSolid) {
		super(id, sprite, isSolid);
	}

	@Override
	public void render(Screen screen, int x, int y, World world) {
		screen.drawSprite(this.sprite,x, y, world);
	}

	@Override
	public void update(int updateCount) {
		
	}
}
