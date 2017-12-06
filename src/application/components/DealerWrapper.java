package application.components;

import application.models.GamePlayer;

public class DealerWrapper extends GamePlayerWrapper {

	public boolean hasHit = false;
	
	// dealer must score at least 17
	//public static int MIN_SCORE = 17;
	
	public DealerWrapper(GamePlayer gamePlayer) {
		super(gamePlayer);
		// TODO Auto-generated constructor stub
	}

}
