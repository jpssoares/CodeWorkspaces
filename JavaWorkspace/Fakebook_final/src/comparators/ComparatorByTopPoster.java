package comparators;

import social.User;

import java.util.Comparator;

public class ComparatorByTopPoster implements Comparator<User> {

    @Override
    public int compare(User u1, User u2) {

        int compIds = u1.getId().compareTo(u2.getId());

        if(u1.getPostCount() == u2.getPostCount()) {
            if (u1.getAllCommentCount() == u2.getAllCommentCount()) {
                return compIds;
            } else if (u1.getAllCommentCount() > u2.getAllCommentCount()) {
                return -1;
            } else {
                return 1;
            }
        } else if(u1.getPostCount() > u2.getPostCount()) {
            return -1;
        } else {
            return 1;
        }
    }
}