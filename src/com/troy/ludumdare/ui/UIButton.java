package com.troy.ludumdare.ui;

import com.troy.ludumdare.*;
import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.input.*;


public class UIButton extends UIComponent {
	
	public int color;
	private Text text;

	public UIButton(int x, int y, int width, int height, UIPanel parent, int color, Text text) {
		super(x, y, width, height, parent);
		this.color = color;
		this.text = text;
		this.text.x += this.x + parent.getX();
		this.text.y += this.y + parent.getY();
	}

	@Override
	public void onClick() {
		System.out.println(text.text + " was pressed");
		if(text.text.equals("Play")){
			if (Input.isKeyDown(Input.KEY_ENTER)) {
				try {
					Game.game.gameStateManager.setState(Game.game.levelState);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		else if(text.text.equals("Info")){
			InfoWindow.initialize();
		}

	}

	@Override
	public void onHover() {
		this.color = 0xF5F5F5;
	}

	@Override
	public void onOffHover() {
		this.color = 0xCCCCCC;
	}

	@Override
	public void render(Screen screen) {
		screen.drawRectangle(x, y, width, height, color);
		TextMaster.addText(text);
	}
}
