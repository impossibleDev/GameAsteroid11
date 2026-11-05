import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Minimap {
	
	//IMAGE resources
	private static final ImageIcon frameImg = StaticAssets.loadImage("sprites/minimap.png");
	private static final ImageIcon enemyTrackerImg = StaticAssets.loadImage("sprites/mini_enemy.png");
	
	int distanceCovered = 800;
	public JLabel frame = new JLabel();
	public JPanel container = new JPanel();
	protected Game game;
	
	JLabel plrTracker;
	public JLabel newTracker(Sprite sprite) {
		JLabel tracker = new JLabel();
		tracker.setIcon(enemyTrackerImg);
		container.add(tracker);
		return tracker;
	}
	public Minimap(Game game) {
		this.game = game;
		frame.setIcon(frameImg);
		frame.setBounds(30,30,210,210);
		frame.setLayout(null);
		game.ui.add(frame);
		
		container.setBackground(Color.black);
		container.setBounds(5,5,201,200);
		frame.add(container);
		
		plrTracker = newTracker(game.player);
		
		
	}
	
	public void update() {
		for (Enemy obj : game.enemies) {
			double x = obj.pos.x-game.camera.x-(game.windowSize.width/2);
			double y = obj.pos.y-game.camera.y-(game.windowSize.height/2);
//			x=0;y=0;
			obj.minimapTracker.setBounds( (int)(x*0.05)+99, (int)(y*0.05)+99, 12, 12);
		}
		
		double x = game.player.pos.x-game.camera.x-(game.windowSize.width/2);
		double y = game.player.pos.y-game.camera.y-(game.windowSize.height/2);
		plrTracker.setBounds( (int)(x*0.1)+99, (int)(y*0.1)+99, 12, 12);
	}
	
	
}
