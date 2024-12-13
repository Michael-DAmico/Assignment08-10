/**
 * @author Christian Burke and Michael D'Amico
 * @version 12 December 2024
 */

package twitterpack;

import java.util.ArrayList;
import java.util.List;

public class MyRadixSort {
    /**
     * Sorts an array of Tweet objects based on their ID using the Radix Sort algorithm.
     * The algorithm processes the digits of the ID starting from the least significant digit
     * to the most significant digit.
     *
     * @param tweets An array of Tweet objects to be sorted.
     */
    public void sort(Tweet[] tweets) {
        int maxID = 9999999; // Since ID is always a 7-digit number (2000000 to 2099999)
        List<Tweet>[] buckets = new ArrayList[10];

        for (int i = 0; i < 10; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int exp = 1; maxID / exp > 0; exp *= 10) {
            for (Tweet tweet : tweets) {
                int index = (tweet.getId() / exp) % 10;
                buckets[index].add(tweet);
            }
            for (List<Tweet> bucket : buckets) {
                bucket.clear();
            }
        }
    }
}

