package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class GameMain extends JFrame implements KeyListener{
	GamePanel gamePanel;
	Thread gameThread;
	boolean gameFlag=true;
	ObjectManager objectManager = new ObjectManager();
	Player player;
	
	public GameMain() {
		gamePanel = new GamePanel(objectManager);
		add(gamePanel);
		this.pack();
		setVisible(true);
		setLocationRelativeTo(null);//화면 가운데로
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		createHero();
		createBlock();
		
		gameThread=new Thread() {
			public void run() {
				while(true) {
					
					try {
						if(gameFlag) {
							//System.out.println("게임 루프 실행중..");
							Thread.sleep(5);
							gamePanel.tick();
							gamePanel.render();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}					
				}	
			}
		};
		gameThread.start();
		this.addKeyListener(this);
	}
	
	/*------------------------------------------------------------
	 오브젝트 생성
	------------------------------------------------------------*/
	public void createHero() {
		player = new Player(objectManager,ObjectType.Player ,100, 0, 40, 100, 0, 0);
		objectManager.addObject(player);
	}
	
	public void createBlock() {
		for(int i=0;i<10;i++){
			Block block = new Block(objectManager,ObjectType.Block ,(50+2)*i, 400, 50, 50, 0, 0);
			objectManager.addObject(block);
		}
	}
	
	/*------------------------------------------------------------
	 키보드 제어
	------------------------------------------------------------*/
	@Override
	public void keyPressed(KeyEvent e) {		
		int key=e.getKeyCode();
		
		System.out.println(key);
		
		switch(key) {
			case KeyEvent.VK_LEFT:player.velX=-1;break; 
			case KeyEvent.VK_RIGHT:player.velX=1;break; 
			case KeyEvent.VK_UP:player.velY=-1;break; 
			case KeyEvent.VK_DOWN:player.velY=1;break; 
			case KeyEvent.VK_SPACE:player.velY=-5f;break; 
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key=e.getKeyCode();
		
		System.out.println(key);
		
		switch(key) {
			case KeyEvent.VK_LEFT:player.velX=0;break; 
			case KeyEvent.VK_RIGHT:player.velX=0;break; 
			case KeyEvent.VK_UP:player.velY=0;break; 
			case KeyEvent.VK_DOWN:player.velY=0;break; 
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	
	/*------------------------------------------------------------
	 게임 제어
	------------------------------------------------------------*/
	public void startGame() {
		
		gameFlag=true;
	}
	public void pauseGame() {
		gameFlag=false;
	}
	
	public static void main(String[] args) {
		new GameMain();
	}
}
