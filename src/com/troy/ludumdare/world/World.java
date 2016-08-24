package com.troy.ludumdare.world;

import java.util.*;
import com.troy.ludumdare.*;
import com.troy.ludumdare.entity.*;
import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.tile.*;

/** Represents the world **/
public class World {

	/** Size of the world in tiles **/
	private int width, height;

	/** An array of all the tile id's **/
	public int[] tiles;

	public float xOffset, yOffset;

	/** A list of all the entities in the world **/
	public List<Entity> entities;
	private static Random random = new Random();

	public World(WorldStats worldStats) {
		this.width = worldStats.width;
		this.height = worldStats.height;
		this.tiles = new int[width * height];
		entities = new ArrayList<Entity>();
		for (int i = 0; i < width * height; i++) {
			if (random.nextInt(2) == 0) {
				tiles[i] = random.nextInt(6) + 1;
			} else {
				tiles[i] = 1;
			}
		}
		this.xOffset = 0f;
		this.yOffset = 0f;

	}

	/** Draws the entire world to the screen **/
	public void render(Screen screen) {
		for (int y = 0; y < width; y++) {
			for (int x = 0; x < height; x++) {
				TileRegistry.getTile(tiles[x + y * width]).render(screen, x * Tile.WIDTH, y * Tile.HEIGHT, this);
			}
		}
	}
	
	

	/** updates everything in the world **/
	public void update(int updateCount) {
		for (int y = 0; y < width; y++) {
			for (int x = 0; x < height; x++) {
				TileRegistry.getTile(tiles[x + y * width]).update(updateCount);
			}
		}
	}

	/** Returns the amount of tiles in the world **/
	public int getTotalTileCount() {
		return tiles.length;
	}
	
	public void centerOnPoint(int x, int y){
		xOffset = (float)x - (float)Game.screen.width / 2f;
		yOffset = (float)y - (float)Game.screen.height / 2f;
	}
}
