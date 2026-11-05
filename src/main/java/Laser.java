public class Laser extends Sprite{
	
	public int speed = 10;
	public int damage;
	public boolean isPlayerLaser;
	public Laser(double startX, double startY, double startRot, int damage, boolean isPlayerLaser, Game game){
		
		this.sizeX=32;//8*4;
		this.sizeY=32;//8*4;
		this.damage = damage;
		this.setIcon(StaticAssets.loadImage("sprites/laser_red.png"));
		
		this.game = game;
		this.setPos(startX,startY);
		this.setRotation(startRot);
		game.spritesPane.add(this);
		
		this.vel.x = speed * Math.cos(startRot);
		this.vel.y = speed * Math.sin(startRot);
		this.isPlayerLaser=isPlayerLaser;
		
	}
	
	public void update() {
		
		updatePos();
		if (Vector2.disSq(game.camera, this.pos)>game.loadedDis*game.loadedDis) {
			destroy();
		}
		
		boolean hitMeteor=false;
		for (Meteor obj : game.meteors) {
    		if (this.getBounds().intersects(obj.getBounds())) {
    			hitMeteor=true;
    		}
		}
		
		if (hitMeteor) {
			destroy();
		}else {
			if (isPlayerLaser) {
				for (Enemy obj : game.enemies) {
		    		if (!obj.invincible && this.getBounds().intersects(obj.getBounds())) {
		    			obj.damage(damage);
		    			destroy();
		    		}
				}
			}else {
				if (this.getBounds().intersects(game.player.getBounds())) {
					game.player.damage(damage);
					destroy();
				}
			}
		}
		
	}
}
