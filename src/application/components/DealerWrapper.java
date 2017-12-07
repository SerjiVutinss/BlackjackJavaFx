package application.components;

import application.models.GamePlayer;

public class DealerWrapper extends GamePlayerWrapper {

	// class may not be strictly necessary but including so I can easily implement
	// extra dealer specific UI functionality in future
	public DealerWrapper(GamePlayer gamePlayer) {
		super(gamePlayer);
		this.handWrapper.lblHandScore.setVisible(false);
	}

}
