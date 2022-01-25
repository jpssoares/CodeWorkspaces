package social;

import exceptions.CommentsEmptyException;

import java.util.*;

public interface Post {

    /**
     * Return the honest of the post.
     *
     * @return honest/fake
     */
    String getHonesty();

    /**
     * Return the message of the post.
     *
     * @return message
     */
    String getMessage();

    /**
     * Return the user id of the author of the post.
     *
     * @return
     */
    String getAuthor();

    /**
     * Return a list of all the topics associated with the post.
     *
     * @return topics
     */
    List<String> getTopics();

    /**
     * Return list of all the comments from this post.
     *
     * @return list of comments
     */
    Iterator<Comment> listComments() throws CommentsEmptyException;

    /**
     * Return the number of comments of this post.
     *
     * @return number of comments
     */
    int getCommentCount();

    /**
     * Return the post id.
     *
     * @return post id
     */
    int getPostId();

    /**
     * Return the last comment from the post.
     *
     * @return comment
     */
    Comment getLastComment();

    /**
     * Add a new comment to the post.
     *
     * @param c - comment
     */
    void addComment(Comment c);
}
