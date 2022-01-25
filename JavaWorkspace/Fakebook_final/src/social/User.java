package social;

import exceptions.PostsEmptyPopularException;

import java.util.ArrayList;
import java.util.List;

public interface User {

    /**
     * Return the users id.
     *
     * @return user id
     */
    String getId();

    /**
     * Return the user kind.
     *
     * @return user kind
     */
    KindTypes getKind();

    /**
     * Return the number of friends the user has.
     *
     * @return number of friends
     */
    int getFriendCount();

    /**
     * Return the number of post the user has.
     *
     * @return number of posts
     */
    int getPostCount();

    /**
     * Return the number of comments.
     *
     * @return number o comments
     */
    int getAllCommentCount();

    /**
     * Return the number of lies made by a user on different comments and posts.
     *
     * @return number of lies.
     */
    int getLies();

    /**
     * Return the number of posts the user has commented on.
     *
     * @return comments on different posts.
     */
    int getCommentsByDifPosts();

    /**
     * Return the number of posts the user has access to.
     *
     * @return available posts.
     */
    int getAvailablePosts();

    /**
     * Add the most recently available posts to the user accesses.
     *
     * @param newPosts    new posts
     */
    void addAvailablePosts(int newPosts);

    /**
     * Add a post to the list of posts the user has commented.
     *
     */
    void addNewCommentDifPost();

    /**
     * Add one friend to the friend counter.
     */
    void addFriend();

    /**
     * Add one comment to the comment counter.
     */
    void commentPost(Comment c);

    /**
     * Add a post to the list of post made by the user.
     *
     * @param list    of posts
     * @param honesty of the message
     * @param message of the post
     */
    void addPost(ArrayList<String> list, String honesty, String message);

    /**
     * Return the posts made by the user.
     *
     * @return list of posts
     */
    List<Post> listPosts();

    /**
     * Return the post with the given id.
     * @param idPost
     * @return post
     */
    Post getPost(int idPost);

    /**
     * Check if the user has a post with the given id.
     *
     * @param id of the post
     * @return <code>true<code> if the user has a post with the given id
     */
    boolean hasPost(int id);
}
