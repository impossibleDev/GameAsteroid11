//This class...

//import java.awt.Image;

//import objects.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class AsteroidGameFrame extends JFrame implements ActionListener, KeyListener, MouseMotionListener, MouseListener{
	
	
	
	ImageIcon cursor = StaticAssets.loadImage("sprites/cursor.png");
	
	ImageIcon bg1 = StaticAssets.loadImage("sprites/bg1.png");
	ImageIcon bg2 = StaticAssets.loadImage("sprites/bg2.png");
	ImageIcon bg3 = StaticAssets.loadImage("sprites/bg3.png");
	JLabel bg = new JLabel(StaticAssets.loadImage("sprites/bgOld.jpg"));
	
	Game game;
	
	public AsteroidGameFrame() {
		
		setSize(1200,800);
		setTitle("Asteroid Rush");
		setUndecorated(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(null);
		setVisible(true);

		
		
		game = new Game(this);
		game.loadedDis = 1200;
		
		//UI
        game.ui.minimap = new Minimap(game);
        game.ui.cardFrame = new CardUi(game);
		
		game.frame.setCursor(Game.CreateCursor(cursor));
		//SPRITE OBJECTS
		
		game.player = new Player(0, 0, game);

		new BgSprite(bg2, 0.25, game);
		new BgSprite(bg3, 0.2, game).setSize(1600,1200);
		new BgSprite(bg1, 0.1, game);
		
		//LISTENERS
//		bg.addMouseMotionListener(this);
		this.addKeyListener(this);
		game.frame.addMouseMotionListener(this);
		game.frame.addMouseListener(this);
		game.timer.addActionListener(this);
		//Start Game
		
		game.timer.start();
		
	}
	
//	public void mouseDragged(MouseEvent a) {
//		
//	}

	@Override
	public void mouseMoved(MouseEvent mouse) {
		game.mousePos.x=mouse.getX();
		game.mousePos.y=mouse.getY();
		
	}
	
	@Override
	public void mouseDragged(MouseEvent mouse) {
		game.mousePos.x=mouse.getX();
		game.mousePos.y=mouse.getY();
	}
	
	@Override 
	public void actionPerformed(ActionEvent e) {
		game.update();
		if (game.waveSpawner.state==-1) {
			//spawn wave
			game.waveSpawner.state = 0; //loading
//			game.waveSpawner.spawnWithGrid(0,20,1,500);
			game.waveSpawner.spawnWithGrid(200,0,2,1500);
			game.waveSpawner.state = 1; //battling
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();
		
		if (key==KeyEvent.VK_W) game.player.up=-1;
		if (key==KeyEvent.VK_S) game.player.down=1;
		if (key==KeyEvent.VK_A) game.player.left=-1;
		if (key==KeyEvent.VK_D) game.player.right=1;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		if (key==KeyEvent.VK_W) game.player.up=0;
		if (key==KeyEvent.VK_S) game.player.down=0;
		if (key==KeyEvent.VK_A) game.player.left=0;
		if (key==KeyEvent.VK_D) game.player.right=0;
	}

//	@Override
//	public void mouseDragged(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void mousePressed(MouseEvent e) {
		game.player.shooting=true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		game.player.shooting=false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	

//	@Override
//	public void mouseEntered(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseExited(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
	
}
