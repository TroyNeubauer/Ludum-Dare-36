package com.troy.ludumdare.entity;

import com.troy.ludumdare.Item.*;
import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.sound.*;
import com.troy.ludumdare.world.*;
import com.troy.troyberry.math.*;


public class EntityArrow extends Entity {
	
	public float velX, velY, posX, posY;
	public AngledSprite angledSprite;
	public int age;
	private WeaponStats stats;
	private EntityLiving parent;
	
	public static Sound hit = new Sound("hit");

	public EntityArrow(float x, float y, float velX, float velY, AngledSprite sprite, EntityLiving parent, WeaponStats stats) {
		super(Maths.round(x), Maths.round(y), sprite.getBasicSprite());
		this.velX = velX;
		this.velY = velY;
		this.posX = x;
		this.posY = y;
		this.angledSprite = sprite;
		this.age = 0;
		this.parent = parent;
		this.stats = stats;
	}
	
	@Override
	public void render(Screen screen, World world){
		
		double angle = Vector2f.getAngleFromPoint(new Vector2f(0, 0), new Vector2f(velX, velY));
		screen.drawSprite(angledSprite.getCurrentSprite(angle + 180), Math.round(posX), Math.round(posY), world, true);
	}


	@Override
	public void update(World world, int updateCount) {
		age++;
		if(age > 180) world.removeEntity(this);
		int xvel = 0, yvel = 0;
		if(velX != 0f && velX < 1f && velX > -1f){
			xvel = (velX > 0) ? 1 : -1;
		}else{
			xvel = Maths.round(velX);
		}
		
		if(velY != 0f && velY < 1f && velY > -1f){
			yvel = (velY > 0) ? 1 : -1;
		}else{
			yvel = Maths.round(velY);
		}
		
		Vector2i preCollision = new Vector2i(xvel, yvel);
		Vector2i postCollision = world.checkCollision(xvel, yvel, this, true);
		if(preCollision.equals(postCollision)){
			this.posX += velX;
			this.posY += velY;
			this.x = Math.round(posX);
			this.y = Math.round(posY);
		}else{
			velX = 0f;
			velY = 0f;
		}
		
		for(Entity e : world.entities){
			if(e instanceof EntityLiving){
				EntityLiving entity = (EntityLiving) e;
				if(entity.equals((Entity)parent)){
					continue;
				}
				if(Maths.inRange(this.x, e.x + 4, e.x + 28) && Maths.inRange(this.y, e.y, e.y + 32) && velX != 0f && velY != 0f){
					world.removeEntity(this);
					entity.hitCountDown = 15;
					entity.damage(stats.getRangedDamage());
					hit.play();
				}
			}
		}
	}
	
	@Override
	public void onSpawn() {
		
	}
}
