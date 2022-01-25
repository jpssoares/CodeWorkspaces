package comparators;

import social.User;

import java.util.Comparator;

public class ComparatorByShameless implements Comparator<User> {

    @Override
    public int compare(User u1, User u2) {

        int compId = u1.getId().compareTo(u2.getId());

        if(u1.getLies() == u2.getLies()) {
            if(u1.getPostCount() == u2.getPostCount()) {
                return compId;
            }
            else {
                if(u1.getPostCount() < u2.getPostCount()) {
                    return -1;
                }
                else {
                    return 1;
                }
            }
        } else {

            if(u1.getLies() > u2.getLies()) {
                return -1;
            }
            else {
                return 1;
            }
        }
    }
}