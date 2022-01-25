import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import social.*;
import exceptions.*;

public class Main {

    // Feedback given by the program

    // Success feedback
    private static final String REGISTERED_USER = "%s registered.\n";
    private static final String REGISTERED_FRIENDSHIP = "%s is friend of %s.\n";
    private static final String REGISTERED_POST = "%s sent a %s post to %d friends. Post id = %s.\n";
    private static final String REGISTERED_COMMENT = "Comment added!";
    private static final String EXIT_PROGRAM_MESSAGE = "Bye!";

    // Error feedback
    private static final String INVALID_USER_KIND = "%s is an invalid user kind!\n";
    private static final String UNKNOWN_COMMAND_MESSAGE = "Unknown command. Type help to see available commands.";

    // Enum that define commands
    private enum Command {
        REGISTER, USERS, ADDFRIEND, FRIENDS, POST, USERPOSTS, COMMENT, READPOST, COMMENTSBYUSER, TOPICFANATICS,
        TOPICPOSTS, POPULARPOST, TOPPOSTER, RESPONSIVE, SHAMELESS, HELP, EXIT, UNKNOWN_COMMAND
    }

    private static Command readCommand(Scanner in) {
        try {
            return Command.valueOf(in.next().toUpperCase());
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN_COMMAND;
        }
    }

    private static void invalidCommand(Scanner in) {
        in.nextLine();
        System.out.println(UNKNOWN_COMMAND_MESSAGE);
    }

    /**
     * Main program. Command interpreter.
     *
     * @param args - arguments for the program execution
     */
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            DataBase dataBase = new DataBaseClass();
            Command cmd = readCommand(in);

