/**
 * @author Christian Burke and Michael D'Amico
 * @version 26 November 2024
 */
package twitterpack;

import java.io.IOException;

public class Program8 {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java Program8 <trainFile> <testFile>");
            return;
        }

        String trainFile = args[0];
        String testFile = args[1];

        try {
            // Build train hash map using MyDataReader
            long startTime = System.currentTimeMillis();
            MyHashMap<Tweet, Boolean> trainData = MyDataReader.readDataToHashMap(trainFile);
            long endTime = System.currentTimeMillis();
            System.out.println((endTime - startTime) + " milliseconds to build the train hash map");

            // Build test hash map using MyDataReader
            startTime = System.currentTimeMillis();
            MyHashMap<Tweet, Boolean> testData = MyDataReader.readDataToHashMap(testFile);
            endTime = System.currentTimeMillis();
            System.out.println((endTime - startTime) + " milliseconds to build the test hash map");

            // Create and train the sentiment analysis model
            MySentimentAnalysisModel model = new MySentimentAnalysisModel(trainData);

            // Perform prediction and calculate the accuracy
            double accuracy = model.prediction(testData);
            System.out.println("Ratio of correct predictions: " + accuracy);

            // Print out number of resizes (you can track the resize count in MyHashMap if needed)
            System.out.println("Resizing occurred " + trainData.getResizeCount() + " times for train hash map");
            System.out.println("Resizing occurred " + testData.getResizeCount() + " times for test hash map");

        } catch (IOException e) {
            System.err.println("Error reading files: " + e.getMessage());
        }
    }
}
