package com.troy.ludumdare.battle;

import com.troy.ludumdare.Item.*;
import com.troy.ludumdare.battle.Battle.*;
import com.troy.ludumdare.util.*;

public class Battles {

	public static void init() {
		BattleManager.addBattle(new Battle(1, -50000, 10,
			"You are a person living in 50000 BC. Your group of hunters and gauthers decide to\n"//
				+ "battle another group of hunters and gathers at a misterious circle or rocks. \nDefeat your opponent to move on", //
			Assets.villageOpponent, BattleStrategy.RANGED_FIGHT, Item.BOW_II, 30, 30, 10f, 30f, Item.BASIC_BOW));//

		BattleManager.addBattle(new Battle(2, -49999, 15, "The opposing group cant accecpt defeat. So you battle again...", //
			Assets.villageOpponent, BattleStrategy.RANGED_FIGHT, Item.BOW_II, 30, 30, 10f, 30f, Item.BASIC_BOW));

		BattleManager.addBattle(new Battle(3, -49998, 15,
			"The opposing group is now scared of your fighting ability.\n"//
				+ "If you win this battle, they will flee and their best bow will become yours", //
			Assets.villageOpponent, BattleStrategy.RANGED_RUN, Item.BOW_II, 30, 30, 10f, 30f, Item.BASIC_BOW));
		
		BattleManager.addBattle(new Battle(4, -49995, 50,
			"A Barbarian King has heard of you and he decides to battle you",//
			Assets.barbarianKing, BattleStrategy.RANGED_FIGHT, Item.BARBARIAN_BOW, 30, 20, 50f, 30f, Item.BOW_II));
	}
}
