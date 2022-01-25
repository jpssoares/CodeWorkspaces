package friends;

/**
 * Esta classe e utilizada para devolver elementos do array de posts, um de cada
 * vez.
 * 
 * @author Joao Soares N57609
 *
 */
public class PostIterator {

	private Post[] timeline;
	private int counter;
	private int nextPost;

	public PostIterator(Post[] timeline, int counter) {
		this.timeline = timeline;
		this.counter = counter;
		nextPost = 0;
	}

	public boolean hasNext() {
		return nextPost < counter;
	}

	public Post next() {
		return timeline[nextPost++];
	}
}
