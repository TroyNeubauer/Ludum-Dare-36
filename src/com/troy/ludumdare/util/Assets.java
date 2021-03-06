package com.troy.ludumdare.util;

import com.troy.ludumdare.graphics.*;

/** Used to store and load all assets that the game requires **/
public class Assets {

	public static SpriteSheet spriteSheet;
	public static Sprite nullTile, bow, bow2, bow3, basicGun, copperSword, bronzeSword, ironSword, steelSword;
	public static WalkingSprite basicPlayer, barbarianKing, villageOpponent, midevalPlayer, cesar;
	public static AgeingSprite sand, wood, wood2;
	public static AngledSprite arrow;
	
	private static boolean hasBeenLoaded = false;
	
	
	/** Loads all assets **/
	public static void init() throws Exception {
		if(hasBeenLoaded) return;
		hasBeenLoaded = true;
		
		spriteSheet = new SpriteSheet("/textures/spritesheet.png", 16);
		nullTile = new Sprite(240, 240, 16, 16, spriteSheet);
		sand = new AgeingSprite(128, 0, 16, 16, 8, Assets.spriteSheet);
		wood = new AgeingSprite(128, 16, 16, 16, 8, Assets.spriteSheet);
		wood2 = new AgeingSprite(128, 32, 16, 16, 8, Assets.spriteSheet);
		bow = new Sprite(128, 128, 16, 16, spriteSheet);
		bow2 = new Sprite(bow, 0xFFFFFFFF, 0xFF0);
		bow3 = new Sprite(bow, 0xFFFFFFFF, 0xFFFF00);
		arrow = new AngledSprite(128, 96, 16, 16, 8, spriteSheet);
		basicGun = new Sprite(144, 128, 16, 16, spriteSheet);
		copperSword = new Sprite(160, 128, 16, 16, spriteSheet);
		bronzeSword = new Sprite(176, 128, 16, 16, spriteSheet);
		ironSword = new Sprite(176 + 16, 128, 16, 16, spriteSheet);
		steelSword = new Sprite(176 + 32, 128, 16, 16, spriteSheet);
		
		basicPlayer = new WalkingSprite(0, 0, 32, 32, spriteSheet, 280);
		barbarianKing = new WalkingSprite(0, 128, 32, 32, spriteSheet, 175);
		villageOpponent = new WalkingSprite(0, 256, 32, 32, spriteSheet, 300);
		cesar = new WalkingSprite(96, 256, 32, 32, spriteSheet, 300);
		
		midevalPlayer = new WalkingSprite(0, 384, 32, 32, spriteSheet, 220);
	}

	private Assets() {
	}

}
