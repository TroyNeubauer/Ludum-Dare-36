package com.troy.ludumdare.battle;

import com.troy.ludumdare.Item.*;
import com.troy.ludumdare.battle.Battle.*;
import com.troy.ludumdare.util.*;

public class Battles {

	public static void init() {
		BattleManager.addBattle(new Battle(1, -50000, 10,
			"You are a person living in 50000 BC. Your group of hunters and gauthers decide to\n"//
				+ "battle another group of hunters and gathers at a misterious circle or rocks. \nDefeat your opponent to move on", //
			Assets.villageOpponent, BattleStrategy.RANGED_FIGHT, Item.BOW_II, 30, 20, 10f, 50f, Item.BASIC_BOW));//

		BattleManager.addBattle(new Battle(2, -49999, 15, "The opposing group cant accecpt defeat. So you battle again...", //
			Assets.villageOpponent, BattleStrategy.RANGED_FIGHT, Item.BOW_II, 30, 20, 10f, 50f, Item.BASIC_BOW));

		BattleManager.addBattle(new Battle(3, -49998, 15,
			"The opposing group is now scared of your fighting ability.\n"//
				+ "If you win this battle, they will flee and their best bow will become yours", //
			Assets.villageOpponent, BattleStrategy.RANGED_RUN, Item.BOW_II, 30, 20, 10f, 50f, Item.BASIC_BOW));
		
		BattleManager.addBattle(new Battle(4, -49995, 50,
			"A Barbarian King has heard of you and he decides to fight you.",//
			Assets.barbarianKing, BattleStrategy.RANGED_FIGHT, Item.BARBARIAN_BOW, 30, 20, 50f, 50f, Item.BOW_II));
		
		BattleManager.addBattle(new Battle(5, -49895, 5,
			"Congratsulants! You have defeated the evil Barbarian King! However,\nbefore he let out his last breath, he "
			+ "put a curse on you! Because of the curse, you\nshall never leave the arena! You also shall never die. If "
			+ "other people want to duel,\n you cannot turn down their offer!",//
			Assets.villageOpponent, BattleStrategy.RANGED_FIGHT, Item.BOW_II, 20, 20, 50f, 50f, Item.STEEL_SWORD));
		
		BattleManager.addBattle(new Battle(6, -40000, 6,
			"Because of your curse, years pass by the hundreds, then thousands. \nYet the cuse pervents you from ageing, "
			+ "so you never feel weak or old.",//
			Assets.villageOpponent, BattleStrategy.RANGED_FIGHT, Item.BOW_II, 40, 20, 50f, 50f, null));
		
		BattleManager.addBattle(new Battle(7, -20000, 6,
			"As time passes, you notice something about fellow humans. They are\nstarting to become more inteligent."
			+ " Yet, there are allways people willing to fight you.",//
			Assets.villageOpponent, BattleStrategy.RANGED_FIGHT, Item.BOW_II, 30, 20, 50f, 50f, null));
		
		BattleManager.addBattle(new Battle(8, -15000, 6,
			"",//
			Assets.villageOpponent, BattleStrategy.RANGED_FIGHT, Item.BOW_II, 30, 20, 50f, 50f, null));
	}
}
