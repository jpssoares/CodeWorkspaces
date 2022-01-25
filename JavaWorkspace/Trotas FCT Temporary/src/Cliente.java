
public class Cliente {
	 
	private int balance; // Saldo do cliente em centimos de Euro
	private int numRents; // Numero de alugueres efetuados pelo cliente
	private int totalRentTime; // Numero total de minutos de aluguer
	private int maxRentTime; //O numero maximo de minutos de aluguer que ja efetuou num aluguer
	private int expenses; // Total de centimos que ja gastou no sistema
	private int timeLate; // Numero total de minutos de atraso na entrega das trotinetes
	private String nifCl, emailCl, phoneCl, nomeCl, rentIdTrot, rentMatricula;
	private boolean parado;
	private boolean existsCl;
	
	public Cliente () {
		existsCl = false;
		balance = 200;
		totalRentTime = 0;
		numRents = 0;
		maxRentTime = 0;
		expenses = 0;
		timeLate = 0;
	}
	public void adCliente(String nif, String email, String phone, String nome) {
		existsCl = true;
		parado = true;
		nifCl = nif;
		emailCl = email;
		phoneCl = phone;
		nomeCl = nome;

	}
	public void remCliente() {
		existsCl = false;
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
	public void alugarCl(String idTrot, String matricula) {
		
		parado = false;
		rentIdTrot = idTrot;
		rentMatricula = matricula;
		
	}
	public void libertarCl(int time, int cost) {
		
		/* Vai cobrar o valor do aluguer, 
		 * somar + 1 ao numero de aluguers
		 * somar o tempo de aluguer ao tempo total
		 * e vai somar o valor do aluguer ao valor total das despesas do cliente
		*/
		
		parado = true;
		balance -= cost;
		numRents += 1;
		totalRentTime += time;
		expenses += cost;
		
		if (time > maxRentTime || maxRentTime == 0) {
			
			//Se o tempo de aluguer for maior que o tempo maximo de aluguer, ou se ainda nao tenha sido feito nenhum aluguer
			
			maxRentTime = time;
		}
	}
	public int getBalance() {
		//Devolve o saldo do cliente em centimos de Euro
		return balance;
	}
	public int getNumRents() {
		//Devolve o numero de alugueres
		return numRents;
	}
	public int getTotalRentTime() {
		//Devolve o tempo total de aluguer em minutos
		return totalRentTime;
	}
	public int getMaxRentTime(){
		//Devolve o tempo maximo de aluguer
		return maxRentTime;
	}

	public double getAverageRentTime() {
		//Devolve o tempo medio de aluguer
		double averageConcentration = (totalRentTime / numRents);
		return (averageConcentration); // Numero medio de minutos que costuma durar um aluguer feito por si
	}
	public int getExpenses() {
		//Devolve as despesas do cliente
		return expenses;
	}
	
	// Tempo de atraso
	
	public void addTimeLate(int time) {
		timeLate += time;
	}
	public int getTimeLate() {
		return timeLate;
	}
	
	//Dados do cliente
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
	public String getRentIdTrot() {
		return rentIdTrot;
	}
	public String getRentMatricula() {
		return rentMatricula;
	}
	public boolean paradoCl() {
		return parado;
	}
	public boolean existsCl() {
		return existsCl;
	}
}