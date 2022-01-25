package social;

import java.util.ArrayList;

public class SelfCenteredClass extends AbstractUser {

    public SelfCenteredClass(String id) {
        this.id = id;
        this.kind = KindTypes.selfcentered;

        this.posts = new ArrayList<>(MAX_SIZE);
        this.friendCount = 0;
        this.allCommentCount = 0;
    }
}
