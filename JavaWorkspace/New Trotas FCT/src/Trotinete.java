
public class Trotinete {
	
	private int numRents; 
	private int totalTime;
	private int lastTime;
	private String idTrot1, matricula1;
	boolean parada;
	boolean inactiva;
	
	public Trotinete (String idTrot, String matricula) {
		inactiva = false;
		parada = true;
		idTrot1 = idTrot;
		matricula1 = matricula;
		numRents = 0;
		totalTime = 0;
	}

	public void alugarTrt() {
		parada = false;
	}
	public void libertarTrt(int time) {
		parada = true;
		numRents += 1;
		totalTime += time;
		lastTime = time;
	}
	public void usarPromocao() {
		numRents -= 1;
		totalTime -= lastTime;
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
	public boolean paradaTrt() {
		return parada;
	}
	public boolean inactiva() {
		return inactiva;
	}
	
}
