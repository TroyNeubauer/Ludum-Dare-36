package com.troy.ludumdare.graphics;

import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

/** Represents a sheet containing lots of spites **/
public class SpriteSheet {
	
	/** The path to the sprite sheet **/
	private final String path;
	
	/** with and height of the sprite sheet in pixels **/
	public final int width, height;
	
	/** How large a sprite usually is (usually) **/
	public final int spriteSize;
	
	/** The name of the sprite sheet (no path) **/
	public final String name;
	
	/** The image data for the sprite sheet **/
	public int pixels[];

	public SpriteSheet(String path, int spriteSize) throws Exception {
		this.path = path;
		this.spriteSize = spriteSize;
		this.name = new File(path).getName();

		BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));

		width = image.getWidth();
		height = image.getHeight();

		this.pixels = new int[width * height];
		
		/** Loads the image data from the image on disk to a pixel array in memory **/
		image.getRGB(0, 0, width, height, pixels, 0, width);

	}
}
