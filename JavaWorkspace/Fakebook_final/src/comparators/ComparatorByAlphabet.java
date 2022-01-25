package comparators;

import social.User;

import java.util.Comparator;

public class ComparatorByAlphabet implements Comparator<User> {

    @Override
    public int compare(User u1, User u2) {

        return u1.getId().compareTo(u2.getId());
    }
}
