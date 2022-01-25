
public class ConstellationStock {
	private float balance;
	private int numberBeerSold;
	private int numberBeerDiscounted;
	
	public static final int BEER_COST = 10;
	public static final int IDEAL_WEISS = 200;
	public static final int IDEAL_LAGER = 100;
	public static final float DISCOUNT = 0.2f;
	
	public ConstellationStock(float weissCost, float lagerCost){
		balance = 0;
		numberBeerDiscounted = 0;
		numberBeerSold = 0;
		
		}
	
	public float request(int numberOfWeiss, int numberOfLager){
		float interestRate;
		
		if(numberOfWeiss >= IDEAL_WEISS && numberOfLager >= IDEAL_LAGER) {
			interestRate = DISCOUNT;
			numberBeerDiscounted = numberBeerDiscounted + numberOfWeiss + numberOfLager;
			
		}
		else {
			interestRate = 1f;
			numberBeerSold = numberBeerSold + numberOfWeiss + numberOfLager;
		}
		balance = balance + BEER_COST * interestRate * numberOfWeiss + BEER_COST * interestRate * numberOfLager;
		
		return (BEER_COST * interestRate * numberOfWeiss + BEER_COST * interestRate * numberOfLager);
		
	}
	public int soldAtPvp() {
		return numberBeerSold;
	}
	public int soldAtDiscount() {
		return numberBeerDiscounted;
	}
	public float cash(){
		return balance;
	}
	public float iva() {
		return (balance * 0.23f);
	}
	public float profit() {
		return (balance - this.iva() -);
	}
	public boolean hasProfit() {
		return (balance > 0);
	}
}

