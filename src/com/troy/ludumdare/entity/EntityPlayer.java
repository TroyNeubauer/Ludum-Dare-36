package com.troy.ludumdare.entity;

import com.troy.ludumdare.*;
import com.troy.ludumdare.Item.WeaponStats.*;
import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.input.*;
import com.troy.ludumdare.sound.*;
import com.troy.ludumdare.ui.*;
import com.troy.ludumdare.world.*;
import com.troy.troyberry.math.*;

/** Represents the player **/
public class EntityPlayer extends EntityLiving {

	public static UIInventory inventory;

	Sound shootSound = new Sound("shoot");
	public int cooldown;
	public boolean hasControl;

	public EntityPlayer(int x, int y, WalkingSprite walkingSprite, float health) {
		super(x, y, walkingSprite, health);
		this.hasControl = false;
		this.cooldown = 0;
	}

	@Override
	public void update(World world, int updateCount) {
		this.hitCountDown--;
		this.cooldown++;
		if (isDead()) return;
		int xx = 0;
		int yy = 0;
		if (hasControl) {
			if (Keyboard.isKeyDown(Keyboard.KEY_W)) yy -= 1;
			if (Keyboard.isKeyDown(Keyboard.KEY_S)) yy += 1;
			if (Keyboard.isKeyDown(Keyboard.KEY_A)) xx -= 1;
			if (Keyboard.isKeyDown(Keyboard.KEY_D)) xx += 1;
		}
		this.walkingSprite.update(updateCount, new Vector2i(xx, yy));

		velocity = world.checkCollision(xx, yy, this, false);
		xx = velocity.x;
		yy = velocity.y;
		x += xx;
		y += yy;
		if (Controls.SHOOT.hasBeenPressed() && cooldown > EntityPlayer.inventory.getSelectedItem().stats.cooldown && hasControl) {
			shootSound.play();
			cooldown = 0;
			if (inventory.getSelectedItem() != null) {
				if (inventory.getSelectedItem().stats.type == DamageType.RANGED) {
					world.shootArrow(Game.screen.width / 2 + 8, Game.screen.height / 2 + 8, Input.mouseX, Input.mouseY, this,
						EntityPlayer.inventory.getSelectedItem().stats);
				}else{
					world.hit(x + 16, y + 16, Input.mouseX + world.xOffset, Input.mouseY + world.yOffset, inventory.getSelectedItem().stats, this);
				}
			}
		}
	}

	public void render(Screen screen, World world) {
		if (isDead()) return;
		Sprite sprite = this.walkingSprite.getCurrentSprite();
		if (hitCountDown > 0) {
			sprite = sprite.getHitSprite();
		}
		screen.drawSprite(sprite, x, y, world, true);
	}

	@Override
	public void onDeath() {

	}

	@Override
	public void onSpawn() {
	}

}
