package com.troy.ludumdare.entity;

import java.util.*;
import com.troy.ludumdare.gamestate.*;
import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.tile.*;
import com.troy.ludumdare.world.*;
import com.troy.troyberry.math.*;

public class Attacker extends NPC {
	
	private Random random;
	private int color, age, smartFactor;
	private final int lifeLength, updateTime;

	public Attacker(int x, int y, WalkingSprite attacker, float health, Vector2i velocity) {
		super(x, y, attacker, health, velocity);
		random = new Random();
		this.color = 0xFF + random.nextInt(255) + random.nextInt(255) + random.nextInt(255);
		this.age = 0;
		this.lifeLength = random.nextInt(1000);
		this.smartFactor = (random.nextInt(5) == 0) ? (random.nextInt(100) + 50) : 0;
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
		age++;
		if (age > lifeLength) this.kill(world);

		this.walkingSprite.update(updateCount, this.velocity);
		if (((updateCount + updateTime) % (15 + smartFactor)) == 0) {
			EntityPlayer player = world.getPlayer();
			if(player == null) return;
			Vector2i goal = new Vector2i((player.x + Tile.SIZE / 2 + player.velocity.x * Tile.SIZE) / Tile.SIZE, (player.y + Tile.SIZE / 4 + player.velocity.y * Tile.SIZE) / Tile.SIZE);
			Vector2i start = new Vector2i((x + Tile.SIZE / 2) / Tile.SIZE, (y + Tile.SIZE / 2) / Tile.SIZE);
			this.setPathFinding(world.findPath(start, goal));
			if (pathFindInstructions != null && pathFindInstructions.size() > 0) {
				int index = (this.pathFindInstructions.size() - 1);
				index = (index < 0) ? 0 : index;
				this.nextLocation = pathFindInstructions.get(index).pos;
			}
		}
		if (this.pathFindInstructions != null) {
			if (this.pathFindInstructions.size() > 0) {
				if (hasReachedNextLocation()) getNextLocation();

				if (nextLocation != null) {

					boolean movedX = false;
					int xx = 0;
					int yy = 0;

					if ((x) < (nextLocation.x * Tile.SIZE)) {
						xx += 1;
						movedX = true;
					}

					if ((x) > (nextLocation.x * Tile.SIZE)) {
						xx -= 1;
						movedX = true;
					}

					if ((y) < (nextLocation.y * Tile.SIZE) && !movedX) yy += 1;

					if ((y) > (nextLocation.y * Tile.SIZE) && !movedX) yy -= 1;
					this.walkingSprite.update(updateCount, velocity);
					this.velocity = world.checkCollision(getPosition(), getPosition(), getEntitySize(), xx, yy, this);
					this.x += this.velocity.x;
					this.y += this.velocity.y;
				}
			}

		}

	}

	@Override
	public void onSpawn() {

	}
}
