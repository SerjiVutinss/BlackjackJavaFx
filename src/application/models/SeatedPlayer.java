package application.models;

public class SeatedPlayer extends GamePlayer {
	
	double balance;

	public SeatedPlayer(String name, double balance) {
		super(name);
		this.balance=balance;
	}

	public SeatedPlayer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	String getName() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	void addCardToHand(Card c) {
//		// TODO Auto-generated method stub
//
//	}

	@Override
	void clearHand() {
		// TODO Auto-generated method stub
	}
}
