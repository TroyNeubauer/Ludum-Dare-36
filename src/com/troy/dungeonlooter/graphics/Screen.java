package com.troy.dungeonlooter.graphics;

import java.awt.*;
import com.troy.dungeonlooter.entity.*;

public class Screen {

	public int width, height;
	public int[] pixels;
	public int clearColor = 0;
	private Camera camera;
	private Graphics g;

	public Screen(int width, int height, Camera camera) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
		this.camera = camera;
	}

	public void clear(Graphics g) {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = clearColor;
		}
		this.g = g;
	}

	public void renderEntity(Sprite sprite, int x, int y) {
		
	}

	public void drawTile(Sprite sprite, int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
