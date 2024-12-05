# Assignment08
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















# Assignment 9

Key Features:

Custom Heap Implementation (MyHeap)
Efficient organization of tweets by recency (date and time).

Contributions:

Christian

  	viewRecentTweet method in UserInterface class
   	deleteRecentTweet method in UserInterface class
    viewOtherUserTweet method in UserInterface class
    signOut method in UserInterface class
    Program9
  	Javadoc,
	.PDF
 	Readme

  
Michael
	
 	MyHeap class
  	UserInterface method in UserInterface class
   	start method in UserInterface class
    	displayMenu method in UserInterface class
    toString method to Tweet class
 	Javadoc,
    
	


  
  Creations Made:
  
 	Added UserInterface class
 	Added MyHeap class
 	Added Program9 class
	Added tostring method to Tweet class
    no change the MyDataReader
    
Supports:
Adding tweets.
Viewing your most recent tweet.
Deleting the most recent tweet.
Veiwing another users tweets.
Sign out.

Encapsulates tweet data including:
User ID.
Post content.
Sentiment classification (positive/negative).
Post date and time.
Implements Comparable to prioritize tweets by post date.
Data Parsing (MyDataReader)

Reads tweet data from TSV files and constructs:
A hash map of user-specific heaps (HashMap<String, MyHeap<Tweet>>).
A hash map for sentiment classification (MyHashMap<Tweet, Boolean>).

Handles preprocessing of tweet data for sentiment analysis.
Program9 (Main Driver)

Manages user interactions via a console-based UI:
Allows users to view, delete, and query tweets.
Supports signing in and out of user accounts.

Files Included:
Java Classes
MyHeap: Custom heap implementation for managing tweets by recency.
UserInterface: Implements user interface for viewing tweet data.
MyDataReader: Utility class for reading and processing tweet datasets.
Program9: Main program for managing and querying tweets.
Tweet: Data class representing individual tweets with metadata and sentiment.

Instructions:
Requirements
Java Development Kit (JDK 8 or higher).
Input dataset formatted as a .tsv file, where each line contains:
Tweet ID, sentiment (1 for positive, 0 for negative), user ID, text, and post date (ISO 8601 format).

Compilation:
Compile all files with the following command:

bash
Copy code
javac -d . *.java  
Execution
Run the program with the following command:

bash
Copy code
java twitterpack.Program9 <tweets_train.tsv>  
Argument:

<tweets_train.tsv>: Path to the input dataset.
Example:
bash
Copy code
java twitterpack.Program9 tweets_train.tsv  
Output
Data Structure Build Time:

Time taken to load tweet data into the hash map of heaps.
User Features:

View the most recent tweet of a user.
Delete the most recent tweet of a user.
Query tweets of other users.
Error Handling:

Handles malformed data.
Ensures user experience with detailed error messages.
How It Works
Data Loading Phase:
Parses the dataset using MyDataReader.
Constructs a hash map of user-specific heaps for managing tweets.
Tweet Management:
View Recent Tweet: Fetches the most recent tweet for a user.
Delete Recent Tweet: Removes and displays the latest tweet.
Query Other Users: Allows signed-in users to view tweets from other accounts.
Performance Metrics
Build time for data structures.
Efficiency of heap operations (view, delete).