            while (!cmd.equals(Command.EXIT)) {
                switch (cmd) {
                    case REGISTER:
                        setRegister(in, dataBase);
                        break;
                    case USERS:
                        setUsers(dataBase);
                        break;
                    case ADDFRIEND:
                        setAddFriend(in, dataBase);
                        break;
                    case FRIENDS:
                        setFriends(in, dataBase);
                        break;
                    case POST:
                        setPost(in, dataBase);
                        break;
                    case USERPOSTS:
                        setUserPosts(in, dataBase);
                        break;
                    case COMMENT:
                        setComment(in, dataBase);
                        break;
                    case READPOST:
                        setReadPost(in, dataBase);
                        break;
                    case COMMENTSBYUSER:
                        setCommentsByUser(in, dataBase);
                        break;
                    case TOPICFANATICS:
                        setTopicFanatics(in, dataBase);
                        break;
                    case TOPICPOSTS:
                        setTopicPosts(in, dataBase);
                        break;
                    case POPULARPOST:
                        setPopularPost(dataBase);
                        break;
                    case TOPPOSTER:
                        setTopPoster(dataBase);
                        break;
                    case RESPONSIVE:
                        setResponsive(dataBase);
                        break;
                    case SHAMELESS:
                        setShameless(dataBase);
                        break;
                    case HELP:
                        setHelp();
                        break;
                    default:
                        invalidCommand(in);
                        break;
                }
                cmd = readCommand(in);
            }
            System.out.println(EXIT_PROGRAM_MESSAGE);
        } finally {
        }
    }

    private static void setRegister(Scanner in, DataBase dataBase) {
        String kind = in.next().toLowerCase();
        String id = in.next() + in.nextLine();

        try {
            switch (kind) {
                case ("naive"):
                    dataBase.registerNaive(id);
                    System.out.printf(REGISTERED_USER, id);
                    break;
                case ("liar"):
                    dataBase.registerLiar(id);
                    System.out.printf(REGISTERED_USER, id);
                    break;
                case ("fanatic"):
                    tryFanaticSetRegister(in, dataBase, id);
                    break;
                case ("selfcentered"):
                    dataBase.registerSelfCentered(id);
                    System.out.printf(REGISTERED_USER, id);
                    break;
                default:
                    System.out.printf(INVALID_USER_KIND, kind);
            }
        } catch (UserAlreadyExistsException e1) {
            System.out.printf(e1.getMessage(), id);
        }
    }

    private static void tryFanaticSetRegister(Scanner in, DataBase dataBase, String id) {
        int tps = in.nextInt();
        String list = in.next() + in.nextLine();

        try {
            dataBase.registerFanatic(id, tps, list);
            System.out.printf(REGISTERED_USER, id);
        } catch (UserAlreadyExistsException e1) {
            System.out.printf(e1.getMessage(), id);
        } catch (RepeatedFanaticismException e2) {
            System.out.println(e2.getMessage());
        }
    }

    private static void setUsers(DataBase dataBase) {
        try {
            Iterator<User> it = dataBase.listUsers();
            while (it.hasNext()) {
                User u = it.next();
                System.out.printf("%s [%s] %d %d %d\n", u.getId(), u.getKind(), u.getFriendCount(), u.getPostCount(), u.getAllCommentCount());
            }

        } catch (UserListEmptyException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void setAddFriend(Scanner in, DataBase dataBase) {
        String id1 = in.next() + in.nextLine();
        String id2 = in.next() + in.nextLine();

        try {
            dataBase.addFriendship(id1, id2);
            System.out.printf(REGISTERED_FRIENDSHIP, id1, id2);

        } catch (UnexistingUserException e1) {
            if (!dataBase.hasUser(id1)) {
                System.out.printf(e1.getMessage(), id1);
            } else
                System.out.printf(e1.getMessage(), id2);
        } catch (FriendshipAlreadyExistsException e2) {
            System.out.printf(e2.getMessage(), id1, id2);
        } catch (SameUserFriendshipException e3) {
            System.out.printf(e3.getMessage(), id1, id2);
        }
    }

    private static void setFriends(Scanner in, DataBase dataBase) {
        String id = in.next() + in.nextLine();

        try {
            Iterator<User> it = dataBase.listFriends(id);
            while (it.hasNext()) {
                User u = it.next();
                System.out.print(u.getId());
                if (it.hasNext())
                    System.out.print(", ");
            }
            System.out.println(".");

        } catch (UnexistingUserException e1) {
            System.out.printf(e1.getMessage(), id);
        } catch (FriendListEmptyException e2) {
            System.out.printf(e2.getMessage(), id);
        }
    }

    private static void setPost(Scanner in, DataBase dataBase) {
        String id = in.next() + in.nextLine();
        int count = in.nextInt();
        ArrayList<String> list = new ArrayList<>();

        try {
            for (int i = 0; i < count; i++) {
                list.add(in.next());
            }
            String honesty = in.next();
            String message = in.next() + in.nextLine();

            dataBase.post(id, count, list, honesty, message);
            System.out.printf(REGISTERED_POST, id, honesty, dataBase.getUser(id).getFriendCount(), dataBase.getUser(id).getPostCount());

        } catch (UnexistingUserException e2) {
            System.out.printf(e2.getMessage(), id);
        } catch (InvalidHashtagsException e3) {
            System.out.println(e3.getMessage());
        } catch (InadequateStanceException e4) {
            System.out.println(e4.getMessage());
        }
    }

    private static void setUserPosts(Scanner in, DataBase dataBase) {
        String id = in.next() + in.nextLine();

        try {
            Iterator<Post> it = dataBase.listUserPosts(id);
            System.out.printf("%s posts:\n", id);
            int number = 1;
            while (it.hasNext()) {
                Post p = it.next();
                System.out.printf("%d. ", number);
                System.out.printf("[%s] %s [%d comments]\n", p.getHonesty(), p.getMessage(), p.getCommentCount());
                number ++;
            }

        } catch (UnexistingUserException e1) {
            System.out.printf(e1.getMessage(), id);
        } catch (PostsEmptyException e2) {
            System.out.printf(e2.getMessage(), id);
        }
    }

    private static void setComment(Scanner in, DataBase dataBase) {
        String id1 = in.next() + in.nextLine();
        String id2 = in.nextLine();
        int postId = in.nextInt();
        String stance = in.next();
        String message = in.nextLine();

        try {
            dataBase.commentPost(id1, id2, postId, stance, message);
            System.out.println(REGISTERED_COMMENT);

        } catch (UnexistingUserException e1) {
            if (!dataBase.hasUser(id1)) {
                System.out.printf(e1.getMessage(), id1);
            } else
                System.out.printf(e1.getMessage(), id2);
        } catch (UnexistingFriendshipException e2) {
            System.out.printf(e2.getMessage(), id1, postId, id2);
        } catch (UnexistingPostException e3) {
            System.out.printf(e3.getMessage(), id2, postId);
        } catch (ImpossibleCommentException e4) {
            System.out.printf(e4.getMessage(), id1);
        } catch (InvalidCommentStanceException e5) {
            System.out.println(e5.getMessage());
        }
    }

    private static void setReadPost(Scanner in, DataBase dataBase) {
        String userId = in.next() + in.nextLine();
        int postId = in.nextInt();
        in.nextLine();

        try {
            Post p = dataBase.getPostIterate(userId, postId);
            System.out.printf("[%s %s] %s\n", p.getAuthor(), p.getHonesty(), p.getMessage());
            Iterator<Comment> it = p.listComments();
            while (it.hasNext()) {
                Comment c = it.next();
                System.out.printf("[%s %s]%s\n", c.getAuthor(), c.getStance(), c.getMessage());
            }

        } catch (UnexistingUserException e1) {
            System.out.printf(e1.getMessage(), userId);
        } catch (UnexistingPostException e2) {
            System.out.printf(e2.getMessage(), userId, postId);
        } catch (CommentsEmptyException e3) {
            System.out.println(e3.getMessage());
        }
    }

    private static void setCommentsByUser(Scanner in, DataBase dataBase) {
        String userId = in.next() + in.nextLine();
        String topicId = in.nextLine();

        try {
            Iterator<Comment> it = dataBase.getCommentsByUser(userId, topicId);
            while (it.hasNext()) {
                Comment c = it.next();
                Post p = c.getPost();
                System.out.printf("[%s %s %s %s]%s\n", p.getAuthor(), p.getHonesty(), p.getPostId(), c.getStance(), c.getMessage());
            }

        } catch (UnexistingUserException e1) {
            System.out.printf(e1.getMessage(), userId);
        } catch (CommentsEmptyException e2) {
            System.out.println(e2.getMessage());
        }
    }

    private static void setTopicFanatics(Scanner in, DataBase dataBase) {
        String fan = in.next();

        try {
            Iterator<User> it = dataBase.topicFanatics(fan);
            while (it.hasNext()) {
                User f = it.next();
                System.out.print(f.getId());
                if (it.hasNext())
                    System.out.print(", ");
            }
            System.out.print(".\n");

        } catch (InvalidFanaticismException e) {
            System.out.printf(e.getMessage(), fan);
        }
    }

    private static void setTopicPosts(Scanner in, DataBase dataBase) {
        String topic = in.next();
        int num = in.nextInt();

        try {
            Iterator<Post> it = dataBase.getTopicPosts(topic, num);
            while (it.hasNext() && num > 0) {
                Post p = it.next();
                System.out.printf("%s %d %d: %s\n", p.getAuthor(), p.getPostId(), p.getCommentCount(), p.getMessage());
                num --;
            }

        } catch (InvalidTopicException e1) {
            System.out.printf(e1.getMessage(), topic);
        } catch (InvalidNumberPostsException e2) {
            System.out.println(e2.getMessage());
        }
    }

    private static void setPopularPost(DataBase dataBase) {
        try {
            Post p = dataBase.getPopularPost();
            System.out.printf("%s %s %s: %s\n", p.getAuthor(), p.getPostId(), p.getCommentCount(), p.getMessage());

        } catch (PostsEmptyPopularException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void setTopPoster(DataBase dataBase) {
        try {
            User u = dataBase.getTopPoster();
            System.out.printf("%s %s %s.\n", u.getId(), u.getPostCount(), u.getAllCommentCount());

        } catch (PostsEmptyTopPosterException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void setResponsive(DataBase dataBase) {
        try {
            User u = dataBase.getResponsive();
            System.out.printf("%s %s %s.\n", u.getId(), u.getCommentsByDifPosts(), u.getAvailablePosts());

        } catch (PostsEmptyResponsiveException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void setShameless(DataBase dataBase) {
        try {
            User u = dataBase.getShameless();
            System.out.printf("%s %s.\n", u.getId(), u.getLies());

        } catch (PostsEmptyShamelessException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Shows the available commands. This command does not require any arguments.
     */
    private static void setHelp() {
        System.out.println("register - registers a new user\n" + "users - lists all users\n" + "addfriend - adds a new friend\n" +
                "friends - lists the user friends\n" + "post - posts a new message\n" + "userposts - lists all posts by a user\n" +
                "comment - user comments on a post\n" + "readpost - prints detailed info on a post\n" +
                "commentsbyuser - shows all the comments by a user on a given post\n" + "topicfanatics - shows a list of fanatic users on a topic\n" +
                "topicposts - shows a list of posts on a given topic\n" + "popularpost - shows the most commented post\n" +
                "topposter - shows the user with more posts\n" + "responsive - shows the user with a higher percentage of commented posts\n" +
                "shameless - shows the top liars\n" + "help - shows the available commands\n" + "exit - terminates the execution of the program");
    }
}
