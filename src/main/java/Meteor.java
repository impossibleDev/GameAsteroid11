
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Meteor extends Sprite{
	
	static ImageIcon meteor1Img = StaticAssets.loadImage("sprites/meteor1.png");
	static ImageIcon meteor2Img = StaticAssets.loadImage("sprites/meteor2.png");
	double rotationspeed;
	private int randomNumber(int min, int max) {
		return min + (int)(Math.random() * ((max - min) + 1));

	}
	
	public Meteor(double startX, double startY, Game game){
		this.game = game;
		
		rotationspeed = Math.random()/40;
		if (Math.random()<0.5)
			this.setIcon(meteor1Img);
		else
			this.setIcon(meteor2Img);
		this.setPos(startX,startY);
		game.spritesPane.add(this);
	}
	
	public void update() {
		updatePos();
		setRotation(rotation+rotationspeed);
		
		if (Vector2.disSq(this.pos,game.player.pos)>game.loadedDis*game.loadedDis) {
			destroy();
		}
	}
}
