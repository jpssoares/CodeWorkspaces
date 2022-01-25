package shapes;

/**
 * Uma das duas formas geometricas com as suas respetivas variaveis e metodos.
 * 
 * @author Joao Soares N57609
 *
 */
public class CircleClass implements Shape {

	private String shape = "CIRCLE";
	private String id;
	private int x, y, radius;

	public CircleClass(String id, int x, int y, int radius) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.radius = radius;
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
	 * Devolve o raio do circulo.
	 * 
	 * @return radius - raio do circulo
	 */
	public int getRadius() {
		return radius;
	}

	@Override
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public float getArea() {
		float area;

		area = (float) (Math.PI * Math.pow(radius, 2));

		return area;
	}

}
