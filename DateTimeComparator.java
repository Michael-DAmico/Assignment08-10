package twitterpack;

import java.util.Comparator;

public class DateTimeComparator implements Comparator<Tweet> {
    @Override
    public int compare(Tweet tweet1, Tweet tweet2) {
        return tweet1.getPostDateTime().compareTo(tweet2.getPostDateTime());
    }
}
