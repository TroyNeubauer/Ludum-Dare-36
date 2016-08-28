package com.troy.ludumdare.graphics;

public class AngledSprite {

	/** The coordinates of the  the top left of the first sprite **/
	private final int x, y;

	/** Width and height of the an individual sprite **/
	public final int spriteWidth, spriteHeight;

	/** How many spites there are in the animated sprite **/
	public final int numberOfSprites;

	/** The sprite sheet that the sprite belongs to **/
	private final SpriteSheet spriteSheet;

	/** The spite's pixels **/
	private final Sprite sprites[];

	public AngledSprite(int x, int y, int spriteWidth, int spriteHeight, int numberOfSprites, SpriteSheet spriteSheet) throws Exception {
		this.x = x;
		this.y = y;

		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;

		this.numberOfSprites = numberOfSprites;

		this.sprites = new Sprite[numberOfSprites];
		this.spriteSheet = spriteSheet;
		this.load();
	}

	public Sprite getCurrentSprite(double angle) {
		angle += 180.0;
		double levels = 360 / (double)numberOfSprites;
		int index = (int) Math.round(angle / levels);
		index %= numberOfSprites;
		return sprites[index];
	}

	/** Loads the image data from the sprite sheet into the pixels array **/
	private void load() throws Exception {
		int[] pixels = new int[this.spriteWidth * this.spriteHeight];
		for(int i = 0; i < this.numberOfSprites; i++){
			sprites[i] = new Sprite((this.x + (i * spriteWidth)), this.y, this.spriteWidth, this.spriteHeight, this.spriteSheet);
		}
		
	}

	public Sprite getBasicSprite() {
		return sprites[0];
	}

}
