package twitterpack;

import java.time.LocalDateTime;
import java.util.Objects;

public class Tweet implements Comparable<Tweet>{
    private LocalDateTime postDateTime;
    private String userID;
    private String text;
    private int id;
    private boolean isSentimentPositive;
    
    public Tweet(int id, boolean isSentimentPositive, String userID, String text, LocalDateTime postDateTime)
    {
        this.id = id;
        this.isSentimentPositive = isSentimentPositive;
        this.userID = userID;
        this.text = text;
        this.postDateTime = postDateTime;
    }


    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }


    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }


    /**
     * @return the text
     */
    public String getText() {
        return text;
    }


    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }


    /**
     * @return the postDateTime
     */
    public LocalDateTime getPostDateTime() {
        return postDateTime;
    }


    /**
     * @param postDateTime the postDateTime to set
     */
    public void setPostDateTime(LocalDateTime postDateTime) {
        this.postDateTime = postDateTime;
    }
    
    @Override 
    public int hashCode() {
        return Objects.hash(this.id);
    }

    /**
     * @return the sentiment
     */
    public boolean isSentimentPositive() {
        return isSentimentPositive;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }


    @Override
    public int compareTo(Tweet otherTweet) {
        return this.postDateTime.compareTo(otherTweet.getPostDateTime());
    }

    
    /**
     * Checks if this Tweet object is equal to another object.
     * Two Tweet objects are considered equal if their id and userID are the same.
     *
     * @param obj the object to compare with this Tweet
     * @return true if the given object is equal to this Tweet, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tweet tweet = (Tweet) obj;
        return id == tweet.id && Objects.equals(userID, tweet.userID);
    }
    
}

