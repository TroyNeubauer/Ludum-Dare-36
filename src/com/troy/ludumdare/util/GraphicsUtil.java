package com.troy.ludumdare.util;

import java.awt.image.*;
import com.troy.ludumdare.graphics.*;

public class GraphicsUtil {
	
	public static BufferedImage pixelsToImage(Sprite sprite){
		BufferedImage image = new BufferedImage(sprite.width, sprite.height, BufferedImage.TYPE_INT_RGB);
		int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		pixels = sprite.pixels;
		return image;
	}
	

	private GraphicsUtil() {
	}

}
