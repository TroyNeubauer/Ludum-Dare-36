package com.troy.ludumdare.Item;

import com.troy.ludumdare.Item.WeaponStats.*;
import com.troy.ludumdare.util.*;

public class Item {

	public int id;
	public String name;
	public WeaponStats stats;
	
	public static final Item BASIC_BOW = new Item(1, "Bow", new WeaponStats(1, 100, 5, 1f, 25,Assets.bow, DamageType.RANGED)),
		BOW_II = new Item(1, "Bow II", new WeaponStats(1.5f, 80, 7f, 1.2f, 20,Assets.bow, DamageType.RANGED));

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
