package com.troy.ludumdare.ui;

import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.input.*;

public abstract class UIComponent {

	protected final int x, y, width, height;
	protected boolean hovering, hoveringLast;
	protected UIPanel parent;

	public UIComponent(int x, int y, int width, int height, UIPanel parent) {
		this.x = x + parent.getX();
		this.y = y + parent.getY();
		this.width = width;
		this.height = height;
		this.parent = parent;
	}

	public abstract void onClick();

	public abstract void onHover();

	public abstract void onOffHover();

	public abstract void render(Screen screen);

	public void update(int updateCount, int mouseX, int mouseY) {
		if (Rectangle.pointIntersectsRectangle(Input.mouseX, Input.mouseY, x, y, width, height)) {
			hovering = true;
			if (!hoveringLast) {
				onHover();
			}
		} else {
			hovering = false;
			if (hoveringLast) {
				onOffHover();
			}
		}
		if (mouseX >= 0 && mouseY >= 0) if (Rectangle.pointIntersectsRectangle(mouseX, mouseY, x, y, width, height)){
			this.onClick();
		}

		hoveringLast = hovering;
	}

}
