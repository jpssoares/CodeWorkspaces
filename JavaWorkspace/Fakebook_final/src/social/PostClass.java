package social;

import exceptions.CommentsEmptyException;

import java.util.*;

public class PostClass implements Post {

    private String author, honesty, message;
    private ArrayList<String> topics;
    private int idPost;

    private List<Comment> comments;

    public PostClass(String id, ArrayList<String> list, String honesty, String message, int idPost) {
        this.author = id;
        this.honesty= honesty;
        this.message = message;
        this.topics = list;
        this.idPost = idPost+1;

        comments = new ArrayList<>();
    }

    @Override
    public String getHonesty() {
        return honesty;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getCommentCount() {
        return comments.size();
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public Iterator<Comment> listComments() throws CommentsEmptyException {
        if(comments.isEmpty())
            throw new CommentsEmptyException();

        return comments.iterator();
    }

    @Override
    public int getPostId() {
        return idPost;
    }

    @Override
    public void addComment(Comment c) {
        comments.add(c);
    }

    @Override
    public List<String> getTopics() {
        return topics;
    }

    @Override
    public Comment getLastComment() {
        return comments.get(comments.size() - 1);
    }
}