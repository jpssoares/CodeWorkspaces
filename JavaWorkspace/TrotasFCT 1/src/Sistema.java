
public class Sistema {
	
	private int alugueres, totalCentimos, atrasos;
	
	public Sistema() {
		alugueres = 0;
		totalCentimos = 0;
		atrasos = 0;
	}
	public void libertar(int time, int cost) {
		alugueres += 1;
		totalCentimos += cost;
		if (time > 60) {
			atrasos += (time - 60);
		}
	}
	public void adDespesas(int value) {
		totalCentimos += value;
	}
	public int getAlugueres() {
		return alugueres;
	}
	public int getTotalCentimos() {
		return totalCentimos;
	}
	public int getAtrasos() {
		return atrasos;
	}
}
