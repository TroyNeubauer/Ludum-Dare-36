package com.troy.ludumdare.ui;

import com.troy.troyberry.math.*;

public class Rectangle {
	
	public static boolean pointIntersectsRectangle(int pointX, int pointY, int recX, int recY, int width, int height){
		return Maths.intersect(recX, recX + width, pointX) && Maths.intersect(recY, recY + height, pointY);
	}
	
	
	
	
	
	private Rectangle() {
	}
}
