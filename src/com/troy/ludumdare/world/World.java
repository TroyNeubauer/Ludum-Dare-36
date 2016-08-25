package com.troy.ludumdare.world;

import java.awt.Font;
import java.util.*;
import com.troy.ludumdare.*;
import com.troy.ludumdare.entity.*;
import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.tile.*;
import com.troy.ludumdare.util.*;
import com.troy.troyberry.math.*;

/** Represents the world **/
public class World {

	Font entityFont = new Font("Times New Roman", Font.BOLD, 30);
	/** Size of the world in tiles **/
	private int width, height;
	private List<Integer> entitiesToRemove = new ArrayList<Integer>();

	/** An array of all the tile id's **/
	public int[] tiles;

	public float xOffset, yOffset;

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

	public World(WorldStats worldStats) {
		this.width = worldStats.width;
		this.height = worldStats.height;
		this.tiles = new int[width * height];
		entities = new ArrayList<Entity>();
		for (int i = 0; i < width * height; i++) {
			tiles[i] = 1;
			if (random.nextInt(3) == 0) {
				tiles[i] = random.nextInt(4) + 1;
				if (random.nextInt(4) + 1 == 1) {
					if (random.nextBoolean()) {
						tiles[i] = 5;
					} else {
						tiles[i] = 6;
					}
				}
			} else {
				tiles[i] = 1;
			}
		}
		tiles[0] = 1;
		this.xOffset = 0f;
		this.yOffset = 0f;

	}

	public Vector2i checkCollision(Vector2i tileLocation, Vector2i entityPosition, Vector2i entitySize, int velX, int velY, Entity entity) {
		if (velX != 0 && velY != 0 && entity instanceof EntityPlayer) {
			return new Vector2i(0, 0);
		}
		int ontOutOf2Width = (entitySize.x / 2) + 2 * velX;
		int ontOutOf2Height = (entitySize.y / 2) + 4 * velY;

		if (getTile((tileLocation.x + velX + ontOutOf2Width) / Tile.SIZE, (tileLocation.y + velY + ontOutOf2Height) / Tile.SIZE).isSolid())
			velX = 0;

		if (getTile((tileLocation.x + velX + ontOutOf2Width) / Tile.SIZE, (tileLocation.y + velY + ontOutOf2Height) / Tile.SIZE).isSolid())
			velY = 0;

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
				TileRegistry.getTile(tiles[x + y * width]).render(screen, x * Tile.SIZE, y * Tile.SIZE, this);
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
				TileRegistry.getTile(tiles[x + y * width]).update(updateCount);
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
			if (current.pos.equals(finish))

			{
				List<Node> path = new ArrayList<Node>();
				while (current.parent != null) {
					path.add(current);
					current = current.parent;
				}
				openList.clear();
				closedList.clear();

				return path;
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
		closedList.clear();
		return null;

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

	/** Returns the amount of tiles in the world **/
	public int getTileCount() {
		return tiles.length;
	}

	public void centerCameraOnPoint(int x, int y) {
		xOffset = (float) x - (float) Game.screen.width / 2f;
		yOffset = (float) y - (float) Game.screen.height / 2f;
	}

	public EntityPlayer getPlayer() {
		for (Entity e : entities)
			if (e instanceof EntityPlayer) {
				return (EntityPlayer) e;
			}
		return null;
	}

	public void spawnEntity(Class<Entity> value) {
		if (value.equals(Attacker.class)) {
			System.out.println("gonna spawn atacker");
		}

	}

	public void spawnAttacker() throws Exception {
		Vector2i tilePos = null;
		do {
			tilePos = new Vector2i(random.nextInt(40), random.nextInt(40));
		} while (this.getTile(tilePos.x, tilePos.y).isSolid());

		this.add(new Attacker(tilePos.x * Tile.SIZE, tilePos.y * Tile.SIZE, new WalkingSprite(Assets.attacker), 100, new Vector2i(0, 0)));

	}
}
