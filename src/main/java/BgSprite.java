import javax.swing.ImageIcon;
import javax.swing.JLabel;
public class BgSprite{
	
	public Sprite bg1 = new Sprite();
	public Sprite bg2 = new Sprite();
	public Sprite bg3 = new Sprite();
	public Sprite bg4 = new Sprite();
	public double posScale;
	protected int resX, resY;
	private double lastX, lastY;
	protected Game game;
	public BgSprite(ImageIcon img, double posScale, Game game) {

		this.posScale = posScale;
		this.game = game;
		this.resX=img.getIconWidth();
		this.resY=img.getIconHeight();
		bg1.game = game;
		bg1.setIcon(img);
		bg1.sizeX = resX;
		bg1.sizeY = resY;
		bg1.pos.x = 0;
		
		bg2.game = game;
		bg2.setIcon(img);
		bg2.sizeX = resX;
		bg2.sizeY = resY;	
		bg2.pos.x = resX;
		
		
		bg3.game = game;
		bg3.setIcon(img);
		bg3.sizeX = resX;
		bg3.sizeY = resY;
		bg3.pos.x = 0;
		bg3.pos.y = resY;
		
		bg4.game = game;
		bg4.setIcon(img);
		bg4.sizeX = resX;
		bg4.sizeY = resY;	
		bg4.pos.x = resX;
		bg4.pos.y = resY;
		
		game.bgSprites.add(this);
    	game.bgPane.add(bg1);
    	game.bgPane.add(bg2);
    	game.bgPane.add(bg3);
    	game.bgPane.add(bg4);
	}
	
	public BgSprite setSize(int x, int y) {
		resX=x;
		resY=y;
		
		bg1.pos.x = 0;    bg1.pos.y = 0;
	    bg2.pos.x = resX; bg2.pos.y = 0;
	    bg3.pos.x = 0;    bg3.pos.y = resY;
	    bg4.pos.x = resX; bg4.pos.y = resY;
		
		return this;
	}
	public void updateSprite(Sprite obj) {
		
		//Infinite tiling
		if (obj.pos.x<-resX) {
			obj.pos.x+=resX*2;
		}
		if (obj.pos.x>resX) {
			obj.pos.x-=resX*2;
		}
		
		if (obj.pos.y<-resY) {
			obj.pos.y+=resY*2;
		}
		if (obj.pos.y>resY) {
			obj.pos.y-=resY*2;
		}
		
		//Offset position by displacement*posScale
		obj.pos.x+=(lastX-game.camera.x)*posScale;
		obj.pos.y+=(lastY-game.camera.y)*posScale;
		obj.setBounds((int)obj.pos.x, (int)obj.pos.y, resX, resY);
	}
	public void update() {
		
		//Update all sprites
		updateSprite(bg1);
		updateSprite(bg2);
		updateSprite(bg3);
		updateSprite(bg4);
		
		//Set last camera position
		lastX=game.camera.x;
		lastY=game.camera.y;
	}
}
