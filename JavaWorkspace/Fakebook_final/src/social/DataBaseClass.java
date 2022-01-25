package social;

import java.util.*;
import comparators.*;
import exceptions.*;

public class DataBaseClass implements DataBase {
    private static final int MAX_SIZE = 50;

    // Contains the registered users
    private Set<User> users;

    // Contains all the friendships
    private Map<User, List<User>> friends;

    // Contains users by topic
    private Map<String, List<User>> topicFanatics;

    // Contains posts by topic
    private Map<String, List<Post>> topicPosts;

    //
    private Map<User, Map<String, List<Comment>>> userCommentsPrimary;

    // Popular Post
    private Post popularPost;

    // Contains posts commented by a user
    private Map<User, List<Post>> postsCommentByUser;

    private int maxComments;
    private int minPostId;
    private int postsCounter;


    public DataBaseClass() {
        this.users = new HashSet<>(MAX_SIZE);

        this.friends = new HashMap<>(MAX_SIZE);

        this.topicFanatics = new HashMap<>(MAX_SIZE);

        this.topicPosts = new HashMap<>(MAX_SIZE);

        this.userCommentsPrimary = new HashMap<>(MAX_SIZE);

        this.postsCommentByUser = new HashMap<>(MAX_SIZE);

        this.maxComments = 0;
        this.minPostId = 0;
        this.postsCounter = 0;
    }

    @Override
    public void registerNaive(String id) throws UserAlreadyExistsException {
        User naiveUser = new NaiveClass(id);

        if (hasUser(id))
            throw new UserAlreadyExistsException();
        users.add(naiveUser);
    }

    @Override
    public void registerLiar(String id) throws UserAlreadyExistsException {
        User liarUser = new LiarClass(id);

        if (hasUser(id))
            throw new UserAlreadyExistsException();
        users.add(liarUser);
    }

    @Override
    public void registerFanatic(String id, int topics, String list) throws RepeatedFanaticismException, UserAlreadyExistsException {
        String[] array = list.split(" ");
        int len = array.length;

        List<String> loves = new ArrayList<>();
        List<String> hates = new ArrayList<>();
        List<String> topicList = new ArrayList<>();

        String love = "loves";
        String hate = "hates";

        for (int i = 0; i < len; i++) {
            if (array[i].equals(love)) {
                if (loves.contains(array[i + 1]) || hates.contains(array[i + 1]))
                    throw new RepeatedFanaticismException();

                loves.add(array[i + 1]);

            } else if (array[i].equals(hate)) {
                if (loves.contains(array[i + 1]) || hates.contains(array[i + 1]))
                    throw new RepeatedFanaticismException();

                hates.add(array[i + 1]);
            }
            topicList.add(array[i + 1]);
            i++;
        }

        User fanaticUser = new FanaticClass(id, loves, hates, topicList);

        if (hasUser(id))
            throw new UserAlreadyExistsException();

        users.add(fanaticUser);

        for (String s : topicList) {
            List<User> listUsers = topicFanatics.get(s);
            if (listUsers == null)
                listUsers = new ArrayList<>();
            listUsers.add(fanaticUser);
            topicFanatics.put(s, listUsers);
        }
    }

    @Override
    public void registerSelfCentered(String id) throws UserAlreadyExistsException {
        User selfCenteredUser = new SelfCenteredClass(id);

        if (hasUser(id))
            throw new UserAlreadyExistsException();
        users.add(selfCenteredUser);
    }

    @Override
    public void addFriendship(String id1, String id2) throws UnexistingUserException, FriendshipAlreadyExistsException, SameUserFriendshipException {
        if (!hasUser(id1) || !hasUser(id2))
            throw new UnexistingUserException();
        if (id1.equals(id2))
            throw new SameUserFriendshipException();
        if (hasFriendship(id1, id2))
            throw new FriendshipAlreadyExistsException();

        // Adds the second given id to the first given id friends list
        List<User> friend2 = friends.get(getUser(id1));

        if (friend2 == null) {
            friend2 = new LinkedList<>();
            friends.put(getUser(id1), friend2);
        }
        friend2.add(getUser(id2));
        getUser(id1).addFriend();

        getUser(id1).addAvailablePosts(getUser(id2).getPostCount());

        // Adds the first given id to the second given id friends list
        List<User> friend1 = friends.get(getUser(id2));

        if (friend1 == null) {
            friend1 = new LinkedList<>();
            friends.put(getUser(id2), friend1);
        }
        friend1.add(getUser(id1));
        getUser(id2).addFriend();

        getUser(id2).addAvailablePosts(getUser(id1).getPostCount());
    }

