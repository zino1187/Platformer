package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{
	public Player(ObjectManager objectManager, ObjectType type,float x, float y, int width, int height, float velX, float velY) {
		super(objectManager,type,x, y, width, height, velX, velY);
		rect = new Rectangle((int)x, (int)y, width, height);
	}
	
	@Override
	public void tick() {
		//System.out.println("Player tick()");
		this.x+=this.velX;
		this.velY+=this.g; //누적연산
		this.y+=this.velY;//누적연산 ( 여기서도 누적연산을 해야 가속도가 붙어서 자연스럽다)
		
		System.out.println(this.y);
		
		
		//중력 적용
		
		//충돌 체크
		int hitCount=0;
		Block hitBlock=null;
		for(int i=0; i<objectManager.objectList.size();i++) {
			GameObject gameObject=objectManager.objectList.get(i);
			if(gameObject.type == ObjectType.Block && rect.intersects(gameObject.rect)) {
				hitCount++;
				hitBlock=(Block)gameObject;
			}
		}
		
		//먼저 그래픽 처리를 해놓고 추후에 계산해야 진동이 일어나지 않는다
		rect.setBounds((int)x,(int)y, width, height);
		
		if(hitCount>0) {
			this.g=0;
			this.y=hitBlock.y-this.height;
		}else {
			this.g=0.05f;			
		}
	}

	@Override
	public void render(Graphics g) {
		//System.out.println("Player render()");
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, width, height);
	}
	
}
