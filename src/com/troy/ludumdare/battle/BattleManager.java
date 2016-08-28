package com.troy.ludumdare.battle;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.sound.sampled.*;
import com.troy.ludumdare.*;
import com.troy.ludumdare.Item.*;
import com.troy.ludumdare.entity.*;
import com.troy.ludumdare.gamestate.*;
import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.input.*;
import com.troy.ludumdare.sound.*;
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
	private static int battleIndex = 4;
	private static float goldPenalty = 1;
	private static Text possibleGold, battleDescription, yourItem, enterToStart, youGotXGold, youWon, youLost;
	private static Sound next = new Sound("GUInext"), die = new Sound("die"), winGame = new Sound("winGame");
	private static Clip clip;

	public static void init() {
		for (Battle battle : BATTLES) {
			System.out.println(battle);
		}
	}

	public static void start(EntityPlayer player, World world) {
	
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Class.class.getResourceAsStream("/sounds/music.wav")));
			clip.start();
			clip.loop(-1);
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			System.err.println("Couldn't play music");
			e.printStackTrace();
		}
		
		BattleManager.player = player;
		BattleManager.world = world;
		battleRunning = true;
		showingBriefing = false;
		showingWin = false;
		showingLoose = false;
		currentBattle = getBattle(battleIndex);
		System.out.println("starting battle #" + currentBattle.number);
		player.setItem(currentBattle.itemToUse);
		player.dead = false;
		UI.inventory.addItem(currentBattle.itemToUse);
		UIInventory.selectedItem = currentBattle.itemToUse;
		player.hasControl = true;
		world.year = currentBattle.year;

		enemy = new EntityEnemy(currentBattle.enemyX + Maths.randomInt(-15, 15), currentBattle.enemyY + Maths.randomInt(-15, 15), currentBattle.enemy, //
			currentBattle.enemyHealth, new Vector2i(0, 0), currentBattle, player);
		world.add(enemy);
		enemy.canShoot = true;
		player.setHealth(currentBattle.playerHealth);
		player.x = 30 * 16;
		player.y = 30 * 16;
		//enemy.kill(world);

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
					next.play();
				}
			} else if (showingWin) {
				if (Controls.NEXT.hasBeenPressed() || Controls.NEXT2.hasBeenPressed()) {
					showBriefing();
					next.play();
				}
			} else if (showingLoose) {
				if (Controls.NEXT.hasBeenPressed() || Controls.NEXT2.hasBeenPressed()) {
					reset();
					next.play();
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
				if(Maths.intersect(Game.screen.width / 2 + 6, Game.screen.width / 2 + 6 + 20, Input.mouseX) && 
					Maths.intersect(130, 130 + 20, Input.mouseY)){
					for (int y = 0; y < 20; y++) {
						for (int x = 0; x < 20; x++) {
							screen.drawPixel((x + Game.screen.width / 2 + 6) + (y + 130) * screen.width, 0xAAAAAA);
							
						}
					}
					TextMaster.addText(new Text(item.name, Input.mouseX, Input.mouseY, 20, Font.BOLD, 0));
					
				}
				screen.drawSprite(item.stats.sprite, Game.screen.width / 2 + 10, 132, world, false);
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
		clip.stop();
		battleIndex++;
		goldPenalty = 1f;
		player.hasControl = false;
		enemy.canShoot = false;
		enemy.kill(world);
		world.removeEntity(enemy);
		enemy = null;
		showWin();
	}

	public static void loose() {
		clip.stop();
		goldPenalty++;
		die.play();
		enemy.canShoot = false;
		showLoose();
	}

	private static void showLoose() {
		showingBriefing = false;
		battleRunning = false;
		showingWin = false;
		showingLoose = true;
		youLost = new Text("You Lost Battle #" + currentBattle.number + "!", Game.screen.width / 2 - 70, 30, 40, Font.BOLD, 0);
	}

	private static void showBriefing() {
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
		showingBriefing = false;
		battleRunning = false;
		showingWin = true;
		showingLoose = false;
		youGotXGold = new Text("You got " + getTotalGold() + " Gold", Game.screen.width / 2 - 30, 120, 24, Font.BOLD, 0);
		youWon = new Text("You Won Battle #" + currentBattle.number + "!", Game.screen.width / 2 - 60, 30, 40, Font.BOLD, 0);
	}

	private static void prepare() {
		currentBattle = getBattle(battleIndex);
		world.removeEntity(player);
		player = null;
		player = new EntityPlayer(30 * 16 + 1, 30 * 16, Assets.basicPlayer, currentBattle.playerHealth, currentBattle.itemToUse);
		world.add(player);
		LevelState.player = player;
		start(player, world);

	}

	private static void reset() {
		enemy.kill(world);
		world.removeEntity(player);
		world.removeEntity(enemy);
		enemy = null;
		player = null;
		player = new EntityPlayer(30 * 16 + 1, 30 * 16, Assets.basicPlayer, currentBattle.playerHealth, currentBattle.itemToUse);
		world.add(player);
		LevelState.player = player;
		start(player, world);

	}

	public static void beginLoop(EntityPlayer player, World world) {
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
		winGame.play();

	}
}
