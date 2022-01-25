package social;

public interface Comment {

    /**
     * Return the user id of the author of the comment.
     *
     * @return user id
     */
    String getAuthor();

    /**
     * Return the positiveness of the comment.
     *
     * @return stance
     */
    String getStance();

    /**
     * return the comment message.
     *
     * @return message
     */
    String getMessage();

    /**
     * Return the post to which this comment is associated with.
     * @return post
     */
    Post getPost();
}
