
public class Diet {
	private int caloriesIn;
	private int caloriesOut;
	private int nEat;
	private int nBurn;
	
	public Diet() {
		caloriesIn = 0;
		caloriesOut = 0;
		
		nEat = 0;
		nBurn = 0;
	}
	public void eat (int c) {
		caloriesIn += c;
		nEat += 1;
	}
	public void burn (int c) {
		caloriesOut += c;
		nBurn += 1;
	}
	public int getBalance() {
		return caloriesIn - caloriesOut;
	}
	public int eatTimes() {
		return nEat;
	}
	public int burnTimes() {
		return nBurn;
	}
	public boolean isBalancenegative() {
		return (this.getBalance() < 0);
	}
	
	public float averageEatenCallories() {
		return caloriesIn / (float)nEat;
	}
}
