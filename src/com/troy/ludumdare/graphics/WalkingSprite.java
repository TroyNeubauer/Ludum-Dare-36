package com.troy.ludumdare.graphics;

import com.troy.troyberry.math.*;

public class WalkingSprite {

	private final int x, y;

	/** Width and height of the an individual sprite **/
	public final int spriteWidth, spriteHeight;
	private Sprite curretSprite;

	private int direction, swapInterval, index;
	private final Sprite[] foreward, backward, left, right;

	/** The sprite sheet that the sprite belongs to **/
	private final SpriteSheet spriteSheet;

	public WalkingSprite(int x, int y, int spriteWidth, int spriteHeight, SpriteSheet sheet, int swapInterval) throws Exception {
		this.x = x;
		this.y = y;
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		this.spriteSheet = sheet;
		this.direction = 0;
		this.index = 0;
		this.swapInterval = Maths.millasecondsToUpdates(swapInterval, 60);
		curretSprite = new Sprite(x, y, spriteWidth, spriteHeight, spriteSheet);

		this.foreward = new Sprite[] { new Sprite(x, y, spriteWidth, spriteHeight, spriteSheet),
			new Sprite(x + spriteWidth * 1, y, spriteWidth, spriteHeight, spriteSheet),
			new Sprite(x + spriteWidth * 2, y, spriteWidth, spriteHeight, spriteSheet) };

		this.backward = new Sprite[] { new Sprite(x, y + spriteWidth * 1, spriteWidth, spriteHeight, spriteSheet),
			new Sprite(x + spriteWidth * 1, y + spriteWidth * 1, spriteWidth, spriteHeight, spriteSheet),
			new Sprite(x + spriteWidth * 2, y + spriteWidth * 1, spriteWidth, spriteHeight, spriteSheet) };

		this.left = new Sprite[] { new Sprite(x, y + spriteWidth * 2, spriteWidth, spriteHeight, spriteSheet),
			new Sprite(x + spriteWidth * 1, y + spriteWidth * 2, spriteWidth, spriteHeight, spriteSheet),
			new Sprite(x + spriteWidth * 2, y + spriteWidth * 2, spriteWidth, spriteHeight, spriteSheet) };

		this.right = new Sprite[] { new Sprite(x, y + spriteWidth * 3, spriteWidth, spriteHeight, spriteSheet),
			new Sprite(x + spriteWidth * 1, y + spriteWidth * 3, spriteWidth, spriteHeight, spriteSheet),
			new Sprite(x + spriteWidth * 2, y + spriteWidth * 3, spriteWidth, spriteHeight, spriteSheet) };

	}

	public WalkingSprite(WalkingSprite attacker) throws Exception {
		this.x = attacker.x;
		this.y = attacker.y;
		this.spriteWidth = attacker.spriteWidth;
		this.spriteHeight = attacker.spriteHeight;
		this.spriteSheet = attacker.spriteSheet;
		this.direction = 0;
		this.index = 0;
		this.swapInterval = attacker.swapInterval;
		curretSprite = new Sprite(x, y, spriteWidth, spriteHeight, spriteSheet);

		this.foreward = new Sprite[] { new Sprite(x, y, spriteWidth, spriteHeight, spriteSheet),
			new Sprite(x + spriteWidth * 1, y, spriteWidth, spriteHeight, spriteSheet),
			new Sprite(x + spriteWidth * 2, y, spriteWidth, spriteHeight, spriteSheet) };

		this.backward = new Sprite[] { new Sprite(x, y + spriteWidth * 1, spriteWidth, spriteHeight, spriteSheet),
			new Sprite(x + spriteWidth * 1, y + spriteWidth * 1, spriteWidth, spriteHeight, spriteSheet),
			new Sprite(x + spriteWidth * 2, y + spriteWidth * 1, spriteWidth, spriteHeight, spriteSheet) };

		this.left = new Sprite[] { new Sprite(x, y + spriteWidth * 2, spriteWidth, spriteHeight, spriteSheet),
			new Sprite(x + spriteWidth * 1, y + spriteWidth * 2, spriteWidth, spriteHeight, spriteSheet),
			new Sprite(x + spriteWidth * 2, y + spriteWidth * 2, spriteWidth, spriteHeight, spriteSheet) };

		this.right = new Sprite[] { new Sprite(x, y + spriteWidth * 3, spriteWidth, spriteHeight, spriteSheet),
			new Sprite(x + spriteWidth * 1, y + spriteWidth * 3, spriteWidth, spriteHeight, spriteSheet),
			new Sprite(x + spriteWidth * 2, y + spriteWidth * 3, spriteWidth, spriteHeight, spriteSheet) };
	}

	public void update(int updateCount, Vector2i velocity) {
		this.index = (updateCount % (4 * swapInterval)) / swapInterval;
		boolean moving = true;
		if (velocity.x == 0 && velocity.y == 0) moving = false;

		this.direction = getDirection(velocity);
		this.curretSprite = getSprite(direction, index, moving);
	}

	private Sprite getSprite(int direction, int index, boolean moving) {
		if (!moving) return getArray(direction)[0];
		if (index == 0) return getArray(direction)[1];
		if (index == 1) return getArray(direction)[0];
		if (index == 2) return getArray(direction)[2];
		if (index == 3) return getArray(direction)[0];
		return null;
	}

	private int getDirection(Vector2i velocity) {
		if (velocity.y < 0) return 0;
		if (velocity.x > 0) return 1;
		if (velocity.y > 0) return 2;
		if (velocity.x < 0) return 3;

		return 2;
	}

	public Sprite[] getArray(int direction) {
		switch (direction) {
		case 0:
			return this.backward;
		case 1:
			return this.right;

		case 2:
			return this.foreward;

		case 3:
			return this.left;

		}
		return null;
	}

	public Sprite getCurrentSprite() {
		if(curretSprite == null)return foreward[0];
		return curretSprite;
	}

	public Sprite getBasicSprite() {
		return foreward[0];
	}

}
