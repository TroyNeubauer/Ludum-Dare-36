package com.troy.ludumdare.tile;

import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.util.*;
import com.troy.ludumdare.world.*;

public abstract class Tile {

	/** The size of a tile **/
	public static final int SIZE = 16;

	/** The number used to identify a tile **/
	public final int id;

	/** The tile's texture **/
	public final Sprite sprite;

	/** Weather or not the tile can be collided with **/
	protected final boolean isSolid;
	
	public static final AgeingTile SAND = new AgeingTile(1, Assets.sand, false), WOOD = new AgeingTile(2, Assets.wood, true),
		WOOD2 = new AgeingTile(3, Assets.wood2, true);

	public static final BasicTile NULL_TILE = new BasicTile(-1, Assets.nullTile, true);

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
