
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

public class Game implements ActionListener {
	
	//WINDOW SETTINGS
	protected Rectangle windowSize = new Rectangle();
	public int loadedDis;
	
	
	//PANES
	Ui ui;
	JLayeredPane spritesPane = new JLayeredPane();
	JLayeredPane bgPane = new JLayeredPane();
	//ACTORS
	ArrayList<Meteor> meteors = new ArrayList<>();
	ArrayList<Enemy> enemies = new ArrayList<>();
	ArrayList<Laser> lasers = new ArrayList<>();
	ArrayList<BgSprite> bgSprites = new ArrayList<>();
	
	//OTHERS
	public Cursor cursor;
    public Player player;
    public Camera camera = new Camera(this);
    public Vector2 mousePos = new Vector2(0,0);
    public Vector2 mousePosWorld = new Vector2(0,0);
    public Container frame;
    public Timer timer;
    public WaveSpawner waveSpawner;
    public MeteorSpawner meteorSpawner;
    
    static double lerp(double a, double b, double t) {
	    return a + t * (b - a);
	}
    
    
    
    public Game(JFrame bg) {
    	
    	//SOUNDS
    	Sound.createSound("enemyShoot", "sounds/enemy_shoot.wav");
    	Sound.createSound("plrShoot", "sounds/player_shoot.wav");
    	Sound.createSound("explosion", "sounds/explosion.wav");
    	Sound.createSound("wavecleared", "sounds/wavecleared.wav");
    	Sound.createSound("bgMusic", "sounds/music/soundtrack1.wav");
    	Sound.setVolume("bgMusic", 0.6);
    	
    	//GAME
        this.frame = bg.getContentPane();
        windowSize.setBounds(0,0,this.frame.getWidth(),this.frame.getHeight());
        this.timer = new Timer(10, this);
		
        waveSpawner = new WaveSpawner(this);
        meteorSpawner = new MeteorSpawner(this);
        
        ui = new Ui(this);
        ui.setBounds(bg.getBounds());
        spritesPane.setBounds(bg.getBounds());
        bgPane.setBounds(bg.getBounds());
        bg.add(ui);
        bg.add(spritesPane);
        bg.add(bgPane);
        
        Sound.playSound("bgMusic");
        Sound.setLooped("bgMusic");
    }
    
    public static Cursor CreateCursor(ImageIcon img) {
    	Toolkit toolkit = Toolkit.getDefaultToolkit();
    	Image imgRaw = img.getImage();
    	Cursor cursor = toolkit.createCustomCursor(imgRaw, new Point(img.getIconWidth()/2, img.getIconHeight()/2), "gameCursor");
    	return cursor;
    }
    
    public void update() {
    	
    	
    	mousePosWorld.x=mousePos.x+camera.x;
    	mousePosWorld.y=mousePos.y+camera.y;
    	
    	windowSize.setBounds(0,0,this.frame.getWidth(),this.frame.getHeight());
    	bgPane.setBounds(windowSize);
    	
    	//UPDATE PER FRAME
    	camera.update();
    	player.update();
    	waveSpawner.update();
    	meteorSpawner.update();
    	ui.update();
    	
    	for (Meteor obj : meteors) {
			obj.update();
		}
    	
    	for (Enemy obj : enemies) {
    		obj.update();
		}
    	
    	for (Laser obj : lasers) {
			obj.update();
		}
    	
    	
    	for (BgSprite obj : bgSprites) {
			obj.update();
		}
    	
    	
    	//Clean up objects to be removed in a safe way
    	Iterator<Meteor> meteorRemoveList = meteors.iterator();
    	while (meteorRemoveList.hasNext()) {
    		Meteor obj = meteorRemoveList.next();
    	    if (obj.removed) {
    	    	meteorRemoveList.remove();
    	    }
    	}
    	
    	Iterator<Enemy> enemyRemoveList = enemies.iterator();
    	while (enemyRemoveList.hasNext()) {
    	    if (enemyRemoveList.next().removed) {
    	    	enemyRemoveList.remove();
    	    }
    	}
    	
    	Iterator<Laser> laserRemoveList = lasers.iterator();
    	while (laserRemoveList.hasNext()) {
    	    if (laserRemoveList.next().removed) {
    	    	laserRemoveList.remove(); // Safely removes the current sprite from the ArrayList
    	    }
    	}
    	
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
