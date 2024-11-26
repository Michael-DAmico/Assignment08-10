# Assignment08-10
Twitter

Sentiment Analysis with MyHashMap Implementation
Authors
Christian Burke and Michael D'Amico
Version: November 26, 2024

Overview
This project implements a custom Sentiment Analysis Model using a hash map (custom class: MyHashMap) to classify tweets as positive or negative based on word frequency analysis. The sentiment analysis leverages separate chaining for collision resolution in the hash map and trains the model using a dataset of labeled tweets.

Contributions:

Christian

  	MySentimentAnalysisModel
  	Javadoc,
	.PDF
     50/50 on Program8

  
Michael
	
 	MyHashMap
    equals method to Tweet class
 	Javadoc,
    50/50 on Program8
	


  
  Creations Made:
  
 	Added MySentimentAnalysisModel class
 	Added MyHashMap class
 	Added Program8 class
	Added equals method to Tweet class
    no change the MyDataReader except commenting out readDataToHashMapHeaps



Key Features:
Custom Hash Map Implementation:

Supports separate chaining for collision handling.
Includes dynamic resizing when the load factor exceeds 0.75.
Tracks resize operations and collisions.
Sentiment Analysis Model:

Processes training data to build word frequency maps for positive and negative sentiments.
Cleans and preprocesses tweet text, removing stop words and irrelevant characters.
Predicts sentiment for test data and calculates accuracy.
Performance Metrics:

Tracks time to build hash maps and resize operations.
Evaluates the accuracy of predictions.
Files Included
Java Classes:
MyHashMap

Implements a custom hash map using separate chaining.
Key functionalities include put, get, resize, and iterator.
MySentimentAnalysisModel

Trains the sentiment analysis model using word frequencies from labeled tweets.
Implements text preprocessing (stop word removal, cleaning).
Predicts sentiment of tweets based on word distributions in training data.
Program8

Main driver program.
Reads training and test datasets, builds hash maps, trains the model, and evaluates predictions.
Outputs metrics such as build time, resize count, and prediction accuracy.
Instructions
Requirements
Java Development Kit (JDK 8 or higher).
Training and test datasets formatted as .csv files, with each line representing a tweet and its sentiment (true/false for positive/negative).
Compilation
Compile the program using the following command:

bash
Copy code
javac -d . *.java
Execution
Run the program with the following command:

bash
Copy code
java twitterpack.Program8 <trainFile> <testFile>
Arguments:

<trainFile>: Path to the training dataset (CSV format).
<testFile>: Path to the test dataset (CSV format).
Example:

bash
Copy code
java twitterpack.Program8 train.csv test.csv
Output
The program outputs:

Time taken to build the hash map for both training and test datasets.
Accuracy of sentiment predictions.
Number of hash table resizes during hash map construction.

How It Works
Training Phase:
Build Word Frequencies:

For each tweet in the training dataset:
Preprocess the text (lowercasing, removing stop words, cleaning).
Count occurrences of words in positive and negative tweets.
Store in MyHashMap:

Positive and negative word frequencies are stored in two separate hash maps.
Prediction Phase:
Evaluate Tweets:

For each tweet in the test dataset:
Clean and preprocess text.
Sum the frequency scores of its words from both hash maps.
Predict the sentiment based on which sum is higher.
Calculate Accuracy:

Compare predictions to true labels and compute the ratio of correct predictions.
