package shapes;

/**
 * Uma das duas formas geometricas com as suas respetivas variaveis e metodos.
 * 
 * @author Joao Soares N57609
 *
 */
public class RectangleClass implements Shape {

	private String shape = "RECTANGLE";
	private String id;
	private int x, y, h, w;

	public RectangleClass(String id, int x, int y, int h, int w) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
	}

	@Override
	public String getShape() {
		return this.shape;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	/**
	 * Devolve a altura do retangulo.
	 * 
	 * @return height - altura do retangulo
	 */
	public int getHeight() {
		return h;
	}

	/**
	 * Devolve a largura do retangulo.
	 * 
	 * @return lenght - largura do retangulo
	 */
	public int getWidth() {
		return w;
	}

	@Override
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public float getArea() {
		float area;

		area = (float) (w * h);
		return area;
	}

}
