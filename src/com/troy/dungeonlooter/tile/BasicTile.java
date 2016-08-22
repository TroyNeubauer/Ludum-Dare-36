package com.troy.dungeonlooter.tile;

import com.troy.dungeonlooter.graphics.*;
import com.troy.troyberry.math.*;


public class BasicTile extends Tile {

	public BasicTile(int id, Sprite sprite, boolean isSolid) {
		super(id, sprite, isSolid);
	}

	@Override
	public void render(Screen screen, float x, float y) {
		screen.drawTile(this.sprite, Maths.floor(x), Maths.floor(y));
	}
}
