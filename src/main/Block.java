package main;

import java.awt.Color;
import java.awt.Graphics;

public class Block extends GameObject{
	
	public Block(ObjectManager objectManager,ObjectType type,float x, float y, int width, int height, int velX, int velY) {
		super(objectManager,type,x, y, width, height, velX, velY);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect((int)x, (int)y, width, height);
	}
	
}
