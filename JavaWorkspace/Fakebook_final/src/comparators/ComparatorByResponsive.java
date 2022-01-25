package comparators;

import social.User;
import java.util.Comparator;

public class ComparatorByResponsive implements Comparator<User> {

    @Override
    public int compare(User u1, User u2) {

        float percentageCommentsU1 = (u1.getCommentsByDifPosts() * 100.0f) / u1.getAvailablePosts();
        float percentageCommentsU2 = (u2.getCommentsByDifPosts() * 100.0f) / u2.getAvailablePosts();

        int compIds = u1.getId().compareTo(u2.getId());

        if(u1.getCommentsByDifPosts() > u2.getCommentsByDifPosts()) {
            return -1;
        }

        if (percentageCommentsU1 == percentageCommentsU2) {
            return compIds;
        } else {
            if (percentageCommentsU1 > percentageCommentsU2) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}