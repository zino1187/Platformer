package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
	public static final int WIDTH=1440;
	public static final int HEIGHT=768;
	
	ObjectManager objectManager;
	ImageManager imageManager=new ImageManager();
	Image img;
	
	public GamePanel(ObjectManager objectManager) {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.objectManager = objectManager;
		img=imageManager.getImage("BG.png");
	}

	public void tick() {
		for(int i=0;i<objectManager.objectList.size();i++) {
			GameObject obj=objectManager.objectList.get(i);
			obj.tick();
		}
	}
	
	public void paint(Graphics g) {
		//g.setColor(Color.BLACK);
		//g.fillRect(0, 0, WIDTH, HEIGHT);
		g.drawImage(img, 0, 0,1440,768,this);
		
		for(int i=0;i<objectManager.objectList.size();i++) {
			GameObject obj=objectManager.objectList.get(i);
			obj.render(g);
		}		
	}
	
	
	public void render() {
		this.repaint();
	}
}
