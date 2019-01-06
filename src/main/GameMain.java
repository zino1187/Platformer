package main;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.JFrame;

public class GameMain extends JFrame implements KeyListener{
	GamePanel gamePanel;
	Thread gameThread;
	boolean gameFlag=true;
	ObjectManager objectManager = new ObjectManager();
	MapAnalizer mapAnalizer;
	ImageManager imageManager=new ImageManager();
	AudioInputStream audio;
	Player player;
	
	public GameMain() {
		gamePanel = new GamePanel(objectManager);
		add(gamePanel);
		this.pack();
		setVisible(true);
		setLocationRelativeTo(null);//화면 가운데로
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		createAudio();
		createHero();
		createMap();
		
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
	public void createAudio() {
		//audio=AudioSystem.getAudioInputStream(url);
	}
	public void createHero() {
		Image img=imageManager.getImage("Idle__000.png");
		player = new Player(objectManager,ObjectType.Player ,150, -100, 55, 100, 0, 0,img);
		objectManager.addObject(player);
	}
	
	public void createMap() {
		Image img=imageManager.getImage("Crate.png");
		
		mapAnalizer = new MapAnalizer();
		
		for(int a=0;a<mapAnalizer.rows;a++) {
			for(int i=0;i<mapAnalizer.cols;i++) {
				int[] color=mapAnalizer.getArgbFromPixel(i,a);
				
				System.out.println("argb: " + color[0] + ", " + color[1] + ", " + color[2] + ", " + color[3]);
				
				if(color[1]==255 && color[2]==0 && color[3]==0){ // 255 0  0
					
					Block block = new Block(objectManager,ObjectType.Block , (32+2)*i, (32+2)*a, 32, 32, 0, 0,img);
					objectManager.addObject(block);
				}
			}
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
			case KeyEvent.VK_LEFT:player.velX=-2;break; 
			case KeyEvent.VK_RIGHT:player.velX=2;break;
			case KeyEvent.VK_SPACE:
				if(player.jumping==false) {
					player.velY=-5f;
				}
				player.jumping=true;
				break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key=e.getKeyCode();
		
		System.out.println(key);
		
		switch(key) {
			case KeyEvent.VK_LEFT:player.velX=0;break; 
			case KeyEvent.VK_RIGHT:player.velX=0;break; 
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
