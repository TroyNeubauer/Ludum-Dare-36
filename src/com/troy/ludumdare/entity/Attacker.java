package com.troy.ludumdare.entity;

import java.util.*;
import com.troy.ludumdare.gamestate.*;
import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.tile.*;
import com.troy.ludumdare.world.*;
import com.troy.troyberry.math.*;

public class Attacker extends NPC {

	private Random random;
	private int color;
	private final int updateTime;

	public Attacker(int x, int y, WalkingSprite attacker, float health, Vector2i velocity) {
		super(x, y, attacker, health, velocity);
		random = new Random();
		this.color = 0xFF + random.nextInt(255) + random.nextInt(255) + random.nextInt(255);
		this.updateTime = LevelState.world.entities.size() % 15;
	}

	@Override
	public void onDeath() {

	}

	@Override
	public void render(Screen screen, World world) {
		screen.drawSprite(this.walkingSprite.getCurrentSprite(), x, y, world, 0xFF32FF39, 0xFFF2F263);
	}

	@Override
	public void update(World world, int updateCount) {

		this.walkingSprite.update(updateCount, this.velocity);
		if (((updateCount + updateTime) % 15) == 0) {
			updateTarget(world);
		}
		getCloseToPlayer(world);
		if (updateCount % 2 == 0) {
			if (this.pathFindInstructions != null) {
				if (this.pathFindInstructions.size() > 0) {
					if (hasReachedNextLocation()) getNextLocation();

					if (nextLocation != null) {
						int xx = 0;
						int yy = 0;

						if (y < nextLocation.y) yy += 1;

						if (y > nextLocation.y) yy -= 1;

						if (x < nextLocation.x) xx += 1;

						if (x > nextLocation.x) xx -= 1;

						this.walkingSprite.update(updateCount, new Vector2i(xx, yy));
						this.velocity = world.checkCollision(xx, yy, this);
						this.x += this.velocity.x;
						this.y += this.velocity.y;
					}
				}

			}
		}

	}

	private void getCloseToPlayer(World world) {
		EntityPlayer player = world.getPlayer();
		if (player == null || nextLocation == null || desiredLocation == null) return;
		if (new Vector2i((nextLocation.x + Tile.SIZE / 2) / Tile.SIZE, (nextLocation.y + Tile.SIZE / 2) / Tile.SIZE)
			.equals(new Vector2i((desiredLocation.x + Tile.SIZE / 2) / Tile.SIZE, (desiredLocation.y + Tile.SIZE / 2) / Tile.SIZE))) {
			desiredLocation = new Vector2i(player.x, player.y);
		}
		if (Maths.approximateDistanceBetweenPoints(player.x, player.y, x, y) < Tile.SIZE) updateTarget(world);
	}

	private void updateTarget(World world) {
		EntityPlayer player = world.getPlayer();
		if (player == null) return;
		Vector2i goal = new Vector2i(player.x / Tile.SIZE, player.y / Tile.SIZE);
		Vector2i start = new Vector2i(x / Tile.SIZE, y / Tile.SIZE);
		this.desiredLocation = new Vector2i(player.x, player.y);
		this.setPathFinding(world.findPath(start, goal));
		if (pathFindInstructions != null && pathFindInstructions.size() > 1) {
			int index = (this.pathFindInstructions.size() - 1);
			index = (index < 0) ? 0 : index;
			Vector2i next = pathFindInstructions.get(index).pos;
			;
			this.nextLocation = new Vector2i(next.x * Tile.SIZE, next.y * Tile.SIZE);
		}
		if (pathFindInstructions != null && pathFindInstructions.size() == 1) {
			nextLocation = new Vector2i(player.x, player.y);
			desiredLocation = new Vector2i(player.x, player.y);
		}
	}

	@Override
	public void onSpawn() {

	}
}
