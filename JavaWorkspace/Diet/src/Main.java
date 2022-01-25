
public class Main {
	public static void main(String[] args) {
		Diet d = new Diet();
		
		System.out.println(d.getBalance());
		//0(int)
		System.out.println(d.eatTimes());
		//0(int)
		
		d.eat(50);
		d.eat(70);
		System.out.println(d.eatTimes());
		//2
		System.out.println(d.burnTimes());
		//0
		d.burn(20);
		System.out.println(d.getBalance());
		//100(int)
		
		d.eat(75);
		d.eat(100);
		d.burn(40);
		d.burn(30);
		System.out.println(d.getBalance());
		//205(int)
		
		System.out.println(d.eatTimes());
		//4(int)
		System.out.println(d.burnTimes());
		//3(int)
	}
}
