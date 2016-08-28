package com.troy.ludumdare.ai;

import java.awt.*;
import java.util.*;
import java.util.List;
import com.troy.ludumdare.*;
import com.troy.ludumdare.entity.*;
import com.troy.ludumdare.gamestate.*;
import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.ui.*;
import com.troy.ludumdare.world.*;
import com.troy.troyberry.math.*;

public class BattleManager {

	public static final List<Battle> BATTLES = new ArrayList<Battle>(100);
	private static Text enemyHealth = new Text("Health", 100, 5, 15, Font.BOLD, 0);
	private static EntityPlayer player;
	private static World world;
	private static Battle currentBattle;
	private static EntityEnemy enemy;
	public static boolean battleRunning = false, battleStatPanelOpen = false, battleBriefingPanelOpen = false;
	private static int battleIndex = 1;
	private static UIPanel battleStatPanel, battleBriefingPanel;

	public static void init() {
		for (Battle battle : BATTLES) {
			System.out.println(battle);
		}
	}

	public static void start(EntityPlayer player, World world) {
		BattleManager.player = player;
		BattleManager.world = world;
		battleRunning = true;
		currentBattle = getBattle(battleIndex);
		System.out.println("starting battle #" + currentBattle.number);
		player.setItem(currentBattle.enemyItem);
		player.hasControl = true;
		world.year = currentBattle.year;

		enemy = new EntityEnemy(currentBattle.enemyX, currentBattle.enemyY, currentBattle.enemy, //
			currentBattle.enemyHealth, new Vector2i(0, 0), currentBattle, player);
		LevelState.world.add(enemy);
		player.setHealth(currentBattle.playerHealth);
		player.x = 30 * 16;
		player.y = 30 * 16;

	}

	public Battle getNextBattle() {
		return BATTLES.get(battleIndex + 1);
	}

	public static void update(int updateCount) {
		if (battleRunning) {
			if (currentBattle.forcedItem) UI.inventoryOpen = false;
			if (enemy.isDead()) {
				win();
			}
			if (player.isDead()) {
				loose();
			}
		}else{
			if(battleStatPanelOpen){
				battleStatPanel.update(updateCount);
			}
		}
	}

	public static void render(Screen screen) {
		if (battleRunning) {
			enemyHealth.text = enemy.getHealth() + " HP";
			enemyHealth.x = enemy.x - world.xOffset + 8;
			enemyHealth.y = enemy.y - world.yOffset - 4;
			TextMaster.addText(enemyHealth);
		} else {
			if(battleStatPanelOpen){
				battleStatPanel.render(screen);
			}
		}
	}

	public static void win() {
		stop();
		battleIndex++;
		
		showOverviewPanel(true);
	}

	public static void loose() {
		stop();
		showOverviewPanel(false);

	}

	private static void showOverviewPanel(boolean result) {
		battleStatPanelOpen = true;
		battleRunning = false;
		battleStatPanel = new UIPanel(0, 0, Game.screen.width, Game.screen.height, 0xFFFFFF);
		Text text = new Text(((result) ? "Next Battle" : "Try Again"), 2, 1, 20, Font.BOLD, 0x0);
		battleStatPanel.add(new UIButton(Game.screen.width / 2 - 20, (Game.screen.height / 3) * 2, 48, 12, battleStatPanel, 0xAAAAAA, text));

	}

	public static void reset() {

	}

	public static void stop() {
		player.hasControl = false;
		battleRunning = false;
		enemy.kill(world);
		world.removeEntity(enemy);
		enemy = null;
		player.setHealth(100f);
	}

	private BattleManager() {

	}

	public static void addBattle(Battle battle) {
		BATTLES.add(battle);

	}

	public static Battle getBattle(int battleIndex) {
		for (Battle battle : BATTLES) {
			if (battle.number == battleIndex) return battle;
		}
		return BATTLES.get(0);
	}

}
