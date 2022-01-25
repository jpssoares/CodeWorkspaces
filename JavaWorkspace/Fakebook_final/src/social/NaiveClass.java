package social;

import java.util.ArrayList;

public class NaiveClass extends AbstractUser {

    public NaiveClass(String id) {
        this.id = id;
        this.kind = KindTypes.naive;

        this.posts = new ArrayList<>(MAX_SIZE);
        this.friendCount = 0;
        this.allCommentCount = 0;
    }
}
