package social;

public class CommentClass implements Comment {

    private String author, stance, message;
    private Post originalPost;
    public CommentClass(String author, String stance, String message, Post post) {
        this.author = author;
        this.message = message;
        this.stance = stance;
        this.originalPost = post;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getStance() {
        return stance;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Post getPost() {
        return originalPost;
    }
}
