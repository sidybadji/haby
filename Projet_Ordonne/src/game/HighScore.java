package game;

public class HighScore {
	
 private int meilleurScore ;
 private Vaisseau v;
 	
 	public HighScore() {
 		this.meilleurScore = v.getScore();
 	}
 	
 	public void changerMeilleurScore(int x) {
 		this.meilleurScore = x;
 	}
 	public int getMeilleurScore() {
 		return this.meilleurScore;
 	}
}
