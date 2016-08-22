package com.troy.dungeonlooter.gamestate;

import com.troy.dungeonlooter.graphics.*;
import com.troy.dungeonlooter.main.*;

public abstract class GameState {

	public abstract void update(Game game);
	
	public abstract void onStart(Game game);
	
	public abstract void onEnd(Game game);

	public abstract void render(Screen screen, Game game);

}
