package com.troy.ludumdare.world;

import java.awt.*;
import java.util.*;
import java.util.List;
import com.troy.ludumdare.*;
import com.troy.ludumdare.entity.*;
import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.tile.*;
import com.troy.ludumdare.util.*;
import com.troy.troyberry.math.*;

/** Represents the world **/
public class World {

	int year = -64000;
	Font entityFont = new Font("Times New Roman", Font.BOLD, 30);
	/** Size of the world in tiles **/
	private int width, height;
	public int playerX = 16 * 30, playerY = 16 * 30;

	private List<Integer> entitiesToRemove = new ArrayList<Integer>();

	/** An array of all the tile id's **/
	public byte[] tiles;

	public int xOffset, yOffset;

	/** A list of all the entities in the world **/
	public List<Entity> entities;

	private Comparator<Node> peferectNodeSorter = new Comparator<Node>() {

		@Override
		public int compare(Node n0, Node n1) {
			if (n1.fCost < n0.fCost) return +1;
			if (n1.fCost > n0.fCost) return -1;
			return 0;
		}
	};

	private Comparator<Node> randomNodeSorter = new Comparator<Node>() {

		@Override
		public int compare(Node n0, Node n1) {
			if (n1.fCost < n0.fCost) return (random.nextInt(3) - 1);
			if (n1.fCost > n0.fCost) return (random.nextInt(3) - 1);
			return 0;
		}
	};

	private static Random random = new Random();

	public Vector2i checkCollision(int velX, int velY, Entity entity) {
		Vector2i tileLocation = entity.getPosition(), entityPosition = entity.getPosition(), entitySize = entity.getEntitySize();
		if (velX != 0 && velY != 0) {
			if (entity instanceof EntityPlayer) return new Vector2i(0, 0);
			return new Vector2i(velX, 0);
		}
		int ontOutOf2Width = (entitySize.x / 2) + 2 * velX;
		int ontOutOf2Height = (entitySize.y / 2) + 4 * velY;

		if (getTile((tileLocation.x + velX + ontOutOf2Width) / Tile.SIZE, (tileLocation.y + velY + ontOutOf2Height) / Tile.SIZE).isSolid()) velX = 0;

		if (getTile((tileLocation.x + velX + ontOutOf2Width) / Tile.SIZE, (tileLocation.y + velY + ontOutOf2Height) / Tile.SIZE).isSolid()) velY = 0;
		if (entity.x + velX + 5 < 0) velX = 0;
		if (entity.y + velY + 4 < 0) velY = 0;

		return new Vector2i(velX, velY);
	}

	public Tile getTile(int x, int y) {
		int index = (x + y * this.width);
		if (index < 0 || index >= (this.width * this.height)) return Tile.NULL_TILE;
		return TileRegistry.getTile(tiles[index]);
	}

	/** Draws the entire world to the screen **/
	public void render(Screen screen) {
		for (int y = 0; y < width; y++) {
			for (int x = 0; x < height; x++) {
				int index = x + y * width;
				if (index < 0 || index >= tiles.length) continue;
				TileRegistry.getTile(tiles[index]).render(screen, x * Tile.SIZE, y * Tile.SIZE, this);
			}
		}
		for (Entity e : entities) {
			e.render(screen, this);
		}
	}

	/** updates everything in the world **/
	public void update(int updateCount) {
		for (int y = 0; y < width; y++) {
			for (int x = 0; x < height; x++) {
				int index = x + y * width;
				if (index < 0 || index >= tiles.length) continue;
				TileRegistry.getTile(tiles[index]).update(updateCount);
			}
		}
		for (Entity e : entities) {
			e.update(this, updateCount);
		}
		for (int a : entitiesToRemove) {
			try {
				entities.remove(a);
			} catch (java.lang.IndexOutOfBoundsException e) {

			}
		}
		entitiesToRemove.clear();
	}

	public void removeEntity(Entity entity) {
		int index = entities.indexOf(entity);
		entitiesToRemove.add(index);
	}

