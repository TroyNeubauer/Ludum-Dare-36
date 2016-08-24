package com.troy.ludumdare.tile;

import java.util.*;
import com.troy.troyberry.logging.*;

/** Used for registering tiles with the game **/
public class TileRegistry {
	
	private static List<Tile> activeTiles = new ArrayList<Tile>();
	private static boolean firstRun = true;
	
	/** Adds a tile to the registry **/
	public static void addTile(Tile tile) {
		if (activeTiles.contains(tile)) {
			return;
		}

		Iterator<Tile> i = activeTiles.iterator();
		while (i.hasNext()) {
			Tile t = (Tile) i.next();
			if (t.id == tile.id) {
				return;
			}
		}
		activeTiles.add(tile);
	}
	
	
	/*** @return A tile with the provided id. Null tile otherwise **/ 
	public static Tile getTile(int id) {
		Iterator<Tile> i = activeTiles.iterator();
		while (i.hasNext()) {
			Tile t = (Tile) i.next();
			if (t.id == id) {
				return t;
			}
		}
		if (!firstRun) Log.warning("Could not find a tile with the id of " + id + " in the tile registry returning null tile!");
		firstRun = false;
		return Tile.NULL_TILE;
	}
	
	/** So that this class is static **/
	private TileRegistry() {
	}

}
