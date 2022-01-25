package social;

import java.util.*;

public class FanaticClass extends AbstractUser implements Fanatic {

    private List<String> loves;
    private List<String> hates;
    private List<String> topics;

    public FanaticClass(String id, List<String> loves, List<String> hates, List<String> topics) {
        this.id = id;
        this.loves = loves;
        this.hates = hates;
        this.topics = topics;

        this.kind = KindTypes.fanatic;

        this.posts = new ArrayList<>(MAX_SIZE);

        this.friendCount = 0;
        this.allCommentCount = 0;
    }

    @Override
    public int getOpinion(String topic) {
        if (loves.contains(topic))
            return 1;
        if (hates.contains(topic))
            return -1;
        return 0;
    }

    @Override
    public int getTopicIndex(String topic) {
        return topics.indexOf(topic);
    }
}