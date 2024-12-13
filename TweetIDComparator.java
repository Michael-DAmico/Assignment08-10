/**
 * @author Christian Burke and Michael D'Amico
 * @version 12 December 2024
 */

package twitterpack;

import java.util.Comparator;

public class TweetIDComparator implements Comparator<Tweet> {

    /**
     * Compares two {@link Tweet} objects based on their unique ID.
     *
     * @param t1 the first {@link Tweet} to compare
     * @param t2 the second {@link Tweet} to compare
     * @return a negative integer, zero, or a positive integer as the ID of {@code t1} 
     *         is less than, equal to, or greater than the ID of {@code t2}.
     */
    @Override
    public int compare(Tweet t1, Tweet t2) {
        return Integer.compare(t1.getId(), t2.getId());
    }
}
