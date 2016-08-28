package com.troy.ludumdare.graphics;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import com.troy.ludumdare.*;

public class TextMaster {

	private static final String FONT_NAME = "Gabriola";

	private static List<Text> texts = new ArrayList<Text>();
	private static List<Font> fonts = new ArrayList<Font>();

	public static void render(Graphics g, Screen screen) {
		for (Text text : texts) {
			int size = (text.size * (Game.game.frame.getWidth() / screen.width)) / 2;
			int style = text.style;
			String letters = text.text;
			int x = text.x * (Game.game.frame.getWidth() / screen.width);
			int y = text.y * (Game.game.frame.getHeight() / screen.height);
			g.setFont(insureFont(size, style));
			g.setColor(new Color(text.color));
			g.drawString(text.text, x, y);
		}
	}

	private static Font insureFont(int size, int style) {
		for (Font font : fonts) {
			if (font.getSize() == size && font.getStyle() == style) {
				return font;
			}
		}
		try {
		     GraphicsEnvironment ge = 
		         GraphicsEnvironment.getLocalGraphicsEnvironment();
		     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, Class.class.getResourceAsStream("/font.ttf")));
		} catch (IOException|FontFormatException e) {
		     e.printStackTrace();
		}
		Font font = new Font(FONT_NAME, style, size);
		fonts.add(font);
		return font;
	}

	public static void addText(Text text) {
		if(!texts.contains(text)){
			texts.add(text);
		}
	}
	
	public static void clear(){
		texts.clear();
	}
}
