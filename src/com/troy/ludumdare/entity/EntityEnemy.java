package com.troy.ludumdare.entity;

import java.util.*;
import com.troy.ludumdare.Item.*;
import com.troy.ludumdare.battle.*;
import com.troy.ludumdare.battle.Battle.*;
import com.troy.ludumdare.gamestate.*;
import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.tile.*;
import com.troy.ludumdare.world.*;
import com.troy.troyberry.math.*;

public class EntityEnemy extends EntityNPC {

	private Random random;
	private Battle battle;
	private EntityPlayer player;
	private Item item;
	private int coolDown;
	public boolean canShoot;

	public EntityEnemy(int x, int y, WalkingSprite attacker, float health, Vector2i velocity, Battle battle, EntityPlayer player) {
		super(x, y, attacker, health, velocity);
		this.random = new Random();
		this.battle = battle;
		this.player = player;
		this.item = battle.enemyItem;
		canShoot = true;
	}

	@Override
	public void onDeath() {

	}

	@Override
	public void render(Screen screen, World world) {
		if (isDead()) return;
		Sprite sprite = this.walkingSprite.getCurrentSprite();
		if (hitCountDown > 0) {
			sprite = sprite.getHitSprite();
		}
		screen.drawSprite(sprite, x, y, world, true);
	}

	@Override
	public void update(World world, int updateCount) {
		if (isDead()) return;
		this.hitCountDown--;
		this.coolDown++;
		this.walkingSprite.update(updateCount, this.velocity);
		if ((updateCount % 3) == 0) {
			updateTarget(world);
		}
		if (this.pathFindInstructions != null) {
			if (this.pathFindInstructions.size() > 0) {

				if (nextLocation != null) {
					Vector2i next = pathFindInstructions.get(pathFindInstructions.size() - 1).pos;
					this.nextLocation = new Vector2i(next.x * Tile.SIZE, next.y * Tile.SIZE);

					int xx = 0;
					int yy = 0;
					double distance = Maths.getDistanceBetweenPoints(x, y, player.x, player.y);
					if ((battle.strategy != BattleStrategy.MELE_FIGHT || battle.strategy != BattleStrategy.MELE_RUN) && this.canShoot) {
						this.shoot(world);
					}

					if ((battle.strategy == BattleStrategy.MELE_FIGHT) || (distance > 7 * 16 //
						&& battle.strategy == BattleStrategy.RANGED_FIGHT)) {

						int moveDistance = 1;
						if (battle.strategy == BattleStrategy.MELE_RUN) moveDistance = 2;

						if (y < nextLocation.y) yy += moveDistance;

						if (y > nextLocation.y) yy -= moveDistance;

						if (x < nextLocation.x) xx += moveDistance;

						if (x > nextLocation.x) xx -= moveDistance;

					}
					this.velocity = world.checkCollision(xx, yy, this, false);
					this.walkingSprite.update(updateCount, new Vector2i(velocity.x, velocity.y));
					this.x += this.velocity.x;
					this.y += this.velocity.y;
				}
			}

		}

	}

	private void shoot(World world) {
		if (coolDown > item.stats.cooldown) {
			world.shootArrow(x - world.xOffset + 8, y - world.yOffset + 8, //
				player.x - world.xOffset + 8, player.y - world.yOffset + 8, item.stats.getRangeSpeed(), false, this, item.stats);
			coolDown = 0;
		}

	}

	private void updateTarget(World world) {
		EntityPlayer player = LevelState.player;
		if (player == null) return;
		Vector2i goal = new Vector2i(player.x / Tile.SIZE, player.y / Tile.SIZE);
		Vector2i start = new Vector2i(x / Tile.SIZE, y / Tile.SIZE);
		this.setPathFinding(world.findPath(start, goal));
		if (pathFindInstructions != null) {
			if (pathFindInstructions.size() > 0) {
				Vector2i next = pathFindInstructions.get(pathFindInstructions.size() - 1).pos;
				this.nextLocation = new Vector2i(next.x * Tile.SIZE, next.y * Tile.SIZE);
			}
		}
	}

	@Override
	public void onSpawn() {

	}
}
