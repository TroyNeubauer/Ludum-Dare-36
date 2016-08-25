package com.troy.ludumdare.gamestate;

import com.troy.ludumdare.*;

import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.input.*;

/** Represents the game when the title screen needs to be displayed **/
public class TitleScreenState extends GameState {

	@Override
	public void update(Game game, int updateCount) throws Exception {
		if (Controls.PLAY.hasBeenPressed()) {
			game.gameStateManager.setState(game.levelState);
		}

	}

	@Override
	public void render(Screen screen, Game game) {

		game.loadAssets();
	}

	@Override
	public void onStart(Game game) {

	}

	@Override
	public void onEnd(Game game) {

	}
}
