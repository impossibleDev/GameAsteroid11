import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class CardUi extends JLabel {
	
	
	static ArrayList<String> options = new ArrayList<>();
	static ArrayList<String> description = new ArrayList<>();
	static ArrayList<ImageIcon> icon = new ArrayList<>();
	
	
	static ImageIcon buttonImg = StaticAssets.loadImage("sprites/button.png");
	
	
	Card card1, card2, card3;
	
	JLabel cardContainer = new JLabel();
	JButton confirmButton = new JButton(buttonImg);
	public Card selected;
	
	protected Game game;
	public CardUi(Game game) {
		this.game = game;
		this.setBounds(game.frame.getBounds());
		
		options.add("TANK");
		icon.add(StaticAssets.loadImage("sprites/upgrades/tank.png"));
		description.add("Increases your health by +15hp, slows your speed by -2");
		
		options.add("SPEED");
		icon.add(StaticAssets.loadImage("sprites/upgrades/speed.png"));
		description.add("Increases your speed by +5");
		
		options.add("DAMAGE");
		icon.add(StaticAssets.loadImage("sprites/upgrades/damage.png"));
		description.add("Increases your damage by +5");
		
		//CARD CONTAINER
		card1 = new Card(this, game);
		card2 = new Card(this, game);
		card3 = new Card(this, game);
		
		cardContainer.setBounds(0,game.windowSize.height/2 - 200,game.windowSize.width,400);
		cardContainer.setLayout(new FlowLayout(FlowLayout.CENTER,40,0));
		cardContainer.add(card1);
		cardContainer.add(card2);
		cardContainer.add(card3);
		
		card1.set(0);
		card2.set(1);
		card3.set(2);
		add(cardContainer);
		
		//CONFIRM BUTTON
		confirmButton.setBounds(game.windowSize.width/2 -75, game.windowSize.height-175, 155, 50);
		add(confirmButton);
		confirmButton.setForeground(Color.white);
		confirmButton.setFocusable(false); 
		confirmButton.setContentAreaFilled(false);
		confirmButton.setBorderPainted(false);
		confirmButton.addActionListener(e -> {applyUpgrade();});
		
		setVisible(false);
		game.ui.add(this);
		
	}
	
	public void update() {
		setBounds(game.windowSize);
		cardContainer.setBounds(0,game.windowSize.height/2 - 200,game.windowSize.width,400);
		confirmButton.setBounds(game.windowSize.width/2 -75, game.windowSize.height-175, 155, 50);
	}
	
	public void Show() {
		selected=null;
		setVisible(true);
	}
	
	public void applyUpgrade() {
		
		if (selected!=null) {
			setVisible(false);
			selected.setIcon(Card.cardFrameImg);
			selected.applyUpgrade();
			game.waveSpawner.waveUiTick=0;
			game.waveSpawner.state=3;
		}
		
	}
}
