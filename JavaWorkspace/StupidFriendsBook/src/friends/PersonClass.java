/**
 * 
 */
package friends;

/**
 * Uma pessoa, com accoes associadas e personalidade consequente
 * @author Miguel Goulao / Adriano Lopes / Carla Ferreira
 *
 */
public class PersonClass implements Person {
	/**
	 * Dimensao do vector de accoes, por omissao.
	 */
	private static final int DEFAULT = 20;
	
	
	/**
	 * Nome da pessoa.
	 */
	private String name;
	
	/**
	 * Accoes da pessoa. 
	 */
	private Action[] myActions;
	
	
	/**
	 * Contador de accoes dessa pessoa.
	 */
	private int counter;
	
	public PersonClass(String name) {
		this.name = name;
		myActions = new Action[DEFAULT];
		counter = 0;
	}

	@Override
	public void addAction(String description) {
		if (counter == myActions.length)  // Se necessario, duplicar tamanho do vector.
			resize();
		myActions[counter++] = new ActionClass(description);
	}
	
	/**
	 * Metodo auxiliar para duplicar o tamanho do vector. 
	 */
	private void resize() {
		Action[] tmp = new Action[2*counter];
		for (int i = 0; i < counter; i++)
			tmp[i] = myActions[i];
		myActions = tmp;
	}
	
	@Override
	public void vote(String description, boolean goodForPerson, boolean goodForOthers) {
		myActions[indexOf(description)].vote(goodForPerson, goodForOthers);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public int getPersonality(){
		int personality;
		int countSelf = 0;
		int countOthers = 0;
		// Calcular os somatorios de beneficios proprios e alheios para todas as accoes da pessoa.
		for (int i = 0; i < counter; i++){
			countSelf += myActions[i].getSelfBenefit();
			countOthers += myActions[i].getOtherBenefit();
		}
		// Atribuir a constante de personalidade, segundo as regras estipuladas no enunciado do problema:
		// Bom para o proprio e para os outros - Inteligente
		// Bom para o proprio, mas mau para os outros - Bandido
		// Mau para o proprio, mas bom para os outros - Anjinho
		// Mau para o proprio e para os outros - Estupido  (o valor por omissao)
		if (countSelf >= 0)
			if (countOthers >= 0)
				personality = StupidFriendsBook.INTELLIGENT;
			else
				personality = StupidFriendsBook.BANDIT;
		else
			if (countOthers >= 0)
				personality = StupidFriendsBook.ANGEL;
			else
				personality = StupidFriendsBook.STUPID;
		
		return personality;
	}
	
	@Override
	public double getBoredom() {
		// Resultado a devolver e o comprimento da hipotenusa do triangulo cujos catetos
		// sao os valores acumulados de beneficios proprios e alheios.
		int countSelf = 0;
		int countOthers = 0;
		for (int i = 0; i < counter; i++){
			countSelf += myActions[i].getSelfBenefit();
			countOthers += myActions[i].getOtherBenefit();
		}
		return Math.sqrt(countSelf*countSelf + countOthers*countOthers);
	}
	
	/**
	 * Devolve o indice da accao com a descricao <code>description</code>, ou -1, se accao nao existir.
	 * @param description - descricao da accao.
	 * @return O indice da accao, ou -1, se accao nao existir.
	 */
	private int indexOf(String description) {
		int result = -1;
		int i = 0;
		while (i < counter && result == -1) {
			if (myActions[i].getDescription().equals(description))
				result = i;
			else
				i++;
		}
		return result;
	}

	@Override
	public boolean hasAction(String description) {
		return indexOf(description) != -1;
	}
}
