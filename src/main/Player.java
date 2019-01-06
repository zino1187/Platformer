package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Player extends GameObject{
	Rectangle[] rect=new Rectangle[4];
	boolean jumping=false;
	Camera camera;
	
	public Player(ObjectManager objectManager, ObjectType type,float x, float y, int width, int height, float velX, float velY,Image img) {
		super(objectManager,type,x, y, width, height, velX, velY,img);
		
		for(int i=0;i<rect.length;i++) {
			rect[i] = new Rectangle();
		}
		camera = new Camera();
	}
	public void setRectBounds(int x, int y, int width, int height) {
		this.rect[0].setBounds((int)x+2, (int)y-2, width-4,2);//위
		this.rect[1].setBounds((int)x+2, (int)y+height, width-4,2);//아래
		this.rect[2].setBounds((int)x-2, (int)y+2, 2,height-8);//좌
		this.rect[3].setBounds((int)x+width, (int)y+2, 2,height-8);//우
	}
	public void showOutline(Graphics g) {
		g.setColor(Color.BLUE);
		for(int i=0;i<rect.length;i++) {
			g.drawRect(rect[i].x, rect[i].y, rect[i].width, rect[i].height);			
		}
	}	
	
	@Override
	public void tick() {
		//System.out.println("Player tick()");
		this.x+=this.velX;
		this.velY+=this.g; //누적연산
		this.y+=this.velY;//누적연산 ( 여기서도 누적연산을 해야 가속도가 붙어서 자연스럽다)
		
		if(this.velY >4) {
			this.velY=4;
		}
		setRectBounds((int)x, (int)y, width, height);
		
		
		//System.out.println(this.velY);
		
		//중력 적용
		
		//바닥 충돌 체크
		int topCount=0;
		int bottomCount=0;
		int leftCount=0;
		int rightCount=0;
		
		Block topBlock=null;
		Block bottomBlock=null;
		Block leftBlock=null;
		Block rightBlock=null;
		
		for(int i=0; i<objectManager.objectList.size();i++) {
			GameObject gameObject=objectManager.objectList.get(i);
			
			if(gameObject.type == ObjectType.Block) {
				
				if(rect[1].intersects(gameObject.x,gameObject.y, gameObject.width, gameObject.height)){ //아래가 닿으면...
					//System.out.println("발 닿았어");
					bottomCount++;
					bottomBlock=(Block)gameObject;
					
				}if(rect[0].intersects(gameObject.x,gameObject.y, gameObject.width, gameObject.height)){ //아래가 닿으면...
					//System.out.println("머리 닿았어");
					topCount++;
					topBlock=(Block)gameObject;
				}if(rect[3].intersects(gameObject.x,gameObject.y, gameObject.width, gameObject.height)){ //아래가 닿으면...
					System.out.println("우측 닿았어");
					rightCount++;
					rightBlock=(Block)gameObject;
				}if(rect[2].intersects(gameObject.x,gameObject.y, gameObject.width, gameObject.height)){ //아래가 닿으면...
					System.out.println("좌측 닿았어");
					leftCount++;
					leftBlock=(Block)gameObject;
				}
				
			}
			
		}
		
		//먼저 그래픽 처리를 해놓고 추후에 계산해야 진동이 일어나지 않는다
		//rect.setBounds((int)x,(int)y, width, height);
		
		if(bottomCount>0) {
			//this.g=0;
			this.y=bottomBlock.y-this.height-2;
			setRectBounds((int)x, (int)y, width, height);
			jumping=false;
		}else {
			//this.g=0.05f;	
			jumping=true;
		}
		
		if(topCount>0) {
			this.y=topBlock.y+topBlock.height+2;
			this.velY=0; //음수에서 양수로 전환되는 시점을 억지로 당긴다!!!
			setRectBounds((int)x, (int)y, width, height);
		}
		if(rightCount>0) {
			this.x=rightBlock.x-this.width-2;
			setRectBounds((int)x, (int)y, width, height);
		}
		if(leftCount>0) {
			this.x=leftBlock.x+leftBlock.width+2;
			setRectBounds((int)x, (int)y, width, height);
		}
		
		
	}
	
	@Override
	public void render(Graphics g) {
		camera.view(g, -x +GamePanel.WIDTH/2, -y+GamePanel.HEIGHT/2);
		
		//showOutline(g);
		
		//System.out.println("Player render()");
		//g.setColor(Color.WHITE);
		//g.drawRect((int)x, (int)y, width, height);
		g.drawImage(img, (int)x, (int)y, width, height, null);
		
	}
	
}
