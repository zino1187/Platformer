package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
	public static final int WIDTH=1024;
	public static final int HEIGHT=768;
	
	ObjectManager objectManager;
	
	public GamePanel(ObjectManager objectManager) {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.objectManager = objectManager;
	}

	public void tick() {
		for(int i=0;i<objectManager.objectList.size();i++) {
			GameObject obj=objectManager.objectList.get(i);
			obj.tick();
		}
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		for(int i=0;i<objectManager.objectList.size();i++) {
			GameObject obj=objectManager.objectList.get(i);
			obj.render(g);
		}		
	}
	
	
	public void render() {
		this.repaint();
	}
}
