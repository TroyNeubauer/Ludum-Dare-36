package com.troy.ludumdare.battle;

import java.awt.*;
import java.util.*;
import java.util.List;
import com.troy.ludumdare.*;
import com.troy.ludumdare.Item.*;
import com.troy.ludumdare.entity.*;
import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.input.*;
import com.troy.ludumdare.ui.*;
import com.troy.ludumdare.util.*;
import com.troy.ludumdare.world.*;
import com.troy.troyberry.math.*;

public class BattleManager {

	public static final List<Battle> BATTLES = new ArrayList<Battle>(100);
	private static Text enemyHealth = new Text("Health", 100, 5, 15, Font.BOLD, 0);
	private static EntityPlayer player;
	private static World world;
	private static Battle currentBattle;
	private static EntityEnemy enemy;
	public static boolean battleRunning = false, showingBriefing = false, showingWin = false, showingLoose = false, wonGame = false;
	private static int battleIndex = 1;
	private static float goldPenalty = 1;
	private static Text possibleGold, battleDescription, yourItem, enterToStart, youGotXGold, youWon, youLost;

	public static void init() {
		for (Battle battle : BATTLES) {
			System.out.println(battle);
		}
	}

	public static void start(EntityPlayer player, World world) {
		BattleManager.player = player;
		BattleManager.world = world;
		battleRunning = true;
		showingBriefing = false;
		showingWin = false;
		showingLoose = false;
		currentBattle = getBattle(battleIndex);
		System.out.println("starting battle #" + currentBattle.number);
		player.setItem(currentBattle.itemToUse);
		UI.inventory.addItem(currentBattle.itemToUse);
		UIInventory.selectedItem = currentBattle.itemToUse;
		player.hasControl = true;
		world.year = currentBattle.year;

		enemy = new EntityEnemy(currentBattle.enemyX, currentBattle.enemyY, currentBattle.enemy, //
			currentBattle.enemyHealth, new Vector2i(0, 0), currentBattle, player);
		world.add(enemy);
		enemy.canShoot = true;
		player.setHealth(currentBattle.playerHealth);
		player.x = 30 * 16;
		player.y = 30 * 16;

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
		} else {
			if (wonGame) {
				
			} else if (showingBriefing) {
				if (Controls.NEXT.hasBeenPressed() || Controls.NEXT2.hasBeenPressed()) {
					prepare();
				}
			} else if (showingWin) {
				if (Controls.NEXT.hasBeenPressed() || Controls.NEXT2.hasBeenPressed()) {
					showBriefing();
				}
			} else if (showingLoose) {
				if (Controls.NEXT.hasBeenPressed() || Controls.NEXT2.hasBeenPressed()) {
					reset();
				}
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
			if (showingBriefing) {
				TextMaster.addText(battleDescription);
				TextMaster.addText(possibleGold);
				TextMaster.addText(yourItem);
				Item item = currentBattle.itemToUse;
				screen.drawSprite(item.stats.sprite, Game.screen.width / 2 + 8, 132, world, false);
				TextMaster.addText(enterToStart);
			}
			if (showingWin) {
				TextMaster.addText(youGotXGold);
				TextMaster.addText(youWon);
				TextMaster.addText(enterToStart);
			}
			if (showingLoose) {
				TextMaster.addText(enterToStart);
				TextMaster.addText(youLost);
			}
		}
	}

	public static void win() {
		battleIndex++;
		goldPenalty = 1f;
		player.hasControl = false;
		System.out.println("you won!");
		enemy.kill(world);
		world.removeEntity(enemy);
		enemy = null;
		showWin();
	}

	public static void loose() {
		goldPenalty++;
		System.out.println("you lost!");
		enemy.canShoot = false;
		System.out.println(player.getHealth());
		world.killArrows();
		showLoose();

	}

	private static void showLoose() {
		System.out.println("showing loose");
		showingBriefing = false;
		battleRunning = false;
		showingWin = false;
		showingLoose = true;
		youLost = new Text("You Lost!", Game.screen.width / 2 - 15, 30, 40, Font.BOLD, 0);
	}

	private static void showBriefing() {
		System.out.println("showing briefing");
		currentBattle = getBattle(battleIndex);
		if (currentBattle == null) return;
		showingBriefing = true;
		battleRunning = false;
		showingWin = false;
		showingLoose = false;

		possibleGold = new Text("You can get up to " + getTotalGold() + " Gold", Game.screen.width / 2 - 50, 40, 24, Font.BOLD, 0);
		battleDescription = new Text(currentBattle.description, 0, 60, 20, Font.BOLD, 0);
		yourItem = new Text("Your item:", Game.screen.width / 2 - 15, 120, 28, Font.BOLD, 0);

	}

	private static void showWin() {
		System.out.println("showing win");
		showingBriefing = false;
		battleRunning = false;
		showingWin = true;
		showingLoose = false;
		youGotXGold = new Text("You got " + getTotalGold() + " Gold", Game.screen.width / 2 - 20, 100, 24, Font.BOLD, 0);
		youWon = new Text("You Won!", Game.screen.width / 2 - 15, 30, 40, Font.BOLD, 0);
	}

	private static void prepare() {
		System.out.println("preparing");
		start(player, world);

	}

	private static void reset() {
		System.out.println("reseting");
		player = new EntityPlayer(30 * 16, 30 * 16, Assets.basicPlayer, currentBattle.playerHealth);
		start(player, world);

	}

	public static void beginLoop(EntityPlayer player, World world) {
		System.out.println("starting loop");
		BattleManager.currentBattle = getBattle(battleIndex);
		BattleManager.player = player;
		BattleManager.world = world;
		goldPenalty = 1f;
		enterToStart = new Text("Press Enter or Shift to move on", Game.screen.width / 2 - 45, Game.screen.height - 18, 20, Font.BOLD, 0);
		player.hasControl = false;

		showBriefing();
	}

	public static int getTotalGold() {
		if (currentBattle == null) return 0;
		return Maths.round(currentBattle.reward / goldPenalty);
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
		winGame();
		return null;
	}

	private static void winGame() {
		battleRunning = false;
		showingBriefing = false;
		showingWin = false;
		showingLoose = false;
		wonGame = true;
		System.out.println("you beat the game!");

	}
}
