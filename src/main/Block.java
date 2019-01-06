package main;

import java.awt.Graphics;
import java.awt.Image;

public class Block extends GameObject{
	
	public Block(ObjectManager objectManager,ObjectType type,float x, float y, int width, int height, int velX, int velY,Image img) {
		super(objectManager,type,x, y, width, height, velX, velY,img);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		setRectBounds((int)x, (int)y, width, height);
		
	}

	@Override
	public void render(Graphics g) {
		//showOutline(g);
		//g.setColor(Color.lightGray);
		//g.fillRect((int)x, (int)y, width, height);
		g.drawImage(img, (int)x, (int)y, width, height, null);
	}
	
}
