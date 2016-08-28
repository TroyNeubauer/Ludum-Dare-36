package com.troy.ludumdare.ai;

import com.troy.ludumdare.Item.*;
import com.troy.ludumdare.Item.WeaponStats.*;
import com.troy.ludumdare.ai.Battle.*;
import com.troy.ludumdare.util.*;

public class Battles {

	public static void init() {
		BattleManager.addBattle(new Battle(1, -50000, 10, "You are a person in 50000 BC. Your group of hunters and gauthers decide to "//
			+ "battle another group of hunters and gathers at a misterious circle or rocks. Defeat your opponent to move on", //
			Assets.villageOpponent, BattleStrategy.RANGED_FIGHT,//
			new Item(1, "enemy bow", new WeaponStats(0.5f, 120, 2f, 1f, 3, Assets.bow, DamageType.RANGED)), //
			30, 20, 10f, 100f, Item.BASIC_BOW));//

		BattleManager.addBattle(new Battle(2, -49999, 15, "Your group is amazed by your skill, and you decide to battle again..", //
			Assets.villageOpponent, //
			BattleStrategy.RANGED_FIGHT, new Item(1, "enemy bow", new WeaponStats(0.5f, 100, 2f, 1f, 3, Assets.bow, DamageType.RANGED)), //
			30, 20, 10f, 100f, Item.BASIC_BOW));
		
		BattleManager.addBattle(new Battle(3, -49999, 15, "Yet again, your group is amazed by your skill, and you decide to battle again..", //
			Assets.villageOpponent, //
			BattleStrategy.RANGED_FIGHT, new Item(1, "enemy bow", new WeaponStats(0.5f, 100, 2f, 1f, 3, Assets.bow, DamageType.RANGED)), //
			30, 20, 10f, 100f, Item.BASIC_BOW));
		
		BattleManager.addBattle(new Battle(4, -49998, 15, "The tribe is now scared of your fighting ability. "//
			+ "If you win this battle, you will cause the tribe to flee and you will get their best bow", //
			Assets.villageOpponent, //
			BattleStrategy.RANGED_RUN, new Item(1, "enemy bow", new WeaponStats(0.5f, 180, 2f, 1f, 3, Assets.bow, DamageType.RANGED)), //
			30, 20, 10f, 100f, Item.BASIC_BOW));
	}
}
