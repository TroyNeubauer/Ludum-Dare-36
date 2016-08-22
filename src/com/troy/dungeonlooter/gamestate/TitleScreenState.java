package com.troy.dungeonlooter.gamestate;

import com.troy.dungeonlooter.cilent.*;
import com.troy.dungeonlooter.graphics.*;
import com.troy.dungeonlooter.main.*;


public class TitleScreenState extends GameState {

	@Override
	public void update(Game game) {
		game.loadAssets();
		if (Controls.PLAY.hasBeenPressed()) {
			game.gameStateManager.setState(game.dungeonLevelState);
		}

	}

	@Override
	public void render(Screen screen, Game game) {
		for(int i = 0; i < screen.pixels.length; i++){
			screen.pixels[i] = 0xFF00FF;
			if((i & 1) == 0 || ((i / screen.width) & 1)  == 0){
				screen.pixels[i] = 0;
			}
		}
	}

	@Override
	public void onStart(Game game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnd(Game game) {
		// TODO Auto-generated method stub
		
	}
}
