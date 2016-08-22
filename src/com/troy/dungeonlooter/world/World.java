package com.troy.dungeonlooter.world;

import java.util.*;
import com.troy.dungeonlooter.entity.*;
import com.troy.dungeonlooter.graphics.*;
import com.troy.dungeonlooter.tile.*;

public class World {

	private int width, height;
	public int[] tiles;
	public List<Entity> entities;
	private static Random random = new Random();

	public World(WorldStats worldStats) {
		this.width = worldStats.width;
		this.height = worldStats.height;
		this.tiles = new int[width * height];
		entities = new ArrayList<Entity>();
		for (int i = 0; i < width * height; i++) {
			tiles[i] = random.nextInt(4) + 1;
		}

	}

	public void render(Screen screen) {
		for (int y = 0; y < width; y++) {
			for (int x = 0; x < height; x++) {
				TileRegistry.getTile(tiles[x + y * width]).render(screen, x * Tile.WIDTH, y * Tile.HEIGHT);
			}
		}
	}

	public void update() {

	}

	public int getTotalTileCount() {
		return tiles.length;
	}
}
