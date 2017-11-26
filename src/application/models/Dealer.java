package application.models;

public class Dealer extends GamePlayer {

	int handsDealt;
	Card holeCard;
	
	public Dealer(String name) {
		super(name);
		this.handsDealt = 0;
	}

	@Override
	String getName() {
		return this.name;
	}

//	@Override
//	public void dealhand(Dealer d, Player p) {
//		
//		// get a reference to the gameDeck
//		ArrayList<Card> gameDeck = GameManager.game_deck;
//		
//		// deal 2 cards to the player...
//		// start at the top of the deck - index 0
//		p.addCardToHand(gameDeck.get(0));
//		// and then remove the card from the deck
//		gameDeck.remove(0);
//		
//		d.addCardToHand(gameDeck.get(0));
//		gameDeck.remove(0);
//		
//		// and repeat
//		p.addCardToHand(gameDeck.get(0));
//		gameDeck.remove(0);
//		
//		// now add the dealer's hole card
//		
//	}

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
