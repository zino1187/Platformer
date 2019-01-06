package main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MapAnalizer {
	BufferedImage image;
	int rows;
	int cols;
	
	public MapAnalizer() {
		try {
			image = ImageIO.read(this.getClass().getClassLoader().getResource("minimap.gif"));
			cols = image.getWidth();
			rows = image.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int[] getArgbFromPixel(int x, int y) {
		int[] color = { 0, 0, 0, 0 };
		
		int pixel = image.getRGB(x, y);
		
		color[0] = (pixel >> 24) & 0xff;
		color[1] = (pixel >> 16) & 0xff;
		color[2] = (pixel >> 8) & 0xff;
		color[3] = (pixel) & 0xff;		
		return color;
	}
	/*
	 * public static void main(String[] args) { new MapAnalizer().pickColor(); }
	 */
}
