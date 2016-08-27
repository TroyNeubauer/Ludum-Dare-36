package com.troy.ludumdare.util;

import com.troy.ludumdare.graphics.*;

/** Used to store and load all assets that the game requires **/
public class Assets {

	public static SpriteSheet spriteSheet;
	public static Sprite nullTile;
	public static WalkingSprite player;
	public static AgeingSprite sand, wood, wood2;
	
	private static boolean hasBeenLoaded = false;
	
	
	/** Loads all assets **/
	public static void init() throws Exception {
		if(hasBeenLoaded) return;
		hasBeenLoaded = true;
		
		spriteSheet = new SpriteSheet("/textures/spritesheet.png", 16);
		nullTile = new Sprite(240, 240, 16, 16, spriteSheet);
		sand = new AgeingSprite(128, 0, 16, 16, 4, Assets.spriteSheet);
		wood = new AgeingSprite(128, 16, 16, 16, 4, Assets.spriteSheet);
		wood2 = new AgeingSprite(128, 32, 16, 16, 4, Assets.spriteSheet);
		
		player = new WalkingSprite(0, 0, 32, 32, spriteSheet, 220);
	}

	private Assets() {
	}

}
