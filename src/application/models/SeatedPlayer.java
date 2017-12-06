package application.models;

public class SeatedPlayer extends GamePlayer {

	public double balance;

	public SeatedPlayer(String name, double balance) {
		super(name);
		this.balance = balance;
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
}
