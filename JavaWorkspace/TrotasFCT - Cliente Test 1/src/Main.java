
import java.util.Scanner;


public class Main {
	public static final int PRECO_BASE = 100;
	
	public static void main(String[] args) {
		Cliente cl = new Cliente();	
		Scanner in = new Scanner(System.in);
		
		String opt = readOption(in);

		while (!opt.equals("SAIR")){
			executeOption(in,opt,cl);
			opt = readOption(in);
		}
		in.close();
	}
	private static String readOption(Scanner in) {
		return in.next().toUpperCase();
	}
	private static void executeOption(Scanner in, String opt, Cliente cl) {
		switch (opt) {
		
		case "NIF":
			System.out.println(cl.getNif());
			break;
		case "EMAIL":
			System.out.println(cl.getEmail());
			break;
		case "PHONE":
			System.out.println(cl.getPhone());
			break;
		case "NOME":
			System.out.println(cl.getNome());
			break;
			
		case "ADCLIENTE":
			// Adicionar um novo cliente.
			String nif = in.next();
			String phone = in.next();
			String email = in.next();
			String nome = in.next() + in.nextLine();
			
			processAdCliente(in,cl,nif,email,phone,nome);
			break;
			
		case "REMCLIENTE":
			// Remover um cliente criado.
			
			processRemCliente(in,cl);
			break;
			
		case "DADOSCLIENTE":
			// Consultar dados do cliente.
			
			processDadosCliente(in,cl);
			break;
			
		case "SAIR":
			/*Consulta do estado do sistema e termina.
			* Inclui a mesma saida de dados que o comando anterior.
			*/
			in.nextLine();
			break;
		default:
			//Aplicar caso nao seja chamada nenhuma das operacoes referidas anteriormente.
			
			showUnknownCommand();
			in.nextLine();
			break;
			}
		}
	private static void showUnknownCommand(){
			System.out.println("Comando invalido");		
	}
	private static void processAdCliente(Scanner in, Cliente cl, String nif, String email, String phone, String nome) {
		String nif1 = nif;
	
		if (cl.existsCl()) {
			if (nif1 == cl.getNif()) {
				System.out.println("Cliente existente.");
			}
			else {
				cl.remCliente();
				cl.adCliente(nif,email,phone,nome);
				System.out.println("Insercao de cliente com sucesso.");
			}
		}
		else {
			cl.adCliente(nif,phone,email,nome);
			System.out.println("Insercao de cliente com sucesso.");
		}
	}
	private static void processRemCliente(Scanner in, Cliente cl) {
		String nif1 = in.nextLine();
		
		if (cl.existsCl() == true & nif1 == cl.getNif()) {
			if (cl.paradoCl()) {
				cl.remCliente();
				System.out.println("Cliente removido com sucesso.");
			}
			else {
				System.out.println("Cliente em movimento.");
			}
		}
		else { System.out.println("Cliente inexistente."); }
	}
	private static void processDadosCliente(Scanner in, Cliente cl) {
		String nif1 = in.next();
		
		if (cl.existsCl()) {
			if (nif1 == cl.getNif()) {
				System.out.println(cl.getNome() + ": " + cl.getNif() + ", " + cl.getEmail() + ", " + cl.getPhone() + ", " + cl.getBalance() + ", " + cl.getTotalRentTime() + ", " + cl.getNumRents() + ", " + cl.getMaxRentTime() + ", " + cl.getAverageRentTime() + ", " + cl.getExpenses());
			}
			else {
				System.out.println("Cliente inexitente.");
			}
		}
		else {
			System.out.println("Cliente inexitente.");
		}
	}
}
