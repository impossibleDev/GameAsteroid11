
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Enemy extends Sprite{
	
	//VARIABLES
	public double health = 100;
	public boolean invincible = false;
	private final int cooldown = 30;
    private int currenttick = 0;
	
	private final double idealMaxDistance = 300;
    private final double idealMinDistance  = 200;
    private final double rotationThreshold = 0.15;
	
	protected HealthBar healthBar;
	public JLabel minimapTracker;
	//resources
	ImageIcon idleImg = StaticAssets.loadImage("sprites/enemy.gif");
	
	static double lerp(double a, double b, double t) {
	    return a + t * (b - a);
	}
	
	
	public Enemy(int startX, int startY, Game game){
		this.game = game;
		this.setIcon(idleImg);
		this.setPos(startX,startY);
		this.minimapTracker = game.ui.minimap.newTracker(this);
		game.spritesPane.add(this);
		
		healthBar = new HealthBar(this, health);
	}
	
	
	public void update() {
		
		if (Vector2.disSq(this.pos,game.player.pos)<game.loadedDis*game.loadedDis) { // won't move offscreen
			
			//SHOOTING
			currenttick++;
			
			if (currenttick>cooldown) {
				game.lasers.add( new Laser(pos.x, pos.y, rotation + 1.5708, 10, false, game) );
				Sound.playSound("enemyShoot");
				currenttick=0;
			}
			
			
			//MOVEMENT
			if (Vector2.disSq(this.game.player.pos, this.pos)<idealMinDistance*idealMinDistance) {
				
				if (this.vel.dis()<4) //acceleration, with a max speed of 10
					this.vel.add( Vector2.sub(this.game.player.pos, this.pos).normalize().multiply(-0.15) );
			}else if (Vector2.disSq(this.game.player.pos, this.pos)>idealMaxDistance*idealMaxDistance) {
				if (this.vel.dis()<4) //acceleration, with a max speed of 10
					this.vel.add( Vector2.sub(this.game.player.pos, this.pos).normalize().multiply(0.3) );
			}
			this.vel.multiply(0.9);
			updatePos();
			
			
			
			//MOVMENT(BOIDS REPEL)
			for (Enemy obj : game.enemies) {
				Vector2 diff = Vector2.sub(this.pos,obj.pos);
				double dis = diff.dis();
	    		if (dis<200) {
	    			this.vel.add( diff.normalize().multiply( (200-dis) * 0.005 ));
	    		}
			}
			
			
			// ROTATION
			double lookat = Vector2.angleLookAt(this.pos, this.game.player.pos);

			// Shortest angular difference in RADIANS (-π to π)
			double diff = ((lookat - this.rotation) % (2 * Math.PI) + 3 * Math.PI) % (2 * Math.PI) - Math.PI;

			if (Math.abs(diff) > rotationThreshold) {
			    this.setRotation(lerp(this.rotation, this.rotation + diff, 0.1));
			}
			
			
			
		}
		
	}
	
	
	public void damage(int amount) {
		health-=amount;
		healthBar.setHealth(health);
		
		if (health<=0) {
			invincible=true;
			healthBar.frame.setVisible(false);
			minimapTracker.getParent().remove(minimapTracker);
			game.camera.camShake.shake(13, 400, 6);
			playDefeatAnim();
		}
	}
	
	
}
