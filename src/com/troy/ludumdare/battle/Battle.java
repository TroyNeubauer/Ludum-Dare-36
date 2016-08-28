package com.troy.ludumdare.battle;

import com.troy.ludumdare.Item.*;
import com.troy.ludumdare.graphics.*;

public class Battle {

	public final int number, enemyX, enemyY, year, reward;
	public final String description;
	public final WalkingSprite enemy;
	public final BattleStrategy strategy;
	public final Item itemToUse, enemyItem;
	public final boolean forcedItem;
	public final float enemyHealth, playerHealth;

	public Battle(int number, int year, int reward, String description, WalkingSprite enemy, BattleStrategy strategy, Item enemyItem, int enemyX, int enemyY, float enemyHealth, float playerHealth, Item itemToUse) {
		this.year = year;
		this.reward = reward;
		this.number = number;
		this.description = description;
		this.enemy = enemy;
		this.strategy = strategy;
		this.itemToUse = itemToUse;
		this.forcedItem = (itemToUse == null) ? false : true;
		this.enemyItem = enemyItem;
		this.enemyHealth = enemyHealth;
		this.playerHealth = playerHealth;
		this.enemyX = enemyX * 16;
		this.enemyY = enemyY * 16;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Battle #" + number + " Description:\n" + this.description + "\n");
		sb.append("strategy " + this.strategy);
		sb.append(" Item for player is " + itemToUse.name + "\n");
		sb.append("forced item " + forcedItem + " enemy item " + this.enemyItem + "\n");
		sb.append("Player health " + playerHealth + " enemy health " + enemyHealth + "\n\n");
		
		return sb.toString();
	}

	public enum BattleStrategy {
		MELE_FIGHT, MELE_RUN, RANGED_FIGHT, RANGED_RUN
	}

}
