package com.troy.ludumdare.entity;

import java.util.*;
import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.tile.*;
import com.troy.ludumdare.util.*;
import com.troy.troyberry.math.*;

public abstract class EntityNPC extends EntityLiving {

	protected Vector2i nextLocation;

	protected List<Node> pathFindInstructions;

	public EntityNPC(int x, int y, WalkingSprite walkingSprite, float health, Vector2i velocity) {
		super(x, y, walkingSprite, health);
		this.velocity = velocity;
	}

	public boolean hasReachedNextLocation() {
		if (nextLocation != null) {
			if (Maths.getDistanceBetweenPoints(x, y, nextLocation.x * Tile.SIZE, nextLocation.y * Tile.SIZE) < 0.4d) {
				this.nextLocation = getNextLocation();
				return true;
			}
		}
		return false;
	}

	public Vector2i getNextLocation() {
		int currentIndex = 0;
		Node currentNode = null;
		for (Node node : pathFindInstructions) {
			if (node.pos.equals(nextLocation)) {
				currentIndex = pathFindInstructions.indexOf(node);
				currentNode = node;
			}
		}
		if (currentIndex > 0) {
			nextLocation = pathFindInstructions.get((pathFindInstructions.indexOf(currentNode) - 1)).pos;
		}
		return nextLocation;
	}

	public void setPathFinding(List<Node> nodes) {
		this.pathFindInstructions = nodes;
	}
}