    @Override
    public Iterator<User> listUsers() throws UserListEmptyException {
        if (users.isEmpty())
            throw new UserListEmptyException();

        List<User> usersLocal = new ArrayList<>(users);
        usersLocal.sort(new ComparatorByAlphabet());

        return usersLocal.iterator();
    }

    @Override
    public Iterator<User> listFriends(String id) throws FriendListEmptyException, UnexistingUserException {
        if (!hasUser(id))
            throw new UnexistingUserException();

        List<User> friendsLocal = friends.get(getUser(id));

        if (friendsLocal == null)
            throw new FriendListEmptyException();

        friendsLocal.sort(new ComparatorByAlphabet());
        return friendsLocal.iterator();
    }

    @Override
    public boolean hasUser(String id) {
        return this.getUser(id) != null;
    }

    @Override
    public Iterator<Post> listUserPosts(String id) throws UnexistingUserException, PostsEmptyException {
        if (!hasUser(id))
            throw new UnexistingUserException();

        List<Post> postsLocal = getUser(id).listPosts();

        if (postsLocal.isEmpty())
            throw new PostsEmptyException();
        else
            return postsLocal.iterator();
    }

    @Override
    public Iterator<Comment> getCommentsByUser(String userId, String topicId) throws UnexistingUserException, CommentsEmptyException {
        if (!hasUser(userId))
            throw new UnexistingUserException();
        if (!userCommentsPrimary.containsKey(getUser(userId)))
            throw new CommentsEmptyException();

        Map<String, List<Comment>> userComments = userCommentsPrimary.get(getUser(userId));

        List<Comment> topicComments = userComments.get(topicId);

        if (topicComments == null)
            throw new CommentsEmptyException();

        if (topicComments.isEmpty())
            throw new CommentsEmptyException();

        return topicComments.iterator();
    }

    @Override
    public Iterator<User> topicFanatics(String fan) throws InvalidFanaticismException {
        List<User> listUsers = topicFanatics.get(fan);

        if (listUsers == null)
            throw new InvalidFanaticismException();

        listUsers.sort(new ComparatorByAlphabet());

        return listUsers.iterator();
    }

    @Override
    public Iterator<Post> getTopicPosts(String topic, int number) throws InvalidTopicException, InvalidNumberPostsException {
        List<Post> listPosts = topicPosts.get(topic);

        if (number < 1)
            throw new InvalidNumberPostsException();
        if (listPosts == null || listPosts.isEmpty())
            throw new InvalidTopicException();

        listPosts.sort(new ComparatorByTopicPosts());

        return listPosts.iterator();
    }

    @Override
    public Post getPopularPost() throws PostsEmptyPopularException {
        if(popularPost == null)
            throw new PostsEmptyPopularException();

        return popularPost;
    }

    @Override
    public User getTopPoster() throws PostsEmptyTopPosterException {
        if(postsCounter == 0)
            throw new PostsEmptyTopPosterException();

        List<User> usersLocal = new ArrayList<>(users);
        usersLocal.sort(new ComparatorByTopPoster());

        return usersLocal.get(0);
    }

    @Override
    public User getResponsive() throws PostsEmptyResponsiveException {
        List<User> usersLocal = new ArrayList<>(users);
        usersLocal.sort(new ComparatorByResponsive());

        if(usersLocal.isEmpty() || usersLocal.get(0).getCommentsByDifPosts() == 0)
            throw new PostsEmptyResponsiveException();

        return usersLocal.get(0);
    }

    @Override
    public User getShameless() throws PostsEmptyShamelessException {
        List<User> usersLocal = new ArrayList<>(users);
        usersLocal.sort(new ComparatorByShameless());

        if(usersLocal.isEmpty() || usersLocal.get(0).getLies() == 0)
            throw new PostsEmptyShamelessException();

        return usersLocal.get(0);
    }

    @Override
    public boolean hasFriendship(String id1, String id2) {
        return (friends.get(getUser(id1)) != null) && (friends.get(getUser(id1)).contains(getUser(id2)));
    }

    @Override
    public User getUser(String id) {
        for (User u : users)
            if (u.getId().equals(id))
                return u;
        return null;
    }

