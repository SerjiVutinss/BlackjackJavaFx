package application.models;

import java.util.ArrayList;

public abstract class GamePlayer {

	public String name;
	//ArrayList<Card> currentHand;
	public Hand hand;
	
	public GamePlayer(String name) {
		this.name = name;

		this.hand = new Hand();
	}
	
	abstract String getName();
	
	public void addCardToHand(Card c) {
	}
	
	abstract void clearHand();
}