
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Sistema s = new Sistema();
		Scanner in = new Scanner(System.in);
		
		String opt = readOption(in);

		while (!opt.equals("SAIR")){
			executeOption(in,opt,s);
			opt = readOption(in);
		}
		System.out.println("Saindo...");
		processEstadoSistema(in,s);
		in.close();
	}
	private static String readOption(Scanner in) {
		return in.next().toUpperCase();
	}
	private static void executeOption(Scanner in, String opt, Sistema s) {
		switch (opt) {

		case "ADCLIENTE":
			processAdCliente(in,s);
			break;
		case "REMCLIENTE":
			processRemCliente(in,s);
			break;
		case "ADTROT":
			processAdTrot(in,s);
			break;
		case "DADOSCLIENTE":
			processDadosCliente(in,s);
			break;
		case "TROT":
			processTrot(in,s);
			break;
		case "DADOSTROT":
			processDadosTrot(in,s);
			break;
		case "CLIENTE":
			processCliente(in,s);
			break;
		case "CARRSALDO":
			processCarrSaldo(in,s);
			break;
		case "ALUGAR":
			processAlugar(in,s);
			break;
		case "LIBERTAR":
			processLibertar(in,s);
			break;
		case "PROMOCAO":
			processPromocao(in,s);
			break;
		case "DESTROT":
			processDesTrot(in,s);
			break;
		case "REACTROT":
			processReacTrot(in,s);
			break;
		case "ESTADOSISTEMA":
			processEstadoSistema(in,s);
			break;
		default:
			showUnknownCommand();
			break;
		}
	}
	private static void showUnknownCommand(){
		System.out.println("Comando invalido.");		
	}
	private static void processAdCliente(Scanner in,Sistema s) {
		String nif = in.next().trim();
		String email = in.next();
		String phone = in.next();
		String nome = in.next() + in.nextLine();

		switch (s.adCliente(nif,email,phone,nome)) {
		case 0:
			System.out.println("Cliente existente.");
			break;
		case 1:
			System.out.println("Insercao de cliente com sucesso.");
			break;
			}
	}
	private static void processRemCliente(Scanner in,Sistema s) {
		String nif = in.nextLine().trim();
		
		switch(s.remCliente(nif)) {
		case 0:
			System.out.println("Cliente removido com sucesso.");
			break;
		case 1:
			System.out.println("Cliente inexistente.");
			break;
		case 2:
			System.out.println("Cliente em movimento.");
			break;
			}
	}
	private static void processAdTrot(Scanner in,Sistema s) {
		String idTrot = in.next().trim();
		String matricula = in.next() + in.nextLine();
		
		switch(s.adTrot(idTrot,matricula)) {
		case 0:
			System.out.println("Insercao de trotinete com sucesso.");
			break;
		case 1:
			System.out.println("Trotinete existente.");
			break;
		}
		
	}
	private static void processDadosCliente(Scanner in,Sistema s) {
		String nif = in.nextLine().trim();
		
		switch(s.dadosCliente(nif)) {
		case 0:
			System.out.println(s.getNome() + ": " + s.getNif() + ", " + s.getEmail() + ", " + s.getPhone() + ", " + s.getBalance() + ", " + s.getTotalRentTime() + ", " + s.getNumRentsCl() + ", " + s.getMaxRentTime() + ", " + s.getAverageRentTime() + ", " + s.getExpenses());
			break;
		case 1:
			System.out.println("Cliente inexistente.");
			break;
		}
	}
	private static void processTrot(Scanner in,Sistema s) {
		String nif = in.nextLine().trim();
		
		switch(s.trot(nif)) {
		case 0:
			System.out.println(s.getIdTrot() + ", " + s.getMatricula());
			break;
		case 1:
			System.out.println("Cliente inexistente.");
			break;
		case 2:
			System.out.println("Cliente sem trotinete.");
			break;
		}
	}
	private static void processDadosTrot(Scanner in,Sistema s) {
		String idTrot = in.nextLine().trim();
		
		switch(s.dadosTrot(idTrot)) {
		case 0:
			System.out.println(s.getMatricula() + ": " + s.estado() + ", " + s.getNumRentsTrt() + ", " + s.getTotalTime());
			break;
		case 1:
			System.out.println("Trotinete inexistente.");
			break;
		}
	}
	private static void processCliente(Scanner in,Sistema s) {
		String idTrot = in.nextLine().trim();
		
		switch(s.cliente(idTrot)) {
		case 0:
			System.out.println(s.getNif() + ", " + s.getNome());
			break;
		case 1:
			System.out.println("Trotinete inexistente.");
			break;
		case 2:
			System.out.println("Trotinete nao alugada.");
			break;
		}
	}
	private static void processCarrSaldo(Scanner in,Sistema s) {
		String nif = in.next().trim();
		int amount = in.nextInt();
		
		switch(s.carrSaldo(nif,amount)) {
		case 0:
			System.out.println("Carregamento efectuado.");
			break;
		case 1:
			System.out.println("Valor invalido.");
			break;
		case 2:
			System.out.println("Cliente inexistente.");
			break;
		}
	}
	private static void processAlugar(Scanner in,Sistema s) {
		String nif = in.next().trim();
		String idTrot = in.next().trim();
		
		switch(s.alugar(nif,idTrot)) {
		case 0:
			System.out.println("Aluguer efectuado com sucesso.");
			break;
		case 1:
			System.out.println("Cliente inexistente.");
			break;
		case 2:
			System.out.println("Trotinete inexistente.");
			break;
		case 3:
			System.out.println("Trotinete nao pode ser alugada.");
			break;
		case 4:
			System.out.println("Cliente sem saldo suficiente.");
			break;
		}
	}
	private static void processLibertar(Scanner in,Sistema s) {
		String idTrot = in.next().trim();
		int minutos = in.nextInt();
		
		switch(s.libertar(idTrot,minutos)) {
		case 0:
			System.out.println("Aluguer terminado.");
			break;
		case 1:
			System.out.println("Valor invalido.");
			break;
		case 2:
			System.out.println("Trotinete inexistente.");
			break;
		case 3:
			System.out.println("Trotinete nao alugada.");
			break;
		}
	}
	private static void processPromocao(Scanner in, Sistema s) {
		String nif = in.next().trim();
		
		switch(s.promocao(nif)) {
		case 0:
			System.out.println("Promocao aplicada.");
			break;
		case 1:
			System.out.println("Cliente inexistente.");
			break;
		case 2:
			System.out.println("Cliente iniciou novo aluguer.");
			break;
		case 3:
			System.out.println("Promocao ja aplicada.");
			break;
		}
	}
	private static void processDesTrot(Scanner in, Sistema s) {
		String idTrot = in.next().trim();
		
		switch(s.desTrot(idTrot)) {
		case 0:
			System.out.println("Trotinete desactivada.");
			break;
		case 1:
			System.out.println("Trotinete inexistente.");
			break;
		case 2:
			System.out.println("Trotinete em movimento.");
			break;
		}
	}
	private static void processReacTrot(Scanner in, Sistema s) {
		String idTrot = in.next().trim();
		
		switch(s.reacTrot(idTrot)) {
		case 0:
			System.out.println("Trotinete reactivada.");
			break;
		case 1:
			System.out.println("Trotinete inexistente.");
			break;
		case 2:
			System.out.println("Trotinete nao inactiva.");
			break;
		}
	}
	private static void processEstadoSistema(Scanner in, Sistema s) {
		System.out.println("Estado actual: " + s.getAlugueres() + ", " + s.getTotalCentimos() + ", " + s.getAtrasos());
	}

}