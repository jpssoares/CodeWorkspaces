package social;

import java.util.*;

public abstract class AbstractUser implements User {
    protected static final int MAX_SIZE = 50;

    protected ArrayList<Post> posts;

    protected KindTypes kind;
    protected String id;
    protected int friendCount, allCommentCount;

    private int commentsByDifPosts, lies, availablePosts;

    public String getId() {
        return id;
    }

    @Override
    public KindTypes getKind() {
        return kind;
    }

    @Override
    public int getLies() {
        return lies;
    }

    @Override
    public int getAvailablePosts() {
        return posts.size() + availablePosts;
    }

    @Override
    public int getFriendCount() {
        return friendCount;
    }

    @Override
    public int getPostCount() {
        return posts.size();
    }

    @Override
    public int getAllCommentCount() {
        return allCommentCount;
    }

    @Override
    public int getCommentsByDifPosts() {
        return commentsByDifPosts;
    }

    @Override
    public void addPost(ArrayList<String> list, String honesty, String message) {
        int idPost = posts.size();
        Post p = new PostClass(id, list, honesty, message, idPost);
        posts.add(p);

        if(honesty.equals("fake"))
            lies ++;
    }

    @Override
    public void addFriend() {
        friendCount += 1;
    }

    @Override
    public void commentPost(Comment c) {
        allCommentCount++;
        if(c.getStance().equals("negative") && c.getPost().getHonesty().equals("honest") ||
                c.getStance().equals("positive") && c.getPost().getHonesty().equals("fake")) {
            lies ++;
        }
    }

    @Override
    public void addNewCommentDifPost() {
        commentsByDifPosts += 1;
    }


    @Override
    public void addAvailablePosts(int newPosts) {
        availablePosts += newPosts;
    }

    @Override
    public List<Post> listPosts() {
        return posts;
    }

    @Override
    public Post getPost(int idPost) {
        try {
            Post p = posts.get(idPost - 1);
            return p;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public boolean hasPost(int id) {
        return (getPost(id) != null);
    }
}