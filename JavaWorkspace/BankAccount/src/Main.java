
public class Main {
	public static void main(String[] args) {
		BankAccount BankAccount1 = new BankAccount(2000) ;
		BankAccount BankAccount2 = new BankAccount();
		
		System.out.println(BankAccount1.getBalance());
		//2000
		System.out.println(BankAccount2.getBalance());
		//0
		
		BankAccount1.deposit(1000);
		System.out.println(BankAccount1.getBalance());
		//3000
		
		BankAccount2.deposit(1000);
		System.out.println(BankAccount2.getBalance());
		//1000
		
		BankAccount1.withdraw(2000);
		System.out.println(BankAccount1.getBalance());
		//1000
		BankAccount2.withdraw(2000);
		System.out.println(BankAccount2.getBalance());
		//-1000
		
		System.out.println(BankAccount1.isInRedZone());
		//False
		System.out.println(BankAccount2.isInRedZone());
		//True
	}
}
