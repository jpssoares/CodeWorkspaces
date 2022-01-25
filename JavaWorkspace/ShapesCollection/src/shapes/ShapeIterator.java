package shapes;

/**
 * Esta classe e utilizada para devolver elementos do array de formas
 * goemetricas, uma de cada vez.
 * 
 * @author Joao Soares N57609
 *
 */

public class ShapeIterator {
	private Shape[] shapes;
	private int counter;
	private int nextShape;

	public ShapeIterator(Shape[] shapes, int counter) {
		this.shapes = shapes;
		this.counter = counter;
		nextShape = 0;
	}

	public boolean hasNext() {
		return nextShape < counter;
	}

	public Shape current() {
		return shapes[nextShape];
	}

	public void next() {
		nextShape++;
	}

}
