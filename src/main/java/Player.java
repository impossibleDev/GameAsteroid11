
import javax.swing.ImageIcon;

public class Player extends Sprite{
	
	//VARIABLES
	public double health = 250;
	public int damage = 10;
	public boolean shooting;
	protected HealthBar healthBar;
	
	private final int cooldown = 7;
    private int currenttick = 0;
	
	ImageIcon imgIdle = StaticAssets.loadImage("sprites/ufo_idle.gif");
	ImageIcon imgFire = StaticAssets.loadImage("sprites/ufo_fire.gif");
	ImageIcon imgHit = StaticAssets.loadImage("sprites/ufo_hit.gif");
	
	//DIRECTIONS
	int up, down, left, right;
	double speed = 4;
	public Player(int startX, int startY, Game game){

		this.sizeX=36*4;
		this.sizeY= 18*4;
		
		this.game = game;
		this.setIcon(imgIdle);
		this.setPos(startX,startY);
		game.spritesPane.add(this);
		
		healthBar = new HealthBar(this, health);
		
	}
	
	public void update() {
		this.vel.x=(left+right)*speed;
		this.vel.y=(down+up)*speed;
		this.updatePos();
		
		//SHOOTING
		if (shooting) {
			currenttick++;
			if (currenttick>cooldown) {
				game.lasers.add( new Laser(pos.x, pos.y, Vector2.angleLookAt(pos, game.mousePosWorld) + 1.5708, damage, true, game) );
				Sound.playSound("plrShoot");
				currenttick=0;
			}
		}
	}
	
	public void damage(int amount) {
		health-=amount;
		healthBar.setHealth(health);
		
		if (health<=0) {
			game.ui.nextWaveTitle.setText("GAME OVER");
			game.ui.nextWaveTitle.setVisible(true);
			game.camera.camShake.shake(13, 400, 6);
			game.timer.stop();
		}
	}
	
}
