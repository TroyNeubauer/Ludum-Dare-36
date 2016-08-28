package com.troy.ludumdare.graphics;

import java.awt.*;
import com.troy.ludumdare.world.*;

/** Represents the 300 by 162 pixels "screen" that is drawn onto the window every frame **/
public class Screen {

	/** width and height of the pixels array **/
	public int width, height;

	/** The actual pixel data **/
	public int[] pixels;

	/** The color to clear the array with **/
	public int clearColor = 0x000000;

	/** The window's graphics context **/
	public Graphics g;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
	}

	/** Clears the pixels array with the selected color **/
	public void clear(Graphics g) {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = clearColor;
		}
		this.g = g;
	}
	
	public void drawSprite(Sprite sprite, int spriteX, int spriteY, World world, boolean inWorld) {
		int spriteWidthMinus1 = sprite.width - 1;
		for(int y = 0; y < sprite.width; y++){
			int yp = y - ((inWorld) ? world.yOffset : 0) + spriteY;
			if(yp < 0 || yp >= height)continue;
			for(int x = 0; x < sprite.width; x++){
				int xp = x - ((inWorld) ? world.xOffset : 0) + spriteX;
				if(xp < 0 || xp >= width)continue;
				int color = sprite.pixels[(x & spriteWidthMinus1) + (y & spriteWidthMinus1) * sprite.width];
				if(color == 0xffff00ff)continue;
				pixels[xp + (yp * width)] = color;
			}
		}
	}
	
	public void drawRectangle(int recX, int recY, int width, int height, int color) {
		
		for(int y = 0; y < height; y++){
			int yp = y + recY;
			if(yp < 0 || yp >= this.height)continue;
			for(int x = 0; x < width; x++){
				int xp = x + recX;
				if(xp < 0 || xp >= this.width)continue;
				pixels[xp + yp * this.width] = color;
			}
		}
	}

	/** Draws a sprite onto the screen 
	 * @param color2 
	 * @param i **/
	public void drawSprite(Sprite sprite, int spriteX, int spriteY, World world, int detecColor, int setColor) {
		int spriteWidthMinus1 = sprite.width - 1;
		for(int y = 0; y < sprite.width; y++){
			int yp = y - Math.round(world.yOffset) + spriteY;
			if(yp < 0 || yp >= height)continue;
			for(int x = 0; x < sprite.width; x++){
				int xp = x - Math.round(world.xOffset) + spriteX;
				if(xp < 0 || xp >= width)continue;
				int color = sprite.pixels[(x & spriteWidthMinus1) + (y & spriteWidthMinus1) * sprite.width];
				if(color == 0xffff00ff)continue;
				if(color == detecColor) color = setColor;
				pixels[xp + (yp * width)] = color;
			}
		}
	}

	public void drawPixel(int location, int color) {
		if(location < 0 || location >= (this.width * this.height))return;
		pixels[location] = color;
	}
	
	public void drawPixelInWorld(int x, int y, int color, World world) {
		x -= world.xOffset;
		y -= world.yOffset;
		int location = x + y * width;
		if(x < 0 || y < 0|| location >= (this.width * this.height))return;
		pixels[location] = color;
	}
	
	public void drawString(Font font, String string, int x, int y){
		g.setColor(Color.black);
		g.setFont(font);
		g.drawString(string, x, y);
	}
}
