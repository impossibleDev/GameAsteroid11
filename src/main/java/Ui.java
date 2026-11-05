import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class Ui extends JLayeredPane{
	public Minimap minimap;
	public CardUi cardFrame;
	public Game game;
	
	JLabel wave = new JLabel("WAVE X");
	JLabel nextWaveTitle = new JLabel("WAVE CLEARED");
	
	public Ui(Game game) {
		this.game = game;
//		setLayout(null);
		wave.setBounds(0,20,game.frame.getWidth(),30);
		wave.setFont(StaticAssets.jersey10);
		wave.setForeground(Color.white);
		wave.setHorizontalAlignment(JLabel.CENTER);
		this.add(wave);
		
		nextWaveTitle.setBounds(0,game.windowSize.height/2 -30,game.windowSize.width,60);
		nextWaveTitle.setFont(StaticAssets.jersey25);
		nextWaveTitle.setForeground(Color.white);
		nextWaveTitle.setVerticalAlignment(JLabel.CENTER);
		nextWaveTitle.setHorizontalAlignment(JLabel.CENTER);
		nextWaveTitle.setVisible(false);
		this.add(nextWaveTitle);
		
	}
	
	public void update() {
		minimap.update();
		cardFrame.update();
	}
}
