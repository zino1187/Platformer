package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
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
	Image img;
	
	
	public GameObject(ObjectManager objectManager, ObjectType type, float x, float y, int width, int height, float velX, float velY,Image img){
		this.objectManager=objectManager;
		this.type=type;
		rect = new Rectangle();
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.velX=velX;
		this.velY=velY;
		this.img=img;
	}
	
	public void setRectBounds(int x, int y, int width, int height) {
		this.rect.setBounds(x, y, width , height);//위
	}
	
	public void showOutline(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect(rect.x, rect.y, rect.width, rect.height);			
		
	}	
	public abstract void tick();
	public abstract void render(Graphics g);
	
}
