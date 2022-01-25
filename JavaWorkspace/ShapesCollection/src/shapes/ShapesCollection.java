package shapes;

/**
 * Esta class representa a colecao de formas geometricas e todos os metodos
 * associados as mesmas.
 * 
 * @author Joao Soares N57609
 *
 */
public interface ShapesCollection {

	/**
	 * Consulta se uma dada forma geometrica esta registada no sistema.
	 * 
	 * @param id - identificador da forma geometrica
	 * @return <code>true<code> se houver uma forma na colecao com o id fornecido
	 */
	boolean hasShape(String id);

	/**
	 * Adiciona um novo circulo a colecao de formas.
	 * 
	 * @param id     - identificador do circulo
	 * @param x      - abcissa do centro do circulo
	 * @param y      - ordenada do centro do circulo
	 * @param radius - raio do circulo
	 */
	void addCircle(String id, int x, int y, int radius);

	/**
	 * Adiciona um novo retangulo a colecao de formas.
	 * 
	 * @param id     - identificador do retangulo
	 * @param x      - abcissa do centro do retangulo
	 * @param y      - ordenada do centro do retangulo
	 * @param height - altura do retangulo
	 * @param width  - largura do retangulo
	 */
	void addRectangle(String id, int x, int y, int height, int width);

	/**
	 * Verifica se existem uma ou mais formas geometricas registadas na colecao.
	 * 
	 * @return <code>true<code> se nao existir nenhuma forma na colecao
	 */
	boolean isEmpty();

	/**
	 * Move um das formas geometricas da colecao.
	 * 
	 * @param id - identificador da forma
	 * @param x  - abcissa do centro da forma
	 * @param y  - ordenada do centro da forma
	 */
	void move(String id, int x, int y);

	/**
	 * Obtem os dados disponiveis acerca de uma forma com o identificador dado.
	 * 
	 * @param id - identificador da forma
	 * @return Forma geometrica
	 */
	Shape getShape(String id);
	
	/**
	 * Devolve a forma geometrica com menor area.
	 * @return Forma geometrica com menor area
	 */
	Shape getSmallestShape();

	/**
	 * Devolve a lista de formas geometricas registadas.
	 * 
	 * @return lista de formas geometricas
	 */
	ShapeIterator listShapes();
}
