package com.troy.dungeonlooter.graphics;

import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class SpriteSheet {

	private final String path;
	public final int width, height, spriteSize;
	public final String name;
	public int pixels[];

	public SpriteSheet(String path, int spriteSize) throws Exception {
		this.path = path;
		this.spriteSize = spriteSize;
		this.name = new File(path).getName();

		BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));

		width = image.getWidth();
		height = image.getHeight();

		this.pixels = new int[width * height];

		image.getRGB(0, 0, width, height, pixels, 0, width);

	}
}
