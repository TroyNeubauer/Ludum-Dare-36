package com.troy.ludumdare.ui;

import java.awt.*;
import com.troy.ludumdare.Item.*;
import com.troy.ludumdare.gamestate.*;
import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.input.*;
import com.troy.ludumdare.sound.*;

public class UIInventory extends UIComponent {

	public static Item selectedItem;
	Sound swichSound = new Sound("ItemSwitch");

	private Item[] items;
	public static final int IWidth = 4, IHeight = 10;
	private boolean hovering = false;

	public UIInventory(int x, int y, int width, int height, UIPanel parent) {
		super(x, y, width, height, parent);
		items = new Item[IWidth * IHeight];
	}

	public boolean addItem(Item item) {
		for (int i = 0; i < items.length; i++) {
			if (items[i] == null && item != null) {
				if (item.equals(items[i])) return false;

				items[i] = item;
				System.out.println("slot " + i + " is open! adding item " + item.name);
				if (selectedItem == null) {
					selectedItem = item;
				}
				return true;
			}
		}
		return false;
	}
	
	public boolean containsItem(Item item){
		if(item == null) return true;
		for(int i = 0; i < items.length; i++){
			if(items[i] == null) continue;
			if(item.name.equals(items[i].name)){
				System.out.println("inventory contains item " + item.name + " allready!");
				return true;
			}
		}
		
		return false;
	}

	@Override
	public void render(Screen screen) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				screen.pixels[(x + this.x) + (y + this.y) * screen.width] = 0x606060;
			}
		}
		if (hovering) {
			int xp = Input.mouseX;
			int yp = Input.mouseY;
			xp -= this.x;
			int indexX = xp / 16;
			int indexY = yp / 16;
			if (indexX + indexY * IWidth >= 0) {
				if (items[indexX + indexY * IWidth] != null) {
					for (int y = 0; y < 16; y++) {
						for (int x = 0; x < 16; x++) {
							screen.drawPixel((x + this.x + indexX * 16) + (y + this.y + indexY * 16) * screen.width, 0x909090);
						}
					}
				}

				Item item = items[indexX + indexY * IWidth];
				if (item != null) {
					TextMaster.addText(new Text("" + item.name, Input.mouseX - 20, Input.mouseY + 7, 17, Font.BOLD, 0));
				}
			}
		}
		for (int y = 0; y < IHeight; y++) {
			for (int x = 0; x < IWidth; x++) {
				if (items[x + y * IWidth] != null) {
					screen.drawSprite(items[x + y * IWidth].stats.sprite, x * 16 + this.x, y * 16 + this.y, LevelState.world, false);
				}
			}
		}
	}

	@Override
	public void onClick() {
		int x = Input.mouseX;
		int y = Input.mouseY;
		x -= this.x;
		int indexX = x / 20;
		int indexY = y / 20;
		if (items[indexX + indexY * IWidth] != null && selectedItem != items[indexX + indexY * IWidth]) {
			selectedItem = items[indexX + indexY * IWidth];
			swichSound.play();
		}

	}

	public Item getSelectedItem() {
		if(selectedItem == null){
			selectedItem = getAdvancedWeapon();
		}
		return selectedItem;
	}

	public void setSelectedItem(Item selectedItem) {
		UIInventory.selectedItem = selectedItem;
	}

	@Override
	public void onHover() {
		hovering = true;
	}

	@Override
	public void onOffHover() {
		hovering = false;
	}

	public Item[] getItems() {
		return items;
	}

	public Item getAdvancedWeapon() {
		for(int i = 0; i < items.length; i++){
			if(items[i] == null){
				System.out.println("best weapon is " + items[i - 1].name);
				return items[i - 1];
			}
		}
		return null;
	}
}
