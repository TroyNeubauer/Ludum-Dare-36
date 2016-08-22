package com.troy.dungeonlooter.tile;

import java.util.*;
import com.troy.troyberry.logging.*;

public class TileRegistry {

	private static List<Tile> activeTiles = new ArrayList<Tile>();
	private static boolean firstRun = true;

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

	private TileRegistry() {
	}

}
