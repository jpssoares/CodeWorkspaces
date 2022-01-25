package friends;

/**
 * Uma accao a associar a uma pessoa, considerando que esta pode ser benefica para si (ou nao)
 * e benefica para os outros (ou nao)
 * 
 * @author Miguel Goulao / Adriano Lopes / Carla Ferreira
 *
 */
public interface Action {
	/**
	 * Vota numa accao. Actualiza os valores acumulados da votacao.
	 * @param goodForPerson - <code>true</code>, se a accao for benefica para o proprio, 
	 * <code>false</code> caso contrario.
	 * @param goodForOthers  - <code>true</code>, se a accao for benefica para os outros, 
	 * <code>false</code> caso contrario.
	 */
	 void vote(boolean goodForPerson, boolean goodForOthers);
	
	/**
	 * Devolve a descricao de uma accao, que a identifica.
	 * @return a descricao da accao
	 */
	String getDescription();
	
	/**
	 * Devolve a diferenca entre o numero de pessoas que consideram a accao benefica para o proprio, 
	 * e prejudicial para o proprio
	 * @return os votos acumulados
	 */
	int getSelfBenefit();
	
	/**
	 * Devolve a diferenca entre o numero de pessoas que consideram a accao benefica para os outros, 
	 * e prejudicial para os outros
	 * @return os votos acumulados
	 */
	int getOtherBenefit();
	
}
