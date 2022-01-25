
public class Main {
	public static void main(String[] args) {
		Counter c1 = new Counter() ;
		
		c1.status();
		System.out.println(c1.status());
		// 0 (int)
		
		c1.inc();
		System.out.println(c1.status());
		// 1 (int)
		
		c1.inc();
		System.out.println(c1.status());
		// 2 (int)
		
		c1.dec();
		System.out.println(c1.status());
		// 1 (int)
		
		c1.reset();
		System.out.println(c1.status());
		// 0 (int) 
	}
}


