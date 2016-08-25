package com.troy.ludumdare.util;

import com.troy.ludumdare.graphics.*;

/** Used to store and load all assets that the game requires **/
public class Assets {

	public static SpriteSheet spriteSheet;
	public static Sprite tile_1, tile_2, tile_3, tile_4, nullTile, windowIcon;
	public static AnimatedSprite fire, explosion;
	public static WalkingSprite player, attacker;
	
	private static boolean hasBeenLoaded = false;
	
	
	/** Loads all assets **/
	public static void init() throws Exception {
		if(hasBeenLoaded) return;
		hasBeenLoaded = true;
		
		spriteSheet = new SpriteSheet("/textures/spritesheet.png", 16);
		nullTile = new Sprite(240, 240, 16, 16, spriteSheet);
		
		tile_1 = new Sprite(16, 0, 16, 16, spriteSheet);
		tile_2 = new Sprite(32, 0, 16, 16, spriteSheet);
		tile_3 = new Sprite(48, 0, 16, 16, spriteSheet);
		tile_4 = new Sprite(64, 0, 16, 16, spriteSheet);
		windowIcon = new Sprite(0, 128, 64, 64, spriteSheet);
		
		fire = new AnimatedSprite(0, 32, 16, 16, 8, spriteSheet, 200);
		explosion = new AnimatedSprite(0, 48, 16, 16, 32, spriteSheet, 50);
		
		player = new WalkingSprite(0, 64, 16, 16, spriteSheet, 300);
		attacker = new WalkingSprite(0, 64, 16, 16, spriteSheet, 250);
	}

	private Assets() {
	}

}
