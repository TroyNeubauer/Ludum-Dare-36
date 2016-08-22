package com.troy.dungeonlooter.util;

import com.troy.dungeonlooter.graphics.*;

public class Assets {

	public static SpriteSheet mainSheet;
	public static Sprite tile_1, tile_2, tile_3, tile_4, nullTile;
	private static boolean hasBeenLoaded = false;

	public static void init() throws Exception {
		if(hasBeenLoaded) return;
		hasBeenLoaded = true;
		
		mainSheet = new SpriteSheet("/textures/spritesheet.png", 16);
		nullTile = new Sprite(240, 240, 16, 16, mainSheet);

		tile_1 = new Sprite(16, 0, 16, 16, mainSheet);
		tile_2 = new Sprite(32, 0, 16, 16, mainSheet);
		tile_3 = new Sprite(48, 0, 16, 16, mainSheet);
		tile_4 = new Sprite(64, 0, 16, 16, mainSheet);
	}

	private Assets() {
	}

}
