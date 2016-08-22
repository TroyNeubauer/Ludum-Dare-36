package com.troy.dungeonlooter.gamestate;

import com.troy.dungeonlooter.entity.*;
import com.troy.dungeonlooter.graphics.*;
import com.troy.dungeonlooter.main.*;
import com.troy.dungeonlooter.world.*;

public class DungeonLevelState extends GameState {

	@Override
	public void update(Game game) {
		Game.camera.update();
		game.world.update();
	}

	@Override
	public void render(Screen screen, Game game) {
		game.world.render(screen);
	}

	@Override
	public void onStart(Game game) {
		Game.camera = new Camera(game, 0, 0, false);
		game.world = new World(new WorldStats(50, 50, false));
		
	}

	@Override
	public void onEnd(Game game) {
		// TODO Auto-generated method stub
		
	}

}
