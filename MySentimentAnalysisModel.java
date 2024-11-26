/**
 * @author Christian Burke and Michael D'Amico
 * @version 26 November 2024
 */
package twitterpack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MySentimentAnalysisModel {

    private MyHashMap<String, Integer> positive;
    private MyHashMap<String, Integer> negative;

    /**
     * Constructor for MySentimentAnalysisModel.
     * @param trainData The training data, a map of tweets to their sentiment.
     * This constructor will build the frequency maps for positive and negative sentiments.
     */
    public MySentimentAnalysisModel(MyHashMap<Tweet, Boolean> trainData) {
        positive = new MyHashMap<>();
        negative = new MyHashMap<>();
        
        trainModel(trainData);
    }

    /**
     * Train the sentiment model by counting word frequencies in positive and negative tweets.
     * @param trainData The training data containing tweets and their sentiment labels.
     */
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

    /**
     * Clean the word by removing non-alphabetic characters.
     * @param word The word to clean.
     * @return The cleaned word.
     */
    private String cleanWord(String word) {
        // Remove mentions, URLs, and any non-alphabetic characters
        word = word.replaceAll("^@.*", "");  // Remove mentions (starting with @)
        word = word.replaceAll("http[s]?://.*", "");  // Remove URLs
        word = word.replaceAll("[^a-zA-Z]", "");  // Remove non-alphabetical characters
        word = removeStopWords(word);
        return word.trim();
    }
    
    
    /**
     * Removes stopwords from the given text.
     * Stopwords are common words that are not useful for sentiment analysis or text processing.
     *
     * @param text the input string from which stopwords will be removed
     * @return a new string with all stopwords removed, retaining only meaningful words
     */
    private static final Set<String> STOPWORDS = new HashSet<>(Arrays.asList(
            "a", "an", "the", "and", "or", "but", "if", "then", "so", "at", "by",
            "for", "of", "on", "in", "to", "with", "is", "are", "was", "were", "be", "been", "being"
        ));

        // Method to remove stopwords
        public static String removeStopWords(String text) {
            StringBuilder result = new StringBuilder();
            String[] words = text.split("\\s+");
            for (String word : words) {
                if (!STOPWORDS.contains(word)) {
                    result.append(word).append(" ");
                }
            }
            return result.toString().trim();
        }

    /**
     * Predict the sentiment of the test data by comparing word frequencies in positive and negative maps.
     * @param testData A map of tweets to their true sentiment.
     * @return The ratio of correct predictions to the total number of predictions.
     */
    public double prediction(MyHashMap<Tweet, Boolean> testData) {
        int correctPredictions = 0;
        int totalPredictions = 0;

        Iterator<Tweet> iterator = testData.iterator();
        
        while (iterator.hasNext()) {
            Tweet tweet = iterator.next();
            boolean actualSentiment = tweet.isSentimentPositive();
            String text = tweet.getText().toLowerCase();
            String[] words = text.split("\\s+");
            
            // find score
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


