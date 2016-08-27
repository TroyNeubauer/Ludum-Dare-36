package com.troy.ludumdare.gamestate;

import java.awt.*;
import com.troy.ludumdare.*;
import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.ui.*;

/** Represents the game when the title screen needs to be displayed **/
public class TitleScreenState extends GameState {
	Text play = new Text("Play", 17, 4, 15, Font.PLAIN, 0x0);
	Text info = new Text("Info", 17, 2, 15, Font.PLAIN, 0x0);
	Text clickHint = new Text("Because of a bug, if you click within the window, the game will crash. To \"click\" hover over an item then hiy enter.", 30, 150, 10, Font.PLAIN, 0xFFFFFF);
	private UIPanel panel;
	private UIButton playButton, infoButton;

	@Override
	public void update(Game game, int updateCount) throws Exception {
		panel.update(updateCount);

	}

	@Override
	public void render(Screen screen, Game game) {
		game.loadAssets();
		panel.render(screen);
		TextMaster.addText(clickHint);
	}

	@Override
	public void onStart(Game game) {
		panel = new UIPanel(0, 0, Game.screen.width, Game.screen.height, 0x707070);
		playButton = new UIButton(125, 50, 50, 10, panel, 0xCCCCCC, play);
		infoButton = new UIButton(125, 75, 50, 10, panel, 0xCCCCCC, info);
		panel.add(playButton);
		panel.add(infoButton);
	}

	@Override
	public void onEnd(Game game) {

	}
}
