
public class WaveSpawner {
	protected Game game;
	protected int wave = 0;
	public int state = -1; //0 for loading, 1 for battling(waiting/nothing), 2 for intermisiion, -1 for load(overide or mshtidk)
	public int waveUiTick = 0;
	public WaveSpawner(Game game) {
		this.game = game;
	}
	
	
	public void update() {
		if (state==1) {
			if (game.enemies.isEmpty()) {
				wave++;
				waveUiTick=0;
				state=2;
				System.out.print(game.enemies.isEmpty());
				//next wave ui text
			}
			
		}else if (state==2) {
			waveUiTick++;
			if (waveUiTick==1) {
				game.ui.nextWaveTitle.setVisible(true);
				Sound.playSound("wavecleared");
				game.ui.nextWaveTitle.setText("WAVE CLEARED");
				
			}else if (waveUiTick==75) {
				game.ui.nextWaveTitle.setVisible(false);
			}else if (waveUiTick==150) {
				game.ui.nextWaveTitle.setVisible(false);
				game.ui.cardFrame.Show();
			}
			
		}else if (state==3) {
			waveUiTick++;
			if (waveUiTick==100) {
				game.ui.nextWaveTitle.setVisible(true);
				game.ui.nextWaveTitle.setText("NEXT WAVE IN 3");
			}else if (waveUiTick==150) {
				game.ui.nextWaveTitle.setText("NEXT WAVE IN 3");
			}else if (waveUiTick==200) {
				game.ui.nextWaveTitle.setText("NEXT WAVE IN 2");
			}else if (waveUiTick==250) {
				game.ui.nextWaveTitle.setText("NEXT WAVE IN 1");
			}else if (waveUiTick==300) {
				game.ui.nextWaveTitle.setVisible(false);
			}else if (waveUiTick==350) {
				game.ui.nextWaveTitle.setVisible(true);
				game.ui.nextWaveTitle.setText("WAVE INCOMING");
			}else if (waveUiTick==400) {
				game.ui.nextWaveTitle.setVisible(false);
				state=-1;
			}
			
		}
		
		
	}
	public void spawnWithGrid(int rootX, int rootY, int gridSize, int enemyDis) {
		gridSize/=2;
		
		for (int x=-gridSize; x<=gridSize;x++) {
			for (int y=-gridSize; y<=gridSize;y++) {
				game.enemies.add( new Enemy(x*enemyDis + rootX,y*enemyDis + rootY, game) );
				
			}
		}
		
		game.ui.wave.setText("WAVE " +  wave);
	}
}
