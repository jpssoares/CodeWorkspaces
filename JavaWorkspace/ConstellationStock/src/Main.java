
public class Main {
	public static void main(String[] args) {
	ConstellationStock cs = new ConstellationStock(2.0f, 4.0f);
	
	System.out.println(cs.hasProfit());
	// false (boolean)
	
	(cs.request(30, 500));
	(cs.soldAtDiscount();
	// 0 (int)
	cs.request(200, 200);
	cs.soldAtDiscount();
	// 400 (int)
	cs.request(100, 30);
	cs.soldAtPvp();
	// 660 (int)
	cs.cash();
	// 9800.0 (float)
	cs.iva();
	// 2254.0 (float)
	cs.profit();
	// 3966.0 (float)
	System.out.println(cs.hasProfit());
	// true (boolean)
}
