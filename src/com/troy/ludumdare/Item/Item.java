package com.troy.ludumdare.Item;

import com.troy.ludumdare.Item.WeaponStats.*;
import com.troy.ludumdare.util.*;

public class Item {

	public int id;
	public String name;
	public WeaponStats stats;
	
	public static final Item BASIC_BOW = new Item(1, "Bow", new WeaponStats(1, 100, 2f, 1f, 25, 1, Assets.bow, DamageType.RANGED)),
		BOW_II = new Item(1, "Bow II", new WeaponStats(0.5f, 60, 5f, 1.2f, 20, 1, Assets.bow2, DamageType.RANGED)),
		BARBARIAN_BOW = new Item(1, "Barbarian Bow", new WeaponStats(3, 80, 7.5f, 2f, 25, 1, Assets.bow3, DamageType.RANGED)),
		BASIC_GUN = new Item(2, "Basic Gun", new WeaponStats(3, 40, 15.7f, 5f, 25, 1, Assets.basicGun, DamageType.RANGED)),
		COPPER_SWORD = new Item(3, "Copper Sword", new WeaponStats(4, 40, 0f, 1f, 0, 30, Assets.copperSword, DamageType.MELE)),
		BRONZE_SWORD = new Item(3, "Bronze Sword", new WeaponStats(5, 35, 0f, 1f, 0, 35, Assets.bronzeSword, DamageType.MELE)),
		IRON_SWORD = new Item(3, "Iron Sword", new WeaponStats(7, 25, 0f, 1f, 0, 40, Assets.ironSword, DamageType.MELE)),
		STEEL_SWORD = new Item(3, "Steel Sword", new WeaponStats(10, 25, 0f, 1f, 0, 45, Assets.steelSword, DamageType.MELE));

	public Item(int id, String name, WeaponStats stats) {
		this.id = id;
		this.name = name;
		this.stats = stats;
	}

	public boolean equals(Object other) {
		if (!(other instanceof Item)) return false;
		Item item = (Item) other;
		if (item.id == this.id && item.name.equals(this.name) && item.stats.meleAtack == this.stats.meleAtack
			&& item.stats.rangeAtack == this.stats.rangeAtack) return true;

		return false;
	}

}
