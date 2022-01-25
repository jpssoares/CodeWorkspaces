
public class Main {
	
	public static void main(String[] args) {
		Lamp lamp1 = new Lamp();
		
		System.out.println(lamp1.isOn());
		// No inicio, lampada1 apagada
		
		lamp1.on();
		System.out.println(lamp1.isOn());
		
		lamp1.on();
		System.out.println(lamp1.isOn());
		//Como ja estava ligada, lampada1 continua ligada
		
		lamp1.off();
		System.out.println(lamp1.isOn());
		
		lamp1.off();
		System.out.println(lamp1.isOn());
		//Como ja estava desligada, lampada1 continua desligada
		
		System.out.println("^Lampada1");

		
		Lamp lamp2 = new Lamp();
		
		System.out.println(lamp2.isOn());
		// No inicio, lampada2 apagada
		
		lamp2.on();
		System.out.println(lamp2.isOn());
		
		lamp2.on();
		System.out.println(lamp2.isOn());
		//Como ja estava ligada, lampada2 continua ligada
		
		lamp2.off();
		System.out.println(lamp2.isOn());
		
		lamp2.off();
		System.out.println(lamp2.isOn());
		//Como ja estava desligada, lampada2 continua desligada
		
		System.out.println("^Lampada2");
	}
}