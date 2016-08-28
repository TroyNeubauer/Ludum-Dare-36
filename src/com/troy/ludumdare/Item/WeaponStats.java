package com.troy.ludumdare.Item;

import com.troy.ludumdare.graphics.*;

public class WeaponStats {
	
	public final float meleAtack, rangeAtack, rangeSpeed;
	private final int rangeAccuracy;
	public final int cooldown, meleRange;
	public final Sprite sprite;
	public final DamageType type;
	
	
	public WeaponStats(float meleAtack, int cooldown, float rangeAtack, float rangeSpeed, int rangeAccuracy, int meleRange, Sprite sprite, DamageType type) {
		this.meleAtack = meleAtack;
		this.rangeAtack = rangeAtack;
		this.sprite = sprite;
		this.type = type;
		this.rangeSpeed = rangeSpeed;
		this.rangeAccuracy = rangeAccuracy;
		this.cooldown = cooldown;
		this.meleRange = meleRange;
	}
	
	public float getRangeSpeed(){
		if(type.equals(DamageType.RANGED)){
			return rangeAtack;
		}
		return 0;
	}
	
	public int getRangeAccuracy(){
		if(type.equals(DamageType.RANGED)){
			return rangeAccuracy;
		}
		return 1;
	}

	public float getRangedDamage(){
		if(type.equals(DamageType.RANGED)){
			return rangeAtack;
		}
		return 0;
	}
	
	public float getMeleDamage(){
		return meleAtack;
	}

	public enum DamageType{
		MELE,
		RANGED
	}

}
