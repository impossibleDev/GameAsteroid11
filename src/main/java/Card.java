import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Card extends JButton{
	
	static ImageIcon cardFrameSelectedImg = StaticAssets.loadImage("sprites/card_selected.png");
	static ImageIcon cardFrameImg = StaticAssets.loadImage("sprites/card.png");
	
	
	public int option;
	JLabel icon, description;
	
	protected Game game;
	protected CardUi cardui;
	public Card(CardUi cardui, Game game) {
		this.cardui = cardui;
		this.game = game;
	    setLayout(null); // ← stops JButton resizing to fit children

	    setIcon(cardFrameImg);
	    setContentAreaFilled(false);
	    setBorderPainted(false);
	    setFocusable(false); // stops button stealing focus from game
//	    setFocusPainted(false);
	    setPreferredSize(new Dimension(241, 362));
	    
	    icon = new JLabel();
	    icon.setSize(48, 48);
	    icon.setLocation(96, 50);
	    add(icon);
	    
	    description = new JLabel();
	    description.setFont(StaticAssets.jersey10);
	    description.setBounds(20,60,201, 180);
	    description.setHorizontalAlignment(CENTER);
	    description.setForeground(Color.white);

        add(description);
	    
	    addActionListener(e -> {
	        if (cardui.selected!=null) {
	        	cardui.selected.setIcon(cardFrameImg);
	        }
	        setIcon(cardFrameSelectedImg);
	        cardui.selected=this; 
	    }
		);
	    
	    cardui.cardContainer.add(this);
	}
	
	public void set(int option) {
		this.option = option;
		icon.setIcon(CardUi.icon.get(option));
		description.setText("<html>" + CardUi.description.get(option));
	}
	
	public void applyUpgrade() {
		game.player.health+=10;
		switch(option) {
		case 0:
			game.player.health+=10;
		case 1:
			game.player.speed+=4;
		case 2:
			game.player.damage+=10;
		}
	}
	
}
