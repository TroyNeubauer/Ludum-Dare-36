package com.troy.ludumdare.gamestate;

import java.util.*;
import com.troy.ludumdare.*;
import com.troy.ludumdare.entity.*;
import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.util.*;
import com.troy.ludumdare.world.*;

/** Represents the state of the game when a player is in the world **/
public class LevelState extends GameState {

	private static Random random = new Random();
	public static World world;
	public static EntityPlayer player;

	@Override
	public void update(Game game, int updateCount) throws Exception {
		world.update(updateCount);
	}

	@Override
	public void render(Screen screen, Game game) {
		world.render(screen);
		world.centerCameraOnPoint(player.x, player.y);
	}

	@Override
	public void onStart(Game game) throws Exception {
		game.loadAssets();
		world = new World(new WorldStats(150, 100, false));
		player = new EntityPlayer(world.playerX, world.playerY, new WalkingSprite(Assets.player), 50);
		world.add(player);
	}
	
	public void startMatch(){
		LevelState.player.x = 30 * 16;
		LevelState.player.y = 30 * 16;
		
	}

	@Override
	public void onEnd(Game game) {

	}

	public static void save() {
	}

}
