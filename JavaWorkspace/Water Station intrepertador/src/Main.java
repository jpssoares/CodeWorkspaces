
import java.util.Scanner;

public class Main {
	public static final String REGISTAR_VALOR = "RC";
	public static final String NUMERO_CONCENTRACOES = "NC";
	public static final String MEDIA = "MED";
	public static final String MAXIMO = "MAX";
	public static final String MINIMO = "MIN";
	public static final String SAIR = "S";

	public static void main(String[] args) {
		
		WaterStation ws = new WaterStation();
		Scanner in = new Scanner(System.in);
		
		String opt = readOption(in);
		

		while (!opt.equals(SAIR)){
			executeOption(in,ws,opt);
			opt = readOption(in);
		}
		in.close();
		
	}
	private static String readOption(Scanner in) {
		return in.next().toUpperCase();
	}
	private static void executeOption(Scanner in, WaterStation ws, String opt) {
			switch (opt) {
			case "RC":
				processSampleLead(in,ws);
				break;
				
			case "NC":
				processNumberSamples(in,ws);
				break;
				
			case "MED":
				processGetAverage(in,ws);
				break;
				
			case "MAX":
				processGetMaximum(in,ws);
				break;
			
			case "MIN":
				processGetMinimum(in,ws);
				break;
			
			case "S":
				in.nextLine();
				break;
			default:
				showUnknownCommand();
				break;
			}
	}
	private static void showUnknownCommand(){
		System.out.println("Comando invalido.");
	}
	private static void processSampleLead(Scanner in, WaterStation ws) {
		
		double con = in.nextDouble();
		in.nextLine();
		
		if (con >= 0) {
			
			ws.sampleLead(con);
			System.out.println("Registo efectuado com sucesso.");
		}
		else {
			System.out.println("Amostra invalida.");
		}
	}
	private static void processNumberSamples(Scanner in, WaterStation ws) {
			System.out.println("Ha " + ws.getNumberSamples() + " concentracoes registadas.");
	}
	private static void processGetAverage(Scanner in, WaterStation ws) {
		if (ws.getNumberSamples() > 0) {
			
			System.out.println("Media: " + String.format("%.2f", ws.getAverage()));
		}
		else {
			System.out.println("Sem amostras.");
		}
		
	}
	private static void processGetMaximum(Scanner in, WaterStation ws) {
		if (ws.getNumberSamples() > 0) {
			System.out.println("Maxima: " + String.format("%.2f", ws.getMaximum()));
		}
		else {
			System.out.println("Sem amostras.");
		}
		
	}
	private static void processGetMinimum(Scanner in, WaterStation ws) {
		if (ws.getNumberSamples() > 0) {
			System.out.println("Minima: " + String.format("%.2f", ws.getMinimum()));
		}
		else {
			System.out.println("Sem amostras.");
		}
		
	}
}
