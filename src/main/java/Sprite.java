
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Sprite extends JLabel{
	
	public Vector2 pos = new Vector2(0,0);
	public Vector2 vel = new Vector2(0,0);
	
	protected double rotation;
	public int sizeX= 36*4, sizeY = 36*4;
	
	protected Game game;
	public boolean removed = false;
	
	ImageIcon explosionImg = StaticAssets.loadImage("sprites/explosion.gif");

	
	public void moveBy(int dX, int dY) {
		this.pos.add(dX, dY);
		updatePos();
	}
	
	public void setPos(double X, double Y) {
		this.pos.set(X, Y);
		updatePos();
	}
	
	public void updatePos() {
		this.pos.x+=this.vel.x;
		this.pos.y+=this.vel.y;
		this.setBounds((int)(this.pos.x-this.game.camera.x-(this.sizeX/2) ), (int)(this.pos.y-this.game.camera.y-(this.sizeY/2)), sizeX, sizeY);
	}
	
	public void setRotation(double radians) {
        this.rotation = radians;
        this.repaint(); // Force the label to redraw with the new angle
    }
	
	
	@Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Rotate around the center of the JLabel
//        double rads = this.rotation // Math.toRadians(rotation);
        g2d.rotate(rotation, getWidth() / 2.0, getHeight() / 2.0);

        // Now draw the actual icon/image using the rotated graphics context
        super.paintComponent(g2d);
        
        g2d.dispose();
    }
	
	
	public boolean touchingEdge() {
		if (getY()<0 || getY()>game.windowSize.height-(sizeY/2)) {
			return true;
		}

        return getX() < 0 || getX() > game.windowSize.width - (sizeX / 2);
    }
	
	public void destroy() {
		removed = true;
		game.spritesPane.remove(this);
	}
	
	public void playAnimation(ImageIcon currentImg, ImageIcon animImg, int duration) {
		setIcon(animImg);
		
		Timer timer = new Timer(duration, e -> {
			setIcon(animImg);
			
		});
		timer.setRepeats(false);
		timer.start();
	}
	
	public void playDefeatAnim() {
		setIcon(explosionImg);
		Sound.playSound("explosion");
		Timer timer = new Timer(800, e -> {
			
			destroy();
		});
		timer.setRepeats(false);
		timer.start();
	}
	
	
}
