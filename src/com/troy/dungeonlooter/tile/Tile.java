package com.troy.dungeonlooter.tile;

import com.troy.dungeonlooter.graphics.*;
import com.troy.dungeonlooter.util.*;

public abstract class Tile {

	public static final int WIDTH = 16, HEIGHT = 16;
	public final int id;
	public final Sprite sprite;
	private boolean isSolid;
	
	public static final Tile BASIC_TILE_1 = new BasicTile(1, Assets.tile_1, false), BASIC_TILE_2 = new BasicTile(2, Assets.tile_2, false),
		BASIC_TILE_3 = new BasicTile(3, Assets.tile_3, false), BASIC_TILE_4 = new BasicTile(4, Assets.tile_4, false), NULL_TILE = new BasicTile(-1, Assets.nullTile, false);

	public Tile(int id, Sprite sprite, boolean isSolid) {
		this.id = id;
		this.sprite = sprite;
		this.isSolid = isSolid;
		
		TileRegistry.addTile(this);
	}

	public abstract void render(Screen screen, float x, float y);
	
	public boolean isSolid() {
		return isSolid;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Tile:");
		sb.append(" id: " + id + "\n");
		sb.append(sprite);

		return sb.toString();
	}
}
