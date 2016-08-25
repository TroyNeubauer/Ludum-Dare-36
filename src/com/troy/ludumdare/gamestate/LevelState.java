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
		if(updateCount % 10 == 0){
			world.spawnAttacker();
		}
	}

	@Override
	public void render(Screen screen, Game game) {
		world.render(screen);
		world.centerCameraOnPoint(Math.round(player.x), Math.round(player.y));
		
	}

	@Override
	public void onStart(Game game) throws Exception {
		game.loadAssets();
		world = new World(new WorldStats(50, 50, false));
		player = new EntityPlayer(32,32, new WalkingSprite(Assets.player), 50);
		
		for(int i = 0; i < 100; i++){
			world.spawnAttacker();
		}
		world.add(player);
	}

	@Override
	public void onEnd(Game game) {

	}

}
