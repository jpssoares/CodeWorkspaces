package comparators;

import java.util.Comparator;
import java.lang.Integer;
import social.Post;

public class ComparatorByTopicPosts implements Comparator<Post> {

    @Override
    public int compare(Post p1, Post p2) {
        Integer firstCommentCount = p1.getCommentCount();
        Integer secondCommentCount = p2.getCommentCount();

        int compCommentCount = - (firstCommentCount.compareTo(secondCommentCount));

        if (compCommentCount == 0)
            compCommentCount = p1.getAuthor().compareTo(p2.getAuthor());

        if (compCommentCount == 0) {
            Integer id1 = p1.getPostId();
            Integer id2 = p2.getPostId();
            compCommentCount = - (id1.compareTo(id2));
        }
        return compCommentCount;
    }
}
