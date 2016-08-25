package com.troy.ludumdare.gamestate;

import com.troy.ludumdare.*;
import com.troy.ludumdare.graphics.*;

public abstract class GameState {
	
	/** Called to update the game state 
	 * @throws Exception **/
	public abstract void update(Game game, int updateCount) throws Exception;
	
	/** Called when this game state becomes the current game state 
	 * @throws Exception **/
	public abstract void onStart(Game game) throws Exception;
	
	/** Called when this state is no longer the current game state **/
	public abstract void onEnd(Game game);
	
	/** Called to render the game state **/
	public abstract void render(Screen screen, Game game);

}
