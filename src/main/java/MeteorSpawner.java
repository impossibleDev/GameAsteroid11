
public class MeteorSpawner extends Sprite{
	
	int nextSpawn;
	protected Game game;
	private int randomNumber(int min, int max) {
		return min + (int)(Math.random() * ((max - min) + 1));

	}
	public MeteorSpawner(Game game) {
		this.game = game;
	}
	
	public void update() {
		nextSpawn--;
		if (nextSpawn<=0) {
			nextSpawn = randomNumber(180,170);
			//new meteor
			Meteor obj = new Meteor(
					game.player.pos.x+randomNumber(-game.windowSize.width,game.windowSize.width)/1.5,
					game.player.pos.y+randomNumber(-game.windowSize.height,game.windowSize.height)/1.5, game
					);
			obj.vel.x= randomNumber(-70,70)/10;
			obj.vel.y= randomNumber(-70,70)/10;
			game.meteors.add(obj);
		}
	}
}
