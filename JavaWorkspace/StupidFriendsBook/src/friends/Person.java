package friends;

/**
 * Uma pessoa, com accoes associadas e personalidade consequente
 * 
 * @author Miguel Goulao / Adriano Lopes / Carla Ferreira
 *
 */

public interface Person {
	
	/**
	 * Verifica se a pessoa tem a accao <code>description</code>
	 * @param description - descricao da accao.
	 * @return devolve <code>true</code> se a pessoa tem a accao <code>description</code>
	 */
	boolean hasAction(String description);

	/**
	 * Acrescenta uma accao <code>description</code> a pessoa
	 * @pre !hasAction(descrition)
	 * @param description - descricao da accao.
	 */
	void addAction(String description);

	/**
	 * Vota na accao, incrementando os votos nos beneficios proprios 
	 * se <code>goodForPerson</code> for <code>true</code>, ou decrementando-os, 
	 * caso contrario, e fazendo o mesmo em relacao aos beneficios alheios, 
	 * com o argumento <code>goodForOthers</code>.
	 * @pre hasAction(descrition)
	 * @param description - descricao da accao a votar
	 * @param goodForPerson - <code>true</code>, se for benefico para o proprio, <code>false</code> caso contrario
	 * @param goodForOthers - <code>true</code>, se for benefico para os outros, <code>false</code> caso contrario
	 */
	void vote(String description, boolean goodForPerson,	boolean goodForOthers);

	/**
	 * Devolve o nome da pessoa
	 * @return o nome da pessoa.
	 */
	String getName();

	/**
	 * Devolve a personalidade dominante da pessoa.
	 * @return devolve um inteiro representando o traco de personalidade dominante, 
	 * com os valores <code>INTELIGENT</code>, <code>STUPID</code>, 
	 * <code>BANDIT</code>, ou <code>ANGEL</code>.
	 */
	int getPersonality();

	/**
	 * Devolve o valor da distancia dos votos acumulados a origem usando o 
	 * teorema de Pitagoras, sendo a distancia a hipotenusa e os valores 
	 * acumulados de beneficios proprios e alheios os catetos
	 * @return o valor calculado.
	 */
	double getBoredom();
}