    @Override
    public void post(String id, int count, ArrayList<String> list, String honesty, String message) throws UnexistingUserException, InvalidHashtagsException, InadequateStanceException {
        if (!hasUser(id))
            throw new UnexistingUserException();
        if (count < 0)
            throw new InvalidHashtagsException();
        if (hasDuplicateElements(list))
            throw new InvalidHashtagsException();

        try {
            switch (getUserType(id)) {
                case fanatic:
                    postAsFanatic(id, count, list, honesty, message);
                    break;
                case liar:
                    postAsLiar(id, list, honesty, message);
                    break;
                case selfcentered:
                    postAsSelfCentered(id, list, honesty, message);
                    break;
                case naive:
                    postAsNaive(id, list, honesty, message);
                    break;
            }

            for(int i = 0; i < friends.get(getUser(id)).size(); i++ ) {
                friends.get(getUser(id)).get(i).addAvailablePosts(1);
            }

            Post p = getUser(id).getPost(getUser(id).getPostCount());
            addPostTopics(list, p);

            postsCounter ++;

        } catch (InadequateStanceException e) {
            throw new InadequateStanceException();
        }
    }

    private void addPostTopics(ArrayList<String> list, Post p) {
        for (String topic : list) {
            List<Post> posts = topicPosts.get(topic);
            if (posts == null)
                posts = new ArrayList<>();
            posts.add(p);
            topicPosts.put(topic, posts);
        }
    }

    private void postAsFanatic(String id, int count, ArrayList<String> list, String honesty, String message) throws InadequateStanceException {
        User fanaticUser = getUser(id);

        if ((honesty.equals("honest") && fanaticHatesTopics(fanaticUser, count, list)) || (honesty.equals("fake") && !fanaticHatesTopics(fanaticUser, count, list)))
            throw new InadequateStanceException();

        fanaticUser.addPost(list, honesty, message);
    }

    private void postAsLiar(String id, ArrayList<String> list, String honesty, String message) throws InadequateStanceException {
        User liarUser = getUser(id);

        if (honesty.equals("honest"))
            throw new InadequateStanceException();

        liarUser.addPost(list, honesty, message);
    }

    private void postAsSelfCentered(String id, ArrayList<String> list, String honesty, String message) {
        User selfCenteredUser = getUser(id);

        selfCenteredUser.addPost(list, honesty, message);
    }

    private void postAsNaive(String id, ArrayList<String> list, String honesty, String message) {
        User naiveUser = getUser(id);

        naiveUser.addPost(list, honesty, message);
    }

    @Override
    public void commentPost(String id1, String id2, int postId, String stance, String message) throws UnexistingUserException, UnexistingFriendshipException, UnexistingPostException, ImpossibleCommentException, InvalidCommentStanceException {
        if (!hasUser(id1) || !hasUser(id2))
            throw new UnexistingUserException();
        if (!hasFriendship(id1, id2) && !id1.equals(id2))
            throw new UnexistingFriendshipException();
        if (!hasPost(id2, postId))
            throw new UnexistingPostException();
        if (getUser(id1).getKind().equals(KindTypes.selfcentered) && !id1.equals(id2))
            throw new ImpossibleCommentException();

        try {
            switch (getUserType(id1)) {
                case fanatic:
                    postCommentAsFanatic(id1, id2, postId, stance, message);
                    break;
                case liar:
                    postCommentAsLiar(id1, id2, postId, stance, message);
                    break;
                default:
                    postCommentAsNaiveOrSelfCentered(id1, id2, postId, stance, message);
                    break;
            }

        } catch (InvalidCommentStanceException e) {
            throw new InvalidCommentStanceException();
        }
    }

    private void postCommentAsLiar(String id1, String id2, int postId, String stance, String message) throws InvalidCommentStanceException {
        if (!isHonestPost(id2, postId) && !isPositiveComment(stance))
            throw new InvalidCommentStanceException();

        if (isHonestPost(id2, postId) && isPositiveComment(stance))
            throw new InvalidCommentStanceException();

        addCommentPost(id1, id2, postId, stance, message);
    }

