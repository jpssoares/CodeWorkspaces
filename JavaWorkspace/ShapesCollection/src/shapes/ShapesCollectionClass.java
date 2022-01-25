package shapes;

/**
 * Esta class representa a colecao de formas geometricas e todos os metodos
 * associados as mesmas.
 * 
 * @author Joao Soares N57609
 *
 */
public class ShapesCollectionClass implements ShapesCollection {

	private static final int DEFAULT = 100;
	/**
	 * O vetor de formas geometricas.
	 */
	private Shape[] shapes;

	/**
	 * O numero de formas geometricas atual.
	 */
	private int counter;

	public ShapesCollectionClass() {
		shapes = new Shape[DEFAULT];
		counter = 0;
	}

	@Override
	public boolean hasShape(String id) {
		if (!isEmpty()) {
			for (int i = 0; i < counter; i++) {
				if (shapes[i].getId().equals(id)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void addCircle(String iD, int x, int y, int radius) {
		if (isFull()) {
			resize();
		}
		shapes[counter] = new CircleClass(iD, x, y, radius);
		counter++;
	}

	@Override
	public void addRectangle(String iD, int x, int y, int height, int width) {
		if (isFull()) {
			resize();
		}
		shapes[counter] = new RectangleClass(iD, x, y, height, width);
		counter++;
	}

	@Override
	public boolean isEmpty() {
		return (counter == 0);
	}

	@Override
	public void move(String iD, int x, int y) {
		getShape(iD).move(x, y);
	}

	@Override
	public Shape getShape(String id) {
		for (int i = 0; i < counter; i++) {
			if (shapes[i].getId().equals(id)) {
				return shapes[i];
			}
		}
		return null;
	}

	@Override
	public ShapeIterator listShapes() {
		return new ShapeIterator(shapes, counter);
	}

	@Override
	public Shape getSmallestShape() {
		float small = 0;
		int pos = -1;

		for (int i = 0; i < counter; i++) {
			if (small == 0 || small >= shapes[i].getArea()) {
				small = shapes[i].getArea();
				pos = i;
			}
		}
		return shapes[pos];
	}

	// Metodos auxiliares
	/**
	 * Para duplicar o tamanho do vector.
	 */
	private void resize() {
		Shape[] tmp = new Shape[2 * counter];
		for (int i = 0; i < counter; i++)
			tmp[i] = shapes[i];
		shapes = tmp;
	}

	/**
	 * Verificar se existe espaco no vetor das formas geometricas.
	 * 
	 * @return <code>true<code> se estiver cheio
	 */
	private boolean isFull() {
		return (counter == shapes.length);
	}

}
