package com.troy.ludumdare.gamestate;

import java.io.*;
import java.util.*;
import com.troy.ludumdare.*;
import com.troy.ludumdare.entity.*;
import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.sound.*;
import com.troy.ludumdare.util.*;
import com.troy.ludumdare.world.*;

/** Represents the state of the game when a player is in the world **/
public class LevelState extends GameState {

	private static Random random = new Random();
	public static World world;
	public static EntityPlayer player;
	public static final File SAVE_DIRECTORY = new File("./world.dat");
	Sound sound = new Sound("coin");

	@Override
	public void update(Game game, int updateCount) throws Exception {
		world.update(updateCount);
		if (updateCount % 20 == 0) {
			world.spawnAttacker();
		}
		if (updateCount % 120 == 0) {
			sound.play();
		}
	}

	@Override
	public void render(Screen screen, Game game) {
		world.render(screen);
		world.centerCameraOnPoint(player.x, player.y);
	}

	@Override
	public void onStart(Game game) throws Exception {
		game.loadAssets();
		world = new World();
		for (int i = 0; i < 100; i++) {
			world.spawnAttacker();
		}
		player = new EntityPlayer(world.playerX, world.playerY, new WalkingSprite(Assets.player), 50);
		world.add(player);
	}

	@Override
	public void onEnd(Game game) {

	}

	public static void save() {
		if (SAVE_DIRECTORY.mkdirs()) {
			System.out.println("Created save direcrtory since it didnt exist. At: " + SAVE_DIRECTORY.getPath());
		}
		if (world != null) {
			world.save();
		}
	}

}
