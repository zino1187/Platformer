package main;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Camera {
	
	public void view(Graphics g ,float x, float y) {
		Graphics2D g2=(Graphics2D)g;
		g2.translate(x, y);
	}
}
