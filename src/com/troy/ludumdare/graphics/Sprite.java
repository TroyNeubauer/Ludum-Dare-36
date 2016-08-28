package com.troy.ludumdare.graphics;

/** Represents a sprite on a sprite sheet **/
public class Sprite {

	/** Width and height of the sprite **/
	public final int width, height;

	/** The coordinates of the sprite on the sprite sheet **/
	private final int x, y;

	/** The spite's pixels **/
	public int pixels[];

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

	public Sprite(Sprite other) {
		this.width = other.width;
		this.height = other.height;

		this.x = other.x;
		this.y = other.y;
		this.spriteSheet = other.spriteSheet;
		this.pixels = new int[width * height];
		for (int i = 0; i < pixels.length; i++) {
			this.pixels[i] = other.pixels[i];
		}
	}

	public Sprite(Sprite other, int scanColor, int color) {
		this.width = other.width;
		this.height = other.height;

		this.x = other.x;
		this.y = other.y;
		this.spriteSheet = other.spriteSheet;
		this.pixels = new int[width * height];
		for (int i = 0; i < pixels.length; i++) {
			int pixelColor = other.pixels[i];
			if(pixelColor == scanColor){
				this.pixels[i] = color;
			}else{
				this.pixels[i] = pixelColor;
			}
		}
	}

	public Sprite getHitSprite() {
		int[] pixels = new int[this.width * this.height];
		for (int i = 0; i < this.width * this.height; i++) {
			int color = this.pixels[i];
			if (color == 0xFFFF00FF){
				pixels[i] = 0xFFFF00FF;
				continue;
			}
			pixels[i] = 0xFF7777;
		}

		return new Sprite(this.x, this.y, this.width, this.height, pixels);

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
