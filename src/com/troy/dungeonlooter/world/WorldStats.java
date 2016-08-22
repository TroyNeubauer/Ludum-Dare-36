package com.troy.dungeonlooter.world;

public class WorldStats {

	public int width, height;
	public long seed = 0L;
	public boolean fromFile;

	public WorldStats(int width, int height, boolean fromFile) {
		this.width = width;
		this.height = height;
		this.fromFile = fromFile;
	}
}
