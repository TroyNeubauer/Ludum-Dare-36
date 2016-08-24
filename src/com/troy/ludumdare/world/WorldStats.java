package com.troy.ludumdare.world;

/** Represents some information about the world **/
public class WorldStats {
	
	/** Size of the world in pixels **/
	public int width, height;
	
	/** The seed used to generate the world **/
	public long seed = 0L;
	
	/** Weather or not this world was generated or loaded from a file **/
	public boolean fromFile;

	public WorldStats(int width, int height, boolean fromFile) {
		this.width = width;
		this.height = height;
		this.fromFile = fromFile;
	}
}
