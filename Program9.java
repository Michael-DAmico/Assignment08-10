package twitterpack;

import java.io.IOException;
import java.util.HashMap;

public class Program9 {

	public static void main(String[] args) {
		// Ensure correct number of arguments
        if (args.length != 1) {
            System.out.println("Usage: java program9 <tweets_train.tsv>");
            return;
        }

        String filePath = args[0];

        // HashMap to store user IDs and their corresponding heaps
        HashMap<String, MyHeap<Tweet>> userTweetsMap;

        try {
            // Measure time to build the data structure
            long startTime = System.currentTimeMillis();

            userTweetsMap = MyDataReader.readDataToHashMapHeaps(filePath);

            long endTime = System.currentTimeMillis();
            System.out.println((endTime - startTime) + " milliseconds to build the data structure");

            // Launch ui
            UserInterface ui = new UserInterface(userTweetsMap);
            ui.start();

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

	}

