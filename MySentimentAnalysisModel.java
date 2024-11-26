package twitterpack;

import java.util.Iterator;

public class MySentimentAnalysisModel {

    private MyHashMap<String, Integer> positive;
    private MyHashMap<String, Integer> negative;

    public MySentimentAnalysisModel(MyHashMap<Tweet, Boolean> trainData) {
        positive = new MyHashMap<>();
        negative = new MyHashMap<>();
        
        trainModel(trainData);
    }

    private void trainModel(MyHashMap<Tweet, Boolean> trainData) {
        Iterator<Tweet> iterator = trainData.iterator();
        
        while (iterator.hasNext()) {
            Tweet tweet = iterator.next();
            boolean sentiment = tweet.isSentimentPositive();
            String text = tweet.getText().toLowerCase();
            String[] words = text.split("\\s+");

            for (String word : words) {
                word = cleanWord(word);
                
                if (!word.isEmpty()) {
                    MyHashMap<String, Integer> targetMap = sentiment ? positive : negative;
                    targetMap.put(word, targetMap.getOrDefault(word, 0) + 1);
                }
            }
        }
    }

    private String cleanWord(String word) {
        // Remove mentions, URLs, and any non-alphabetic characters
        word = word.replaceAll("^@.*", "");  // Remove mentions (starting with @)
        word = word.replaceAll("http[s]?://.*", "");  // Remove URLs
        word = word.replaceAll("[^a-zA-Z]", "");  // Remove non-alphabetical characters
        return word.trim();
    }

    public double prediction(MyHashMap<Tweet, Boolean> testData) {
        int correctPredictions = 0;
        int totalPredictions = 0;

        Iterator<Tweet> iterator = testData.iterator();
        
        while (iterator.hasNext()) {
            Tweet tweet = iterator.next();
            boolean actualSentiment = tweet.isSentimentPositive();
            String text = tweet.getText().toLowerCase();
            String[] words = text.split("\\s+");
            
            // Calculate score
            int score = 0;
            for (String word : words) {
                word = cleanWord(word);
                if (!word.isEmpty()) {
                    int positiveFreq = positive.getOrDefault(word, 0);
                    int negativeFreq = negative.getOrDefault(word, 0);
                    
                    score += (positiveFreq - negativeFreq);
                }
            }

            // Determine predicted sentiment based on the score
            boolean predictedSentiment = score >= 0;

            if (predictedSentiment == actualSentiment) {
                correctPredictions++;
            }
            totalPredictions++;
        }

        // Return the accuracy ratio
        return (double) correctPredictions / totalPredictions;
    }
}
