package com.troy.ludumdare.graphics;

/** Represents a sprite on a sprite sheet **/
public class Sprite {

	/** Width and height of the sprite **/
	public final int width, height;

	/** The coordinates of the sprite on the sprite sheet **/
	private final int x, y;

	/** The spite's pixels **/
	public final int pixels[];

	/** The sprite sheet that the sprite belongs to **/
	private final SpriteSheet spriteSheet;

	public Sprite(int x, int y, int width, int height, SpriteSheet spriteSheet) throws Exception {
		this.width = width;
		this.height = height;

		this.x = x;
		this.y = y;

		this.spriteSheet = spriteSheet;
		this.pixels = new int[width * height];
		this.load();
	}

	public Sprite(SpriteSheet spriteSheet) throws Exception {
		this(0, 0, spriteSheet.width, spriteSheet.height, spriteSheet);
	}

	public Sprite(int x, int y, int width, int height, int[] pixels) {
		this.width = width;
		this.height = height;

		this.x = x;
		this.y = y;
		this.spriteSheet = null;
		this.pixels = pixels;
	}

	/** Loads the image data from the sprite sheet into the pixels array **/
	private void load() throws Exception {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = spriteSheet.pixels[(x + this.x) + (y + this.y) * spriteSheet.width];
			}
		}
	}

	public Sprite changeColor(int selectColor, int newColor) {
		for (int i = 0; i < pixels.length; i++) {
			if (pixels[i] == selectColor) {
				pixels[i] = newColor;
			}
		}
		return this;
	}
}
