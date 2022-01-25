package social;

public interface Fanatic extends User {

    /**
     * Return the opinion of the user on a certain topic.
     *
     * @param topic of the post
     * @return 0, if the topic is not part of the users interest, 1, if the user
     *         loves the topic, -1, if the user hates the topic
     */
    int getOpinion(String topic);

    /**
     * Return the index of a given topic from the topics list.
     *
     * @param topic
     * @return index
     */
    int getTopicIndex(String topic);
}
