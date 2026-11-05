import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class HealthBar {
	
	JLabel frame, bar;
	double health, maxHealth;
	private static final ImageIcon frameImg = StaticAssets.loadImage("sprites/healthbar_frame.png");
	private static final ImageIcon barImg = StaticAssets.loadImage("sprites/healthbar_bar.png");
	public HealthBar(Sprite sprite, double health) {
		this.health = health;
		this.maxHealth = health;
				
		frame = new JLabel();
		frame.setIcon(frameImg);
		frame.setBounds((sprite.sizeX-79) /2,0,79,15);
		
		bar = new JLabel();
		bar.setIcon(barImg);
		bar.setBounds(4,6,72,6);
		
		frame.add(bar);
		sprite.add(frame);
	}
	
	public void setHealth(double health) {
		this.health = health;
		bar.setBounds(4,6,(int) (health/maxHealth*72),6);
	}
	
	public void setMaxHealth(int health) {
		this.maxHealth = health;
//		bar.setBounds(4,6,(int)health/maxHealth*72,6);
	}
}
