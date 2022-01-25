
public class BankAccount {
	private int balance;
	
	public BankAccount() {
		balance = 0;
	}
	public BankAccount (int initial) {
		balance = initial;
	}
	public void deposit (int amount) {
		balance = balance + amount;
	}
	public void withdraw (int amount) {
		balance = balance - amount;
	}
	public int getBalance() {
		return balance;
	}
	public boolean isInRedZone() {
		return (balance < 0);
	}
}
