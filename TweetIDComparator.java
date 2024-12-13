package twitterpack;

import java.util.Comparator;

public class TweetIDComparator implements Comparator<Tweet> {
    @Override
    public int compare(Tweet t1, Tweet t2) {
        return Integer.compare(t1.getId(), t2.getId());
    }
}
