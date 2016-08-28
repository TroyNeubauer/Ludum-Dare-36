package com.troy.ludumdare.battle;

import com.troy.ludumdare.Item.*;
import com.troy.ludumdare.battle.Battle.*;
import com.troy.ludumdare.util.*;

public class Battles {

	public static void init() {
		BattleManager.addBattle(new Battle(1, -50000, 10,
			"You are a person in 50000 BC. Your group of hunters and gauthers decide to "//
				+ "battle\nanother group of hunters\n and gathers at a misterious circle or rocks. \nDefeat your opponent to move on", //
			Assets.villageOpponent, BattleStrategy.RANGED_FIGHT, Item.BOW_II, 30, 20, 10f, 30f, Item.BASIC_BOW));//

		BattleManager.addBattle(new Battle(2, -49999, 15, "Your group is amazed by your skill, and you decide to battle again..", //
			Assets.villageOpponent, BattleStrategy.RANGED_FIGHT, Item.BOW_II, 30, 20, 10f, 30f, Item.BASIC_BOW));

		BattleManager.addBattle(new Battle(3, -49998, 15,
			"The tribe is now scared of your fighting ability. "//
				+ "If you win this battle, you will cause the tribe to flee and you will get their best bow", //
			Assets.villageOpponent, BattleStrategy.RANGED_RUN, Item.BOW_II, 30, 20, 10f, 30f, Item.BASIC_BOW));
	}
}
