package com.troy.ludumdare.gamestate;

import com.troy.ludumdare.*;
import com.troy.ludumdare.entity.*;
import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.util.*;
import com.troy.ludumdare.world.*;

/** Represents the state of the game when a player is in the world **/
public class LevelState extends GameState {
	
	private World world;
	private EntityPlayer player;

	@Override
	public void update(Game game, int updateCount) {
		world.update(updateCount);
		player.update(world);
		Assets.player.update(updateCount);
	}

	@Override
	public void render(Screen screen, Game game) {
		world.render(screen);
		player.render(screen, world);
		world.centerOnPoint(Math.round(player.x), Math.round(player.y));
		
	}

	@Override
	public void onStart(Game game) {
		game.loadAssets();
		world = new World(new WorldStats(50, 50, false));
		player = new EntityPlayer(0, 0, Assets.player, 50);
	}

	@Override
	public void onEnd(Game game) {

	}

}
