
import java.util.Scanner;

public class Main {
	public static final int PRECO_BASE = 100;
	
	public static void main(String[] args) {
		
		Sistema s = new Sistema();
		
		Scanner in = new Scanner(System.in);
		
		String opt = readOption(in);

		while (!opt.equals("SAIR")){
			executeOption(in,opt,s);
			opt = readOption(in);
		}
		in.close();
	}
	private static String readOption(Scanner in) {
		return in.next().toUpperCase();
	}
	private static void executeOption(Scanner in, String opt, Sistema s) {
		switch (opt) {

		case "ADCLIENTE":
			// Adicionar um novo cliente.
			
			processAdCliente(in,s);
			break;
			
		case "REMCLIENTE":
			// Remover um cliente criado.
			
			processRemCliente(in,s);
			break;
			
		case "ADTROT":
			// Adicionar uma nova trotinete.
			
			processAdTrot(in,s);
			break;
			
		case "DADOSCLIENTE":
			// Consultar dados do cliente.
			
			processDadosCliente(in,s);
			break;
			
		case "TROT":
			// Devolve os dados da trotinete actualmente alugada pelo cliente com o NIF fornecido.
			
			processTrot(in,s);
			break;
			
		case "DADOSTROT":
			// Devolve os dados da trotinete com o idTrot fornecido.
			
			processDadosTrot(in,s);
			break;
		
		case "CLIENTE":
			//Consulta do cliente que esta a alugar a trotinete.
			
			processCliente(in,s);
			break;
		
		case "CARRSALDO":
			// O saldo atual do cliente identificado pelo NIF é adicionado com o valor dado.
			
			processCarrSaldo(in,s);
			break;
			
		case "ALUGAR":
			// Pedido de aluguer da trotinete pelo cliente.
			
			processAlugar(in,s);
			break;
		
		case "LIBERTAR":
			// Fim de aluguer da trotinete pelo cliente.
			
			processLibertar(in,s);
			break;
			
		case "ESTADOSISTEMA":
			/* Consulta do estado do sistema. O resultado inclui:
			 * - O numero total de alugueres efetuados no sistema;
			 * - O valor total de centimos de Euro gastos pelo cliente no sistema;
			 * - O numero de minutos de atraso registados na entrega da trotinete.
			*/
			
			processEstadoSistema(in,s);
			break;
		case "SAIR":
			/*Consulta do estado do sistema e termina.
			* Inclui a mesma saida de dados que o comando anterior.
			*/
			System.out.println("Saindo...");
			processEstadoSistema(in,s);
			in.nextLine();
			break;
		default:
			//Aplicar caso nao seja chamada nenhuma das operacoes referidas anteriormente.
			
			showUnknownCommand();
			break;
			}
		}
	private static void showUnknownCommand(){
			System.out.println("Comando invalido");		
	}
	private static void processAdCliente(Scanner in, Cliente cl) {
		
		String nif = in.next();
		String phone = in.next();
		String email = in.next();
		String nome = in.next() + in.nextLine();
		
		if (cl.existsCl() == false) {
			cl.adCliente(nif,phone,email,nome);
			System.out.println("Insercao de cliente com sucesso.");
		}
		else {
			if (nif == cl.getNif()) {
				System.out.println("Cliente existente.");
			}
			else {
				cl.remCliente();
				cl.adCliente(nif,phone,email,nome);
				System.out.println("Insercao de cliente com sucesso.");
			}
		}
	}
	private static void processRemCliente(Scanner in, Cliente cl) {
		String nif = in.nextLine();
		
		if (cl.existsCl() == true && nif == cl.getNif()) {
			if (cl.paradoCl() == true) {
				cl.remCliente();
				System.out.println("Cliente removido com sucesso.");
			}
			else {
				System.out.println("Cliente em movimento.");
			}
		}
		else { System.out.println("Cliente inexistente."); }
	}
	private static void processAdTrot(Scanner in, Trotinete trt) {
		String idTrot = in.next();
		String matricula = in.nextLine();
		
		if (trt.existsTrt() == false) {
			trt.adTrot(idTrot,matricula);
			System.out.println("Insercao de trotinete com sucesso.");
		}
		else {
			if(idTrot == trt.getIdTrot()) {
				System.out.println("Trotinete existente.");
			}
			else {
				trt.remTrot();
				trt.adTrot(idTrot,matricula);
				System.out.println("Insercao de trotinete com sucesso.");
			}
		}
	}
	private static void processDadosCliente(Scanner in, Cliente cl) {
		String nif = in.next();
		
		if (cl.existsCl() == true && nif == cl.getNif()) {
				System.out.println(cl.getNome() + ": " + cl.getNif() + ", " + cl.getEmail() + ", " + cl.getPhone() + ", " + cl.getBalance() + ", " + cl.getTotalRentTime() + ", " + cl.getNumRents() + ", " + cl.getMaxRentTime() + ", " + cl.getAverageRentTime() + ", " + cl.getExpenses());
		}
		else {
			System.out.println("Cliente inexitente.");
		}
	}
	private static void processTrot(Scanner in, Cliente cl) {
		String nif = in.next();
		
		if (cl.existsCl() == true && nif == cl.getNif()) {
				if (cl.paradoCl() == false) {
					System.out.println(cl.getRentIdTrot() + ", " + cl.getRentMatricula());
				}
				else {
					System.out.println("Cliente sem trotinete.");
				}
		}
		else {
			System.out.println("Cliente inexistente.");
		}
	}
	private static void processDadosTrot(Scanner in, Trotinete trt) {
		String idTrot = in.next();
		String estado;
		
		if (trt.paradaTrt() == false) {
			estado = "alugada";
		}
		else {
			estado = "parada";
		}
		
		if (trt.existsTrt == true && idTrot == trt.getIdTrot()) {
			System.out.println(trt.getMatricula() + ": " + estado + ", " + trt.getNumRents() + ", " + trt.getTotalTime() + ".");
		}
		else {
			System.out.println("Trotinete inexistente.");
		}
		
	}
	private static void processCliente(Scanner in, Trotinete trt) {
		String idTrot = in.next();
		
		if (trt.existsTrt == true && idTrot == trt.getIdTrot()) {
			if (trt.paradaTrt() == false) {
				System.out.println(trt.getRentNif() + ", " + trt.getRentNome());
			}
			else {
				System.out.println("Trotinete nao alugada.");
			}
		}
		else {
			System.out.println("Trotinete inexistente.");
		}
	}
	private static void processCarrSaldo(Scanner in, Cliente cl, Sistema s) {
		String nif = in.next();
		int amount = in.nextInt();
		
		if (cl.existsCl() == true && nif == cl.getNif()) {
			if(amount>0) {
				cl.carrSaldo(amount);
				s.adDespesas(amount);
				System.out.println("Carregamento efectuado.");
			}
			else {
				System.out.println("Valor invalido.");
			}
		}
		else {
			System.out.println("Cliente inexistente.");
		}
	}
	private static void processAlugar(Scanner in, Cliente cl, Trotinete trt) {
		String nif = in.next();
		String idTrot = in.nextLine();
		
		if (cl.existsCl() == true && nif == cl.getNif()) {
			if (trt.existsTrt == true && idTrot == trt.getIdTrot()) {
				
				cl.alugarCl(idTrot,trt.getMatricula());
				trt.alugarTrt(nif, cl.getNome());
				
				System.out.println("Aluguer efectuado com sucesso.");
			}
			else {
				System.out.println("Trotinete inexistente.");
			}
		}
		else {
			System.out.println("Cliente inexistente.");
		}
	}
	private static void processLibertar(Scanner in, Cliente cl, Trotinete trt, Sistema s) {
		String idTrot = in.next();
		int minutos = in.nextInt();
		
			if (trt.existsTrt == true && idTrot == trt.getIdTrot()) {
				if(trt.paradaTrt() == false) {
					if(minutos > 0) {
						
						trt.libertarTrt();
						if (minutos <= 60) {
							cl.libertarCl(minutos,PRECO_BASE);
							s.libertar(minutos,PRECO_BASE);
						}
						else {
							int excess = (minutos / 60);
							int res = (minutos % 60);
							int cost;
							
							cost = (PRECO_BASE * excess);
							
							if (res != 0) {
								cost += PRECO_BASE;
							}
							cl.libertarCl(minutos,cost);
							s.libertar(minutos,cost);
						}						
						System.out.println("Aluguer terminado.");
					}
					else {
						System.out.println("Valor invalido.");
					}
				}
				else {
					System.out.println("Trotinete nao alugada.");
				}
			}
			else {
				System.out.println("Trotinete inexistente.");
			}
	}
	private static void processEstadoSistema(Scanner in, Sistema s) {
		
		in.nextLine();
		System.out.println("Estado actual: " + s.getAlugueres() + ", " + s.getTotalCentimos() + ", " + s.getAtrasos());
	}
}
