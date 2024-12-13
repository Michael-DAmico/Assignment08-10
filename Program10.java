/**
 * @author Christian Burke and Michael D'Amico
 * @version 12 December 2024
 */

package twitterpack;

import java.io.IOException;

public class Program10 {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("provide the path to the tweets file.");
            return;
        }

        String filePath = args[0];
        Tweet[] tweets = MyDataReader.readDataToArray(filePath, 80000);

        // Sort by Tweet ID 1 time
        MyQuickSort<Tweet> idSorter = new MyQuickSort<>();
        idSorter.setComparator(new TweetIDComparator());
        long startID = System.currentTimeMillis();
        idSorter.sort(tweets);
        long endID = System.currentTimeMillis();
        System.out.println("First run - Quicksort based on tweet ID took " + (endID - startID) + " milliseconds.");

        // Sort by DateTime
        MyQuickSort<Tweet> datetimeSorter = new MyQuickSort<>();
        datetimeSorter.setComparator(new DateTimeComparator());
        long startDateTime = System.currentTimeMillis();
        datetimeSorter.sort(tweets);
        long endDateTime = System.currentTimeMillis();
        System.out.println("Quicksort based on tweet DateTime took " + (endDateTime - startDateTime) + " milliseconds.");

        // Show the top 10 sorted tweets by DateTime
        System.out.println("Top-10 sorted tweets by DateTime:");
        for (int i = 0; i < 10 && i < tweets.length; i++) {
            Tweet tweet = tweets[i];
            System.out.println(tweet.getId() + " - " + tweet.getPostDateTime());
        }

        // Radix Sort based on ID
        MyRadixSort radixSorter = new MyRadixSort();
        long startRadix = System.currentTimeMillis();
        radixSorter.sort(tweets);
        long endRadix = System.currentTimeMillis();
        System.out.println("Radixsort based on tweet ID took " + (endRadix - startRadix) + " milliseconds.");
        
        // Sort by Tweet ID 2 time
        //MyQuickSort<Tweet> idSorter = new MyQuickSort<>();
        idSorter.setComparator(new TweetIDComparator());
        long startID2 = System.currentTimeMillis();
        idSorter.sort(tweets);
        long endID2 = System.currentTimeMillis();
        System.out.println("Second run - Quicksort based on tweet ID took " + (endID2 - startID2) + " milliseconds.");

    }
}
