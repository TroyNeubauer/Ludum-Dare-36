package com.troy.dungeonlooter.graphics;

public class Sprite {

	public final int WIDTH, HEIGHT;
	private final int x, y;
	public int pixels[];
	private final SpriteSheet spriteSheet;

	public Sprite(int x, int y, int width, int height, SpriteSheet spriteSheet) {
		this.WIDTH = width;
		this.HEIGHT = height;

		this.x = x;
		this.y = y;

		this.spriteSheet = spriteSheet;
		this.pixels = new int[width * height];
		this.load();
	}

	public Sprite(SpriteSheet spriteSheet) {
		this(0, 0, spriteSheet.width, spriteSheet.height, spriteSheet);
	}

	private void load() {
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				pixels[x + y * WIDTH] = spriteSheet.pixels[(x + this.x) + (y + this.y) * spriteSheet.width];
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Sprite:\n");
		sb.append("contained in spritesheet " + spriteSheet.name + "\n");
		sb.append("image data:\n");
		for(int y = 0; y < HEIGHT; y++){
			for(int x = 0; x < WIDTH; x++){
				sb.append(new String(Integer.toHexString(pixels[x + y * WIDTH])).toUpperCase() + ", ");
			}
			sb.append('\n');
		}
		
		return sb.toString();
	}

}
