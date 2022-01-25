package friends;

/**
 * Uma accao a associar a uma pessoa, considerando que esta pode ser benefica para si (ou nao)
 * e benefica para os outros (ou nao)
 * @author Miguel Goulao / Adriano Lopes / Carla Ferreira
 *
 */
public class ActionClass implements Action {
	/**
	 * Descricao da accao
	 */
	private String description;
	
	
	/**
	 * Acumulador de votos sobre beneficios, ou prejuizos proprios.
	 */
	private int selfBenefitScore;
	
	/**
	 * Acumulador de votos sobre beneficios, ou prejuizos alheios.
	 */
	private int otherBenefitScore;
	
	/**
	 * Construtor de ActionClass. Inicialmente, uma accao ainda nao foi votada e tem a descricao passada por argumento.
	 * @param description - Descricao da accao, que serve para a identificar.
	 */
	public ActionClass(String description){
		this.description = description;
		selfBenefitScore = 0;
		otherBenefitScore = 0;
	}
	
	@Override
	public void vote(boolean goodForPerson, boolean goodForOthers){
		if (goodForPerson)
			selfBenefitScore++;
		else
			selfBenefitScore--;
		if (goodForOthers)
			otherBenefitScore++;
		else
			otherBenefitScore--;
	}
	
	@Override
	public String getDescription(){
		return description;
	}
	
	@Override
	public int getSelfBenefit(){
		return selfBenefitScore;
	}
	
	@Override
	public int getOtherBenefit(){
		return otherBenefitScore;
	}
}
