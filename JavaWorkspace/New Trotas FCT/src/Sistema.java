
public class Sistema {
	private static final int PRECO_BASE = 100;
	private int alugueres, totalCentimos, atrasos;
	private int lastTotalCentimos, lastAtrasos;

	private Cliente cl = null;
	private Trotinete trt = null;
	
	public Sistema() {
		alugueres = 0;
		totalCentimos = 0;
		atrasos = 0;
	
	}
	public int adCliente(String nif, String email, String phone, String nome) {
		if(cl==null){
			cl = new Cliente (nif,email,phone,nome);
				return 1;}
		else {
			if (nif.equalsIgnoreCase(cl.getNif())) {
				return 0;}
			else {
				cl = new Cliente (nif,email,phone,nome);
				return 1;}
		}
	}
	public int remCliente(String nif) {
		if(cl!=null && nif.equalsIgnoreCase(cl.getNif())){
				if (cl.paradoCl()==true) {
					cl = null;
					return 0;
				}
				else {
					return 2;}
		}
		else {
			return 1;
			}
		}
	public int adTrot(String idTrot, String matricula) {
		if(trt==null || !idTrot.equalsIgnoreCase(trt.getIdTrot())){
			trt = new Trotinete (idTrot,matricula);
				return 0;}
		else {
				return 1;}
	}
	public int dadosCliente(String nif) {
		if(cl!=null && nif.equalsIgnoreCase(cl.getNif())) {
				return 0;}
		else {
				return 1;}
	}
	public int trot(String nif) {
		if(cl!=null && nif.equalsIgnoreCase(cl.getNif())) {
			if (cl.paradoCl()!=true) {
				return 0;}
			else {
				return 2;
			}
		}
		else {
			return 1;
		}
	}
	public int dadosTrot(String idTrot) {
		if(trt!=null && idTrot.equalsIgnoreCase(trt.getIdTrot())){
			return 0;
		}
		else {
			return 1;
		}
	}
	public int cliente(String idTrot) {
		if(trt!=null && idTrot.equalsIgnoreCase(trt.getIdTrot())){
			if (trt.paradaTrt()!=true) {
				return 0;
			}
			else {
				return 2;
			}
		}
		else {
			return 1;
		}
	}
	public int carrSaldo(String nif, int amount) {
		if(cl!=null && nif.equalsIgnoreCase(cl.getNif())) {
			if(amount>0) {
				cl.carrSaldo(amount);
				return 0;
			}
			else {
				return 1;
			}
		}
		else {
			return 2;
		}
	}
	public int alugar(String nif, String idTrot) {
		if(cl!=null && nif.equalsIgnoreCase(cl.getNif())) {
			if(trt!=null && idTrot.equalsIgnoreCase(trt.getIdTrot())){
				if(trt.paradaTrt() == true && trt.inactiva()==false) {
					if (cl.getBalance() >= 100) {
						cl.alugarCl();
						trt.alugarTrt();
						return 0;
					}
					else {
						return 4;
					}
				}
				else {
					return 3;
				}
			}
			else {
				return 2;
			}
		}
		else {
			return 1;
		}
	}
	public int libertar(String idTrot, int time) {
		if(time>0) {	
			if(trt!=null && idTrot.equalsIgnoreCase(trt.getIdTrot())){
				if(trt.paradaTrt() != true || trt.inactiva()==true) {
					alugueres += 1;
						
					trt.libertarTrt(time);
					if (time <= 60) {
						cl.libertarCl(time,PRECO_BASE);
						lastTotalCentimos = totalCentimos;
						totalCentimos += PRECO_BASE;
					}
					else {
						int excess = (time / 30) - 2;
						int res = (time % 30);
						int cost = PRECO_BASE + (PRECO_BASE * excess);
							
						if (res != 0) {
							cost += PRECO_BASE;
						}
						cl.libertarCl(time,cost);
						lastTotalCentimos = totalCentimos;
						totalCentimos += cost;
					}
					if (time > 60) {
						lastAtrasos = atrasos;
						atrasos += (time - 60);}
					return 0;
				}
				else {
					return 3;
				}
			}
			else {
				return 2;
			}
		}
		else {
			return 1;
		}
	}
	public int promocao(String nif) {
		if(cl!=null && nif.equalsIgnoreCase(cl.getNif())) {
			if(cl.paradoCl()==true) {
				if(cl.usouPromocao()==false) {
					cl.usarPromocao();
					trt.usarPromocao();
					usarPromocao();
					return 0;
				}
				else {
					return 3;
				}
			}
			else {
				return 2;
			}
		}
		else {
			return 1;
		}
	}
	public int desTrot(String idTrot) {
		if(trt!=null && idTrot.equalsIgnoreCase(trt.getIdTrot())){
			if(trt.paradaTrt()==true) {
				trt.desTrot();
				return 0;
			}
			else {
				return 2;
			}
		}
		else {
			return 1;
		}
	}
	public int reacTrot(String idTrot) {
		if(trt!=null && idTrot.equalsIgnoreCase(trt.getIdTrot())){
			if(trt.inactiva()==true) {
				trt.reacTrot();
				return 0;
			}
			else {
				return 2;
			}
		}
		else {
			return 1;
		}
	}
	public void usarPromocao() {
		alugueres -= 1;
		totalCentimos = lastTotalCentimos;
		atrasos = lastAtrasos;
	}
	public int getAlugueres() {
		return alugueres;
	}
	public int getTotalCentimos() {
		return totalCentimos;
	}
	public int getAtrasos() {
		return atrasos;
	}
	public String getNome() {
		return cl.getNome();
	}
	public String getNif() {
		return cl.getNif();
	}
	public String getEmail() {
		return cl.getEmail();
	}
	public String getPhone() {
		return cl.getPhone();
	}
	public String getIdTrot() {
		return trt.getIdTrot();
	}
	public String getMatricula() {
		return trt.getMatricula();
	}
	public String estado() {
		String e;
		if (trt.inactiva()==true) {
			e = "inactiva";
		}
		else if (trt.paradaTrt()==true) {
			e = "parada";
		}
		else {
			e = "alugada";
		}
		return e;
	}
	public int getBalance() {
		return cl.getBalance();
	}
	public int getNumRentsCl() {
		return cl.getNumRents();
	}
	public int getTotalRentTime() {
		return cl.getTotalRentTime();
	}
	public int getMaxRentTime(){
		return cl.getMaxRentTime();
	}
	public int getAverageRentTime() {
		return cl.getAverageRentTime(); 
	}
	public int getExpenses() {
		return cl.getExpenses();
	}
	public int getNumRentsTrt() {
		return trt.getNumRents();
	}
	public int getTotalTime() {
		return trt.getTotalTime();
	}
}
