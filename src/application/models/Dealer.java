package application.models;

public class Dealer extends GamePlayer {

	public static int MUST_EQUAL = 17;
	Card holeCard;
	
	public Dealer(String name) {
		super(name);
	}

	@Override
	String getName() {
		return this.name;
	}

}