    private void postCommentAsFanatic(String id1, String id2, int postId, String stance, String message) throws InvalidCommentStanceException {
        Post p = getPost(id2, postId);
        User u = getUser(id1);

        ArrayList<String> list = new ArrayList<>(p.getTopics());

         if(!isHonestPost(id2, postId) && (!isPositiveComment(stance) && fanaticHatesTopics(u, list.size(), list) ||
                 isPositiveComment(stance) && fanaticLovesTopics(u, list.size(), list)) ||
                 isHonestPost(id2, postId) && (isPositiveComment(stance) && !fanaticHatesTopics(u, list.size(), list) ||
                         !isPositiveComment(stance) && fanaticLovesTopics(u, list.size(), list)))
             throw new InvalidCommentStanceException();

        addCommentPost(id1, id2, postId, stance, message);
    }

    private void postCommentAsNaiveOrSelfCentered(String id1, String id2, int postId, String stance, String message) throws InvalidCommentStanceException {
        if (!isPositiveComment(stance))
            throw new InvalidCommentStanceException();

        addCommentPost(id1, id2, postId, stance, message);
    }

    private void addCommentPost(String id1, String id2, int postId, String stance, String message) {
        Post post = getPost(id2, postId);
        User commenter = getUser(id1);
        Comment newComment = new CommentClass(id1, stance, message, post);

        commenter.commentPost(newComment);
        post.addComment(newComment);
        addUserComment(commenter, newComment, post.getTopics());
        calculatePopularPost(post);

        List<Post> posts = postsCommentByUser.get(commenter);

        if(posts != null && !posts.contains(post)) {
            commenter.addNewCommentDifPost();
        } else if (posts == null) {
            posts = new LinkedList<>();
            postsCommentByUser.put(commenter, posts);
            commenter.addNewCommentDifPost();
        }
        posts.add(post);
    }

    @Override
    public Post getPostIterate(String userId, int postId) throws UnexistingUserException, UnexistingPostException {
        if(!hasUser(userId))
            throw new UnexistingUserException();
        if(!hasPost(userId, postId))
            throw new UnexistingPostException();

        return getUser(userId).getPost(postId);
    }

    @Override
    public boolean hasPost(String id, int idPost) {
        return getUser(id).hasPost(idPost);
    }

    // Auxiliary methods

    private void addUserComment(User commenter, Comment comment, List<String> list) {
        Map<String, List<Comment>> userCommentsSecondary = userCommentsPrimary.get(commenter);

        if (userCommentsSecondary == null)
            userCommentsSecondary = new HashMap<>();

        for (String topic : list) {
            List<Comment> comments = userCommentsSecondary.get(topic);

            if (comments == null) {
                comments = new ArrayList<>();
            }
            comments.add(comment);
            userCommentsSecondary.put(topic, comments);
            userCommentsPrimary.put(commenter, userCommentsSecondary);
        }
    }

    private KindTypes getUserType(String id) {
        return getUser(id).getKind();
    }

    private static boolean hasDuplicateElements(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++)
            for (int j = i + 1; j < list.size(); j++)
                if (j != i && list.get(j).equals(list.get(i)))
                    return true;
        return false;
    }

    private boolean fanaticLovesTopics(User f, int count, ArrayList<String> list) {
        for (int i = 0; i < count; i++) {
            if (((Fanatic) f).getOpinion(list.get(i)) > 0) {
                return true;
            }
        }
        return false;
    }

    private boolean fanaticHatesTopics(User f, int count, ArrayList<String> list) {
        for (int i = 0; i < count; i++) {
            if (((Fanatic) f).getOpinion(list.get(i)) < 0) {
                return true;
            }
        }
        return false;
    }

    private boolean isHonestPost(String id, int postId) {
        return getPost(id, postId).getHonesty().equals("honest");
    }

    private boolean isPositiveComment(String stance) {
        return stance.equals("positive");
    }

    private Post getPost(String userId, int postId) {
        return getUser(userId).getPost(postId);
    }

    private void calculatePopularPost(Post post) {
        if (popularPost == null) {
            popularPost = post;
            minPostId = post.getPostId();
            maxComments = post.getCommentCount();

        } else if (post.getCommentCount() > maxComments) {
            maxComments = post.getCommentCount();
            popularPost = post;

        } else if (post.getCommentCount() == maxComments) {
            int comp = post.getAuthor().compareTo(popularPost.getAuthor());
            if (comp == 0) {
                if (post.getPostId() < minPostId) {
                    minPostId = post.getPostId();
                    popularPost = post;
                }
            } else if (comp < 0) {
                popularPost = post;
            }
        }
    }
}