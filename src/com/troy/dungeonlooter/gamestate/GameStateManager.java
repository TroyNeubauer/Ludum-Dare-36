package com.troy.dungeonlooter.gamestate;

import com.troy.dungeonlooter.graphics.*;
import com.troy.dungeonlooter.main.*;

public class GameStateManager {

	private GameState currentState;
	private Game game;

	public GameStateManager(GameState currentState, Game game) {
		currentState.onStart(game);
		this.currentState = currentState;
		this.game = game;

	}

	public void setState(GameState newState) {
		currentState.onEnd(game);
		newState.onStart(game);
		currentState = newState;
	}

	public void update(Game game) {
		currentState.update(game);
	}

	public void render(Screen screen, Game game) {
		currentState.render(screen, game);
	}

}
