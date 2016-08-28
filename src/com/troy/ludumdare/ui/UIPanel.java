package com.troy.ludumdare.ui;

import java.util.*;
import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.input.*;

public class UIPanel {

	private final int x, y, width, height;
	private int color;
	private List<UIComponent> components;
	private boolean enabled, visable;
	private boolean enterLast, enter;

	public UIPanel(int x, int y, int width, int height, int color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.components = new ArrayList<UIComponent>();
		this.enabled = true;
		this.visable = true;
	}

	public void add(UIComponent component) {
		components.add(component);
	}

	public void remove(UIComponent component) {
		components.add(component);
	}

	public void render(Screen screen) {
		if(!visable) return;
		for(UIComponent comp : components){
			comp.render(screen);
		}
	}

	public void update(int updateCount) {
		if(!enabled) return;
		int mouseX = -1, mouseY = -1;
		if(Controls.NEXT.hasBeenPressed() || Controls.NEXT2.hasBeenPressed()){
			enter = true;
			if(!enterLast){
				mouseX = Input.mouseX;
				mouseY = Input.mouseY;
			}
		}else{
			enter = false;
		}
		
		enterLast = enter;
		
		for(UIComponent comp : components){
			comp.update(updateCount, mouseX, mouseY);
		}
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isVisable() {
		return visable;
	}

}
