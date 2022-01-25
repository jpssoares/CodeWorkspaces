import java.util.Scanner;
import shapes.*;

/**
 * Programa principal para a aplicacao ShapesCollection
 * 
 * @author Carla Ferreira / Joana Parreira / Miguel Goulao / Pedro Campones
 * 
 */
public class Main {

	/**
	 * Comandos do utilizador
	 */
	private static final String ADD_CIRCLE = "CIRCLE";
	private static final String ADD_RECTANGLE = "RECTANGLE";
	private static final String LIST = "LIST";
	private static final String MOVE = "MOVE";
	private static final String MINAREA = "MINAREA";
	private static final String EXIT = "EXIT";

	/**
	 * Feedback dado pelo programa
	 */	
	private static final String ID_EXISTS = "Identifier already exists.\n";
	private static final String ADDED_CIRCLE = "A new circle was added.\n";
	private static final String ADDED_RECTANGLE = "A new rectangle was added.\n";
	private static final String IS_EMPTY = "Empty collection.\n";
	private static final String ALL_SHAPES = "All shapes in the collection:";
	private static final String ID_NOT_EXIST = "Identifier does not exist.\n";
	private static final String MOVED_SHAPE = "Shape was moved.\n";
	private static final String EXITING = "Exiting...\n";

	/**
	 * Programa principal. Invoca interpretador de comandos.
	 * @param args - argumentos para execucao da aplicacao. Nao sao usados, neste programa.
	 */
	public static void main(String[] args) {
		Main.commands();
	}

	/**
	 * Interpretador de comandos.
	 */
	private static void commands() {
		/*************************************************************/
		/*** Criacao do objecto                                    ***/
		/*** Assume-se que existe a Interface ShapesCollection     ***/
		/*** que 'e implementada pela classe ShapesCollectionClass ***/
		/*************************************************************/
		ShapesCollection shapes = new ShapesCollectionClass();
		
		Scanner input = new Scanner(System.in);
		String command = input.next().toUpperCase();
		
		while (!command.equals(Main.EXIT)) {
			switch (command) {
			case Main.ADD_CIRCLE:
				addCircle(input,shapes);
				break;
			case Main.ADD_RECTANGLE:
				addRectangle(input,shapes);
				break;
			case Main.LIST:
				listShapes(shapes);
				break;
			case Main.MOVE:
				move(input,shapes);
				break;
			case Main.MINAREA:
				minArea(shapes);
				break;
			default:
				break;
			}
			command = input.next().toUpperCase();
		}
		System.out.println(EXITING);
	}

	/**
	 * Adiciona um circulo 'a coleccao de formas geometricas, 
	 * caso o identificador dado nao exista. Se ja existir, nao faz nada.
	 * @param in - o input de onde os dados vao ser lidos.
	 * @param shapes - o ShapesCollection no qual se pretende adicionar o circulo.
	 */
	private static void addCircle(Scanner in, ShapesCollection shapes) {
		String ID = in.next();
		int x = in.nextInt();
		int y = in.nextInt();
		int radius = in.nextInt(); in.nextLine();

		if (shapes.hasShape(ID))
			System.out.println(Main.ID_EXISTS);
		else {
			shapes.addCircle(ID, x, y, radius);
			System.out.println(Main.ADDED_CIRCLE);
		}
	}

	/**
	 * Adiciona um rectangulo 'a coleccao de formas geometricas, 
	 * caso o identificador dado nao exista. Se ja existir, nao faz nada.
	 * @param in - o input de onde os dados vao ser lidos.
	 * @param shapes - o ShapesCollection no qual se pretende adicionar o rectangulo.
	 */
	private static void addRectangle(Scanner in, ShapesCollection shapes) {
		String ID = in.next();
		int x = in.nextInt();
		int y = in.nextInt();
		int height = in.nextInt(); 
		int width = in.nextInt(); in.nextLine();

		if (shapes.hasShape(ID))
			System.out.println(Main.ID_EXISTS);
		else {
			shapes.addRectangle(ID, x, y, height, width);
			System.out.println(Main.ADDED_RECTANGLE);
		}
	}

	/**
	 * Lista todas as formas geometricas.
	 * @param shapes - o ShapesCollection sobre qual se pretende efectuar a listagem.
	 */
	private static void listShapes(ShapesCollection shapes) {
		if (shapes.isEmpty())
			System.out.println(Main.IS_EMPTY);
		else {
			// A COMPLETAR
			}
	}

	/**
	 * Move uma forma geometrica, caso o identificador dado exista. 
	 * Se nao existir, nao faz nada.
	 * @param in - o input de onde os dados vao ser lidos.
	 * @param shapes - o ShapesCollection no qual se pretende mover a forma geometrica.
	 */
	private static void move(Scanner in, ShapesCollection shapes) {
		String ID = in.next();
		int x = in.nextInt();
		int y = in.nextInt(); in.nextLine();

		if (!shapes.hasShape(ID))
			System.out.println(Main.ID_NOT_EXIST);
		else {
			shapes.move(ID, x, y);
			System.out.println(Main.MOVED_SHAPE);
		}
	}

	/**
	 * Apresenta a forma geomerica com menor a area.
	 * @param shapes - o ShapesCollection no qual se pretende determinar a forma 
	 * geometrica com a menor area.
	 */
	private static void minArea(ShapesCollection shapes) {
		if (shapes.isEmpty())
			System.out.println(Main.IS_EMPTY);
		else {
			// A COMPLETAR
		}
	}
}