	public List<Node> findPath(Vector2i start, Vector2i finish) {
		if (getTile(finish.x, finish.y).isSolid()) return null;
		List<Node> openList = new ArrayList<Node>();
		List<Node> closedList = new ArrayList<Node>();
		Node current = new Node(start, null, 0.0, getDistanceBetweenTiles(start, finish));
		openList.add(current);
		int tryCount = 0;

		while (openList.size() > 0 && tryCount < 200) {

			Collections.sort(openList, peferectNodeSorter);
			tryCount++;
			current = openList.get(0);
			if (current.pos.equals(finish)) {
				return retracePath(start, finish, current, openList, closedList);
			}
			openList.remove(current);
			closedList.add(current);
			for (int i = 0; i < 9; i++) {
				if (i == 0 || i == 2 || i == 4 || i == 6 || i == 8) continue;
				int x = current.pos.x, y = current.pos.y;
				int xi = (i % 3) - 1;
				int yi = (i / 3) - 1;
				Tile nextTile = getTile(x + xi, y + yi);
				if (nextTile == null || nextTile.isSolid()) continue;

				Vector2i newTilePos = new Vector2i((x + xi), (y + yi));
				double gCost = current.gCost + getDistanceBetweenTiles(newTilePos, current.pos);
				double hCost = getDistanceBetweenTiles(newTilePos, finish);
				Node node = new Node(newTilePos, current, gCost, hCost);
				if (vecInList(closedList, newTilePos) && gCost >= node.gCost) continue;
				if (!vecInList(openList, newTilePos) || gCost < current.gCost) openList.add(node);

			}

		}
		Node bestNode = current;
		for (Node node : openList) {
			if (node.hCost < bestNode.hCost) {
				bestNode = node;
			}
		}
		closedList.clear();
		return null;

	}

	private List<Node> retracePath(Vector2i start, Vector2i finish, Node current, List<Node> openList, List<Node> closedList) {
		List<Node> path = new ArrayList<Node>();
		path.add(new Node(start, null, 0.0, 0.0));
		while (current.parent != null) {
			path.add(current);
			current = current.parent;
		}
		openList.clear();
		closedList.clear();

		return path;
	}

	private boolean vecInList(List<Node> list, Vector2i vector) {
		for (Node node : list) {
			if (node.pos.equals(vector)) {
				return true;
			}
		}
		return false;
	}

	public double getDistanceBetweenTiles(Vector2i vec1, Vector2i vec2) {
		return Maths.getDistanceBetweenPoints(vec1.x, vec1.y, vec2.x, vec2.y);
	}

	public World add(Entity e) {
		entities.add(e);
		return this;
	}

	public World(WorldStats worldStats) {
		this.width = (short) worldStats.width;
		this.height = (short) worldStats.height;
		this.tiles = new byte[width * height];
		entities = new ArrayList<Entity>();
		for (int i = 0; i < width * height; i++) {
			tiles[i] = 1;
		}
		int radius = 15;
		int location = 20, loopCount = 200;
		double angle = 0;
		double slice = Math.PI * 2 / loopCount;
		int x, y;
		Vector2i center = new Vector2i(30, 30);

		for (int i = 0; i < loopCount; i++) {
			angle = i * slice;
			for (int times = 15; times < 18; times++) {
				x = Maths.round(center.x + Math.cos(angle) * times);
				y = Maths.round(center.y + Math.sin(angle) * times);
				tiles[x + y * this.width] = 2;
			}
		}
		for(int i = 0; i < 20; i++){
			tiles[(30 + 12 - random.nextInt(24)) + ( 30 + 12 - random.nextInt(24)) * this.width] = 3;
		}
		tiles[30 + 30 * this.width] = 1;
		tiles[31 + 30 * this.width] = 1;
		tiles[30 + 31 * this.width] = 1;
		tiles[31 + 31 * this.width] = 1;
		this.xOffset = 0;
		this.yOffset = 0;

	}

	public void centerCameraOnPoint(int x, int y) {
		xOffset = x - Game.screen.width / 2;
		yOffset = y - Game.screen.height / 2;
	}
}