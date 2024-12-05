/**
 * @author Christian Burke and Michael D'Amico
 * @version 4 December 2024
 */
package twitterpack;

import java.util.HashMap;
import java.util.Scanner;

public class UserInterface {

	private HashMap<String, MyHeap<Tweet>> userTweetMap;
    private Scanner scanner;

    /**
     * Constructs a UserInterface instance with the provided user tweet map.
     *
     * @param userTweetMap A hash map with user IDs as keys and heaps of their tweets as values.
     */
    public UserInterface(HashMap<String, MyHeap<Tweet>> userTweetMap) {
        this.userTweetMap = userTweetMap;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the interactive menu, allowing users to sign in and perform actions.
     */
    public void start() {
        System.out.println("Welcome to the Twitter Interface!");
        String userId = null;

        while (true) {
            if (userId == null) {
                System.out.print("Enter your User ID to sign in (or type 'exit' to quit): ");
                userId = scanner.nextLine();
                if (userId.equalsIgnoreCase("exit")) break;

                if (!userTweetMap.containsKey(userId)) {
                    System.out.println("User ID not found. Try again.");
                    userId = null;
                } else {
                    System.out.println("Signed in as User: " + userId);
                }
            } else {
                displayMenu(userId);
            }
        }

        System.out.println("Goodbye!");
    }

    /**
     * Displays the menu options for the signed-in user and processes their input.
     *
     * @param userId The currently signed-in user's ID.
     */
    private void displayMenu(String userId) {
        System.out.println("\nMenu:");
        System.out.println("1. View your most recent tweet");
        System.out.println("2. Delete your most recent tweet");
        System.out.println("3. View another user's recent tweet");
        System.out.println("4. Sign out");

        System.out.print("Choose an option: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 :
            	viewRecentTweet(userId);
            	break;
            case 2:
            	deleteRecentTweet(userId);
            	break;
            case 3:
            	viewOtherUserTweet();
            	break;
            case 4: 
            	signOut(userId);
            	break;
            default: 
            System.out.println("Invalid choice. Please try again.");
        }
    }

	/**
     * Displays the most recent tweet of the signed-in user.
     *
     * @param userId The currently signed-in user's ID.
     */
    private void viewRecentTweet(String userId) {
        MyHeap<Tweet> heap = userTweetMap.get(userId);
        if (heap == null || heap.isEmpty()) {
            System.out.println("No tweets found.");
        } else {
            System.out.println("Your most recent tweet:");
            System.out.println(heap.peek());
        }
    }

    /**
     * Deletes the most recent tweet of the signed-in user and displays it.
     *
     * @param userId The currently signed-in user's ID.
     */
    private void deleteRecentTweet(String userId) {
    	MyHeap<Tweet> heap = userTweetMap.get(userId);
        if (heap == null || heap.isEmpty()) {
            System.out.println("No tweets to delete.");
            return;
        }

        long startTime = System.nanoTime();
        Tweet deletedTweet = heap.delete();
        long endTime = System.currentTimeMillis();

        System.out.printf("Deleted tweet for user %s in %d nanoseconds.%n", userId, (endTime - startTime));
        System.out.println("Most recent tweet deleted: " + deletedTweet);
    }

    /**
     * Allows the signed-in user to view another user's most recent tweet.
     */
    private void viewOtherUserTweet() {
        System.out.print("Enter the User ID of the user whose tweet you want to view: ");
        String otherUserId = scanner.nextLine();

        MyHeap<Tweet> heap = userTweetMap.get(otherUserId);
        if (heap == null || heap.isEmpty()) {
            System.out.println("No tweets found for this user.");
        } else {
            System.out.println("Most recent tweet from User " + otherUserId + ":");
            System.out.println(heap.peek());
        }
    }

    /**
     * Allows the user to sign out and resets the user session.
     * Prompts the sign-in message again.
     * @param currentUserID 
     */
    private void signOut(Object currentUserID) {
        //System.out.println("Signed out.");
        System.out.println("You have been signed out.");
        System.out.println();
        currentUserID = null; // Clear the current user
        //System.out.println("\nWelcome to the Twitter Interface!");
        start(); // Restart the interface to prompt for sign-in
    }
}
