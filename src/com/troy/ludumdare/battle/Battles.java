package com.troy.ludumdare.battle;

import com.troy.ludumdare.Item.*;
import com.troy.ludumdare.battle.Battle.*;
import com.troy.ludumdare.entity.*;
import com.troy.ludumdare.gamestate.*;
import com.troy.ludumdare.util.*;
import com.troy.troyberry.math.*;

public class Battles {

	public static void init() {
		BattleManager.addBattle(new Battle(1, -50000, 10,
			" You are a person living in 50000 BC. Your group of hunters and gatherers\n decide to"//
				+ "battle another group of hunters and gatherers at a mysterious\n circle or rocks.Defeat your opponent to move on", //
			Assets.villageOpponent, BattleStrategy.RANGED_FIGHT, Item.BOW_II, 30, 20, 10f, 50f, Item.BASIC_BOW));//

		BattleManager.addBattle(new Battle(2, -49999, 15, " The opposing group can't accecpt defeat. So you fight again...", //
			Assets.villageOpponent, BattleStrategy.RANGED_FIGHT, Item.BOW_II, 30, 20, 10f, 50f, Item.BASIC_BOW));

		BattleManager.addBattle(new Battle(3, -49998, 15,
			" The opposing group is now scared of your fighting ability.\n "//
				+ "If you win this battle, they will flee and their best bow will become yours", //
			Assets.villageOpponent, BattleStrategy.RANGED_RUN, Item.BOW_II, 30, 20, 10f, 50f, Item.BASIC_BOW));
		
		BattleManager.addBattle(new Battle(4, -49995, 150,
			" A Barbarian King has heard of you and he decides to fight you.",//
			Assets.barbarianKing, BattleStrategy.RANGED_FIGHT, Item.BARBARIAN_BOW, 30, 20, 50f, 50f, Item.BOW_II));
		
		BattleManager.addBattle(new Battle(5, -49895, 5,
			" Congratulations! You have defeated the evil Barbarian King! However,\n before he let out his last breath, he "
			+ "put a curse on you! Because of the curse,\n you shall never leave the arena! You also shall never die. If "
			+ "other people\n want to duel, you cannot turn down their offer!",//
			Assets.villageOpponent, BattleStrategy.RANGED_FIGHT, Item.BOW_II, 20, 20, 50f, 50f, Item.BARBARIAN_BOW));
		
		BattleManager.addBattle(new Battle(6, -40000, 5,
			" Because of your curse, years pass by the hundreds, then thousands. \n Yet the cuse prevents you from ageing, "
			+ "so you never feel weak or old.\n Also you can use any weapon from your collection.",//
			Assets.villageOpponent, BattleStrategy.RANGED_FIGHT, Item.BOW_II, 40, 20, 50f, 50f, null));
		
		BattleManager.addBattle(new Battle(7, -20000, 5,
			" As time passes, you notice something about fellow humans. They are\n starting to become more intelligent."
			+ " Yet, there are allways people willing\n to fight you.",//
			Assets.villageOpponent, BattleStrategy.RANGED_FIGHT, Item.BOW_II, 30, 20, 50f, 50f, null));

		
		BattleManager.addBattle(new Battle(8, -9800, 5,
			" Soon enough, the humans around you realize that by running the sand in the\n arena through some woven ropes, they "
			+ "can get rid of some of impurities\n in the sand. Also, with sharpned rocks on their side, they can cut down\n trees",//
			Assets.villageOpponent, BattleStrategy.RANGED_FIGHT, Item.BASIC_BOW, 30, 20, 50f, 50f, null));
		
		BattleManager.addBattle(new Battle(9, -7103, 20,
			" Other humans are starting to settle down instead of chasing herds of\n animals.A small town develeps near your arena."
			+ " The town hosts a\n tournament, with a large prize. However you can only use one of your\n most basic bows.",//
			Assets.villageOpponent, BattleStrategy.RANGED_FIGHT, Item.BARBARIAN_BOW, 30, 20, 50f, 50f, Item.BOW_II));
		
		BattleManager.addBattle(new Battle(10, -3103, 20,
			" One of the people in the town near you has discovered that when a\n orange colored hard rock is heated, it becomes very"
			+ "plyable.\n  He has put a stick on the end and shaped the rock into a sharp dual\n sided blade. To use your"
			+ "new weapon, stand close to a target and press space.",//
			Assets.villageOpponent, BattleStrategy.RANGED_FIGHT, Item.BARBARIAN_BOW, 30, 20, 50f, 50f, Item.COPPER_SWORD));
		
		BattleManager.addBattle(new Battle(11, -2556, 50,
			" Since your use of the sword was so legandary, the town has determened\n that this new rock needs a name. "
			+ "They have decided to call it copper.\n Watch out, your opponent is also weilding a Copper Sword",//
			Assets.villageOpponent, BattleStrategy.MELE_FIGHT, Item.COPPER_SWORD, 30, 20, 40f, 50f, null));
		
		BattleManager.addBattle(new Battle(12, -2143, 50,
			" Sometimes switching items mid game can be very useful you certainly will need to in this battle",//
			Assets.villageOpponent, BattleStrategy.MELE_FIGHT, Item.COPPER_SWORD, 30, 20, 20f, 50f, null, new //
			EntityEnemy(20 * 16, 20 * 16, Assets.villageOpponent, 60f, new Vector2i(0, 0), null, LevelState.player)));
		
		BattleManager.addBattle(new Battle(13, -1565, 50,
			" The people in the town have made an alloy with copper and tin.\n It is stronger than copper. It is called bronze",//
			Assets.villageOpponent, BattleStrategy.MELE_FIGHT, Item.COPPER_SWORD, 30, 20, 50f, 50f, Item.BRONZE_SWORD));
		
		BattleManager.addBattle(new Battle(14, -1052, 50,
			" Once again, the people in the town have discovered iron!\n It is even stronger than copper! Also the towns people"//
			+ " Have given\n you a nice red robe!",//
			Assets.villageOpponent, BattleStrategy.MELE_FIGHT, Item.IRON_SWORD, 30, 20, 40f, 50f, Item.IRON_SWORD));
		
		BattleManager.addBattle(new Battle(15, -50, 300,
			" Julius Caesar has heard of you and wished to duel...",//
			Assets.cesar, BattleStrategy.MELE_FIGHT, Item.IRON_SWORD, 30, 20, 140f, 50f, null));
		
		BattleManager.addBattle(new Battle(16, 745, 75,
			" The Vikings find out about you and request a duel...",//
			Assets.barbarianKing, BattleStrategy.MELE_FIGHT, Item.IRON_SWORD, 30, 20, 100f, 50f, null));
		
		BattleManager.addBattle(new Battle(17, 1056, 50,
			" The people of the town have mixed carbon and iron to make steel!\n They also are upgrading the arena!",//
			Assets.midevalPlayer, BattleStrategy.MELE_FIGHT, Item.STEEL_SWORD, 30, 20, 40, 50f, Item.STEEL_SWORD));
		
		BattleManager.addBattle(new Battle(18, 1492, 50,
			" Christopher Columbus asks for a duel of bows",//
			Assets.cesar, BattleStrategy.RANGED_FIGHT, Item.BARBARIAN_BOW, 30, 20, 50f, 50f, Item.BOW_II));
		
		BattleManager.addBattle(new Battle(19, 1556, 300,
			" With steel and some lead and gunpowder the gun was invented!\n Unfortunately, you will need to win to get one...",//
			Assets.midevalPlayer, BattleStrategy.RANGED_FIGHT, Item.BASIC_GUN, 30, 20, 30f, 50f, null));
		
		
		BattleManager.addBattle(new Battle(20, 1492, 300,
			" As time passes you wonder if you could ever leave the arena...",//
			Assets.midevalPlayer, BattleStrategy.RANGED_FIGHT, Item.STEEL_SWORD, 30, 20, 50f, 50f, Item.BASIC_GUN));
		
		BattleManager.addBattle(new Battle(21, 1785, 300,
			" George Washington asks for a duel.",//
			Assets.cesar, BattleStrategy.RANGED_FIGHT, Item.BASIC_GUN, 30, 20, 100f, 50f, Item.BASIC_GUN));
		
		BattleManager.addBattle(new Battle(22, 2016, 500,
			" A person who claims to be the barbarian's great great great....................................\n Grandson, says that he can lift toe curse if you beat him in a final duel...",//
			Assets.midevalPlayer, BattleStrategy.RANGED_FIGHT, Item.BASIC_BOW, 30, 20, 75f, 50f, Item.BASIC_BOW));
		
	}
}
