import acm.graphics.GImage;

public enum Difficulty {
    EASY, MEDIUM, HARD, IMPOSSIBLE;
	
	public double Difficulty() {
		switch(this) {
			case EASY: return 1;
			case MEDIUM: return 0.75;
			case HARD: return 0.5;
			case IMPOSSIBLE: return 0.10;
		}
		return 0;
	}
}
