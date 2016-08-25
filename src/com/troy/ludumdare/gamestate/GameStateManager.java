package com.troy.ludumdare.gamestate;

import com.troy.ludumdare.*;
import com.troy.ludumdare.graphics.*;

/** Manages all of the game states **/
public class GameStateManager {
	
	/** The current state that is the main focus of the window **/
	private GameState currentState;
	private Game game;
	
	public GameStateManager(GameState currentState, Game game) throws Exception {
		currentState.onStart(game);
		this.currentState = currentState;
		this.game = game;

	}
	
	/** Sets the game state to another state 
	 * @throws Exception **/
	public void setState(GameState newState) throws Exception {
		currentState.onEnd(game);
		newState.onStart(game);
		currentState = newState;
	}
	
	/** Called to update the current game state 
	 * @throws Exception **/
	public void update(Game game, int updateCount) throws Exception {
		currentState.update(game, updateCount);
	}
	
	/** Called to render the current game state **/
	public void render(Screen screen, Game game) {
		currentState.render(screen, game);
	}

}
