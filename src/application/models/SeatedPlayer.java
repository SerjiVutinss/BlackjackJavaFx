package application.models;

public class SeatedPlayer extends GamePlayer {

	private double balance;

	public SeatedPlayer(String name, double balance) {
		super(name);
		this.balance = balance;
	}

	public SeatedPlayer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public double getBalance() {
		return this.balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void addToBalance(double amount) {
		this.balance += amount;
	}

	public void deductFromBalance(double amount) {
		this.balance -= amount;
	}

	@Override
	String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
