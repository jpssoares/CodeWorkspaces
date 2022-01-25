package social;

import java.util.ArrayList;
import java.util.Iterator;
import exceptions.*;

public interface DataBase {

    /**
     * Adds a new naive user.
     *
     * @param id of the user
     */
    void registerNaive(String id) throws UserAlreadyExistsException;

    /**
     * Adds a new liar user.
     *
     * @param id of the user
     */
    void registerLiar(String id) throws UserAlreadyExistsException;

    /**
     * Adds a new fanatic user.
     *
     * @param id of the user
     */
    void registerFanatic(String id, int topics, String list)
            throws UserAlreadyExistsException, RepeatedFanaticismException;

    /**
     * Adds a new self centered user.
     *
     * @param id of the user
     */
    void registerSelfCentered(String id) throws UserAlreadyExistsException;

    /**
     * Adds a new friend to a user.
     *
     * @param id1 - id of the first user
     * @param id2 - id of the second user
     */
    void addFriendship(String id1, String id2)
            throws UnexistingUserException, FriendshipAlreadyExistsException, SameUserFriendshipException;

    /**
     * Add a post to the array of posts made a given user.
     *
     * @param id      of the user
     * @param count   - number of hashtags
     * @param list    of hashtags
     * @param honesty of the message of the post
     * @param message of the post
     */
    void post(String id, int count, ArrayList<String> list, String honesty, String message)
            throws UnexistingUserException, InvalidHashtagsException, InadequateStanceException;

    /**
     * Add a comment to a post from a given user.
     *
     * @param id1     of the commenter
     * @param id2     of the author of the post
     * @param postId
     * @param stance  of the comment (positive/negative)
     * @param message of the comment
     */
    void commentPost(String id1, String id2, int postId, String stance, String message)
            throws UnexistingUserException, UnexistingFriendshipException, UnexistingPostException,
            ImpossibleCommentException, InvalidCommentStanceException;

    /**
     * List all registered users on Fakebook.
     *
     * @return registered users
     */
    Iterator<User> listUsers() throws UserListEmptyException;

    /**
     * Lists all the friends of a user.
     *
     * @param id of the user
     * @return list of users
     */
    Iterator<User> listFriends(String id) throws FriendListEmptyException, UnexistingUserException;

    /**
     * Return a list of all the posts by a given user.
     *
     * @param id of the user
     * @return list of posts
     * @throws UnexistingUserException
     * @throws PostsEmptyException
     */
    Iterator<Post> listUserPosts(String id) throws UnexistingUserException, PostsEmptyException;

    /**
     * Return a list of all the comments made by a given user.
     *
     * @param userId
     * @param topicId
     * @return list of comments
     */
    Iterator<Comment> getCommentsByUser(String userId, String topicId)
            throws UnexistingUserException, CommentsEmptyException;

    /**
     * Return a list of fanatics on a certain topic.
     *
     * @param fan - topic
     * @return list of fanatics
     */
    Iterator<User> topicFanatics(String fan) throws InvalidFanaticismException;

    /**
     * Return the list of posts on a given topic.
     *
     * @param topic
     * @return list of posts
     * @throws PostsEmptyException
     */
    Iterator<Post> getTopicPosts(String topic, int number) throws InvalidTopicException, InvalidNumberPostsException;

    /**
     * Return the list of top liars.
     *
     * @return list of liars
     * @throws PostsEmptyException
     */
    User getShameless() throws PostsEmptyShamelessException;

    /**
     * Return the post with the given id from the given user.
     *
     * @param userId
     * @param postId
     * @return posts
     * @throws UnexistingUserException
     * @throws UnexistingPostException
     */
    Post getPostIterate(String userId, int postId) throws UnexistingUserException, UnexistingPostException;

    /**
     * Return the post with the most comments.
     *
     * @return post
     * @throws PostsEmptyException
     */
    Post getPopularPost() throws PostsEmptyPopularException;

    /**
     * Return the user with the given id.
     *
     * @param id of the user
     * @return user
     */
    User getUser(String id);

    /**
     * Return the user with the most posts.
     *
     * @return user
     * @throws UserListEmptyException
     */
    User getTopPoster() throws PostsEmptyTopPosterException;

    /**
     * Return the user with the higher percentage of commented posts.
     *
     * @return user
     * @throws UserListEmptyException
     */
    User getResponsive() throws PostsEmptyResponsiveException;

    /**
     * Checks if a post has already been added to the system.
     *
     * @param id     of the author
     * @param idPost - id of the post
     * @return <code>true<code> if there is a post with the given id
     */
    boolean hasPost(String id, int idPost);

    /**
     * Checks if the two given users are already friends.
     *
     * @param id1 - id of the first user
     * @param id2 - id of the second user
     * @return <code>true<code> if the two user are already friends
     */
    boolean hasFriendship(String id1, String id2);

    /**
     * Checks if a user has already been added to the system.
     *
     * @param id of the user
     * @return<code>true<code> if there is a user with the given id
     */
    boolean hasUser(String id);
}
