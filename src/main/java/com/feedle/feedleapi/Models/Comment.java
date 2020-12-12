package com.feedle.feedleapi.Models;

import lombok.Data;

/** Represents a comment.
 * Getters/Setters, no argument constructor, equals(), canEqual(),
 * hashCode(), ToString() are available and generated at runtime.
 * @author Marton Pentek
 * @version 1.0
 * @since 12/2020
 */
@Data
public class Comment {
    /**
     * Represents the Id of the comment generally used for accessing the comment.
     */
    public int id;
    /**
     * Represents the actual text in the comment.
     */
    public String content;
    /**
     * Represents the id of the author, used to connect the author to the commment.
     */
    public int userId;
    /**
     * Represents the name of the author, used for display purposes;
     */
    public String authorUserName;
    /**
     * Represents the day of creation.
     */
    public int day;
    /**
     * Represents the month of creation.
     */
    public int month;
    /**
     * Represents the year of creation.
     */
    public int year;
    /**
     * Represents the hour of creation.
     */
    public int hour;
    /**
     * Represents the minute of creation.
     */
    public int minute;
    /**
     * Represents the second of creation.
     */
    public int second;

    /**
     * Represents the post reference
     */
    public int postId;
}
