package main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	ObjectManager objectManager;
	ObjectType type;
	Rectangle rect;
	float x;
	float y;
	int width;
	int height;
	float velX;
	float velY;
	float g=0.05f;
	
	
	public GameObject(ObjectManager objectManager, ObjectType type, float x, float y, int width, int height, float velX, float velY){
		this.objectManager=objectManager;
		this.type=type;
		this.rect = new Rectangle((int)x, (int)y, width,height);
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.velX=velX;
		this.velY=velY;
	}
	public abstract void tick();
	public abstract void render(Graphics g);
}
