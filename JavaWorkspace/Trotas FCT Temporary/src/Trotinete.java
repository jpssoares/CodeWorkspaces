
public class Trotinete {
	
	private int numRents; // Numero de vezes que a trotinete foi alugada
	private int totalTime; // Numero de minutos que esteve em movimento
	private String idTrot1, matricula1, rentNif, rentNome;
	boolean existsTrt;
	boolean parada;
	boolean inactiva;
	
	public Trotinete () {
		existsTrt = false;
		numRents = 0;
		totalTime = 0;
	}
	public void adTrot(String idTrot, String matricula) {
		existsTrt = true;
		inactiva = false;
		parada = true;
		idTrot1 = idTrot;
		matricula1 = matricula;
		
	}
	public void remTrot() {
		existsTrt = false;
		numRents = 0;
		totalTime = 0;
	}
	public void alugarTrt(String nif, String nome) {
		parada = false;
		rentNif = nif;
		rentNome = nome;
	}
	public void libertarTrt() {
		parada = true;
	}
	
	public void desTrot() {
		inactiva = true;
	}
	public void reacTrot() {
		inactiva = false;
	}
	public int getNumRents() {
		return numRents;
	}
	public int getTotalTime() {
		return totalTime;
	}
	public String getIdTrot() {
		return idTrot1;
	}
	public String getMatricula() {
		return matricula1;
	}
	public String getRentNif() {
		return rentNif;
	}
	public String getRentNome() {
		return rentNome;
	}
	public boolean paradaTrt() {
		return parada;
	}
	public boolean inactiva() {
		return inactiva;
	}
	public boolean existsTrt() {
		return existsTrt;
	}

}
