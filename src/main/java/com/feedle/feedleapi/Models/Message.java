package com.feedle.feedleapi.Models;

import lombok.Data;

/** Represents a message.
 * Getters/Setters, no argument constructor, equals(), canEqual(),
 * hashCode(), ToString() are available and generated at runtime.
 * @author Marton Pentek
 * @version 1.0
 * @since 12/2020
 */

@Data
public class Message {
    /**
     * Represents the Id of the Message generally used for identifying the Message.
     */
    public int id;
    /**
     * Represents the actual text in the comment.
     */
    public String content;
    /**
     * Represents the id of the author, used to connect the author to the commment.
     */
    public int UserId;
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
    public int conversationId;
}
