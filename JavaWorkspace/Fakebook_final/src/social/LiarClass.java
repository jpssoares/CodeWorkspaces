package social;

import java.util.ArrayList;

public class LiarClass extends AbstractUser implements Liar {

    private int lies;

    public LiarClass(String id) {
        this.id = id;
        this.kind = KindTypes.liar;

        this.posts = new ArrayList<>(MAX_SIZE);
        this.friendCount = 0;
        this.allCommentCount = 0;
    }

    @Override
    public String getLieCount() {
        return String.valueOf(lies);
    }
}
