package com.troy.ludumdare.ui;

import java.awt.*;
import com.troy.ludumdare.*;
import com.troy.ludumdare.Item.*;
import com.troy.ludumdare.entity.*;
import com.troy.ludumdare.gamestate.*;
import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.input.*;
import com.troy.ludumdare.world.*;

public class UI {
	
	private static Text yearText = new Text("Year", 5, 5, 15, Font.BOLD, 0x030303),
		money = new Text("Money", 30, 5, 15, Font.BOLD, 0x0), healthText = new Text("Health", 100, 5, 15, Font.BOLD, 0);
	
	private static UIPanel inventoryPanel, battleResultsPanel;
	public static UIInventory inventory;
	public static boolean inventoryOpen = false, battleResultsOpen = false;
	
	public static void render(Screen screen, World world){
		TextMaster.addText(yearText);
		TextMaster.addText(money);
		healthText.text = "Health " + LevelState.player.getHealth();
		TextMaster.addText(healthText);
		if(inventoryOpen)inventoryPanel.render(screen);
		screen.drawSprite(inventory.getSelectedItem().stats.sprite, screen.width - ((inventoryOpen) ?  60 : 0) - 16, 0, world, false);
		TextMaster.addText(new Text(inventory.getSelectedItem().name, screen.width - ((inventoryOpen) ?  60 : 0) - 65, 7, 18, Font.BOLD, 0));
		if(battleResultsOpen) battleResultsPanel.render(screen);
		
	}
	
	public static void update(int updateCount, World world, EntityPlayer player){
		yearText.text = world.getYear();
		money.text = player.getMoney();
		
		if(inventoryOpen)inventoryPanel.update(updateCount);
		if(Controls.INVENTORY.hasBeenPressed()) inventoryOpen = !inventoryOpen;
		if(battleResultsOpen) battleResultsPanel.update(updateCount);
	}
	
	public static void init(){
		
		battleResultsPanel = new UIPanel(0, 0, Game.screen.width, Game.screen.height, 0xAAAAAA);
		
		inventoryPanel = new UIPanel(0, 0, Game.screen.width, Game.screen.height, 0x757575);
		inventory = new UIInventory(Game.screen.width - 65, 0, 65, Game.screen.height, inventoryPanel);
		inventoryPanel.add(inventory);
		
		inventory.addItem(new Item(1, "Bow I", Item.BASIC_BOW.stats));
	}
	
}
