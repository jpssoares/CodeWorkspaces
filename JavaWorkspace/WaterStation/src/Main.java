import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		WaterStation ws = new WaterStation();
		Scanner in = new Scanner(System.in);
		
		double c1 = in.nextDouble();
		
		ws.sampleLead(c1);
		in.nextLine();
		
		c1 = in.nextDouble();
		ws.sampleLead(c1);
		in.nextLine();
		
		c1 = in.nextDouble();
		ws.sampleLead(c1);
		in.nextLine();
		
		c1 = in.nextDouble();
		ws.sampleLead(c1);
		in.nextLine();
		
		c1 = in.nextDouble();
		ws.sampleLead(c1);
		in.nextLine();
		
		in.close();
		
		System.out.println("Average concentration: " + (String.format("%.2f", ws.getAverage())));
		System.out.println("Maximum concentration: " + (String.format("%.2f", ws.getMaximum())));
		System.out.println("Minimum concentration: " + (String.format("%.2f", ws.getMinimum())));
		
	}	
}

