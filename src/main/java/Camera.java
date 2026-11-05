
public class Camera extends Vector2{
	
	protected Game game;
	CamShake camShake;
	double plrToMouseLerp = 0.3, damping = 0.03;
	double xRaw, yRaw;
	private static double lerp(double a, double b, double t) {
	    return a + t * (b - a);
	}
    
	
	public Camera(Game game) {
		super(0,0);
		this.game = game;
		camShake = new CamShake();
	}
	
	public void update() {
		xRaw = lerp(xRaw,lerp(game.player.pos.x-(game.windowSize.width/2),game.mousePosWorld.x-(game.windowSize.width/2),plrToMouseLerp),damping);
    	yRaw = lerp(yRaw,lerp(game.player.pos.y-(game.windowSize.height/2),game.mousePosWorld.y-(game.windowSize.height/2),plrToMouseLerp),damping);
    	
    	camShake.update();
    	x=xRaw+camShake.x;
    	y=yRaw+camShake.y;
	}
	
	
}
