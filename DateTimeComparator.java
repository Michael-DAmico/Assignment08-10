/**
 * @author Christian Burke and Michael D'Amico
 * @version 12 December 2024
 */

package twitterpack;

import java.util.Comparator;

public class DateTimeComparator implements Comparator<Tweet> {

    /**
     * Compares two {@link Tweet} objects based on their posting date and time.
     *
     * @param tweet1 the first {@link Tweet} to compare
     * @param tweet2 the second {@link Tweet} to compare
     * @return a negative integer, zero, or a positive integer as the posting date and time 
     *         of {@code tweet1} is earlier than, equal to, or later than {@code tweet2}.
     */
    @Override
    public int compare(Tweet tweet1, Tweet tweet2) {
        return tweet1.getPostDateTime().compareTo(tweet2.getPostDateTime());
    }
}
