package shapes;

/**
 * Esta class representa uma forma geometrica e todos os metodos associados a
 * mesma.
 * 
 * @author Joao Soares N57609
 *
 */
public interface Shape {

	/**
	 * Devolve o tido da forma geometrica em questao.
	 * @return tipo da forma
	 */
	String getShape();
	/**
	 * Devolve o id da forma geometrica em questao.
	 * 
	 * @return id da shape
	 */
	String getId();

	/**
	 * Devolve a abcissa da forma geometrica em questao.
	 * 
	 * @return x - a abcissa
	 */
	int getX();

	/**
	 * Devolve a ordenada da forma geometrica em questao.
	 * 
	 * @return y - a ordenada
	 */
	int getY();

	/**
	 * Move a forma geometrica para a localizacao dada.
	 * 
	 * @param x - abcissa da nova localizacao
	 * @param y - ordenada da nova localizacao
	 */
	void move(int x, int y);
	
	/**
	 * Devolve a area ocupada pela forma geometrica em questao.
	 * @return area - area da forma geometrica
	 */
	float getArea();

}
