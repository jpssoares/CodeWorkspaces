
public class Cliente {
	 
	private int balance; 
	private int numRents; 
	private int totalRentTime; 
	private int maxRentTime; 
	private int expenses; 
	private int timeLate;
	private int lastCost;
	private int lastTime;
	private int lastMaxTime;
	private String nifCl, emailCl, phoneCl, nomeCl;
	private boolean parado;
	private boolean promocao;
	
	public Cliente (String nif, String email, String phone, String nome) {
		parado = true;
		promocao = false;
		nifCl = nif;
		emailCl = email;
		phoneCl = phone;
		nomeCl = nome;
		balance = 200;
		totalRentTime = 0;
		numRents = 0;
		maxRentTime = 0;
		expenses = 0;
		timeLate = 0;
	}
	public void carrSaldo(int amount) {
		balance += amount;
	}
	public void alugarCl() {
		parado = false;
	}
	public void libertarCl(int time, int cost) {
		promocao = false;
		parado = true;
		balance -= cost;
		numRents += 1;
		totalRentTime += time;
		expenses += cost;
		
		lastCost = cost;
		lastTime = time;
		
		if (time > maxRentTime || maxRentTime == 0) {
			lastMaxTime = maxRentTime;
			maxRentTime = time;
		}
	}
	public void usarPromocao() {
		promocao = true;
		balance += lastCost;
		numRents -= 1;
		totalRentTime -= lastTime;
		expenses -= lastCost;
		maxRentTime = lastMaxTime;
	}
	public int getBalance() {
		return balance;
	}
	public int getNumRents() {
		return numRents;
	}
	public int getTotalRentTime() {
		return totalRentTime;
	}
	public int getMaxRentTime(){
		return maxRentTime;
	}

	public int getAverageRentTime() {
		int averageConcentration;
		if (totalRentTime > 0) {
			averageConcentration = (totalRentTime / numRents);
		}
		else {
			averageConcentration = 0;
		}
		return averageConcentration;
	}
	public int getExpenses() {
		return expenses;
	}
	
	public void addTimeLate(int time) {
		timeLate += time;
	}
	public int getTimeLate() {
		return timeLate;
	}
	
	public String getNif() {
		return nifCl;
	}
	public String getEmail() {
		return emailCl;
	}
	public String getPhone() {
		return phoneCl;
	}
	public String getNome() {
		return nomeCl;
	}
	public boolean paradoCl() {
		return parado;
	}
	public boolean usouPromocao() {
		return promocao;
	}
}