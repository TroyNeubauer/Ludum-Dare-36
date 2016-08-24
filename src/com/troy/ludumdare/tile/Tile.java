package com.troy.ludumdare.tile;

import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.util.*;
import com.troy.ludumdare.world.*;

public abstract class Tile {

	/** The size of a tile **/
	public static final int WIDTH = 16, HEIGHT = 16;

	/** The number used to identify a tile **/
	public final int id;

	/** The tile's texture **/
	public final Sprite sprite;

	/** Weather or not the tile can be collided with **/
	private boolean isSolid;

	public static final Tile BASIC_TILE_1 = new BasicTile(1, Assets.tile_1, false), BASIC_TILE_2 = new BasicTile(2, Assets.tile_2, false),
		BASIC_TILE_3 = new BasicTile(3, Assets.tile_3, false), BASIC_TILE_4 = new BasicTile(4, Assets.tile_4, false),
		NULL_TILE = new BasicTile(-1, Assets.nullTile, false);
	
	public static final AnimatedTile FIRE_TILE = new AnimatedTile(5, Assets.fire, false), 
		EXPLOSION_TILE = new AnimatedTile(6, Assets.explosion, false);

	public Tile(int id, Sprite sprite, boolean isSolid) {
		this.id = id;
		this.sprite = sprite;
		this.isSolid = isSolid;

		TileRegistry.addTile(this);
	}

	/** Used to draw a tile to the screen **/
	public abstract void render(Screen screen, int x, int y, World world);
	public abstract void update(int updateCount);

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
