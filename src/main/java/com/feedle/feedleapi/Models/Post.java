package com.feedle.feedleapi.Models;

import lombok.Data;

import java.util.ArrayList;
/** Represents a news post.
 * Getters/Setters, no argument constructor, equals(), canEqual(),
 * hashCode(), ToString() are available and generated at runtime.
 * @author Marton Pentek
 * @version 1.0
 * @since 12/2020
 */
@Data
public class Post {
    /**
     * Represents the Id of the Post generally used for accessing the Post.
     */
    public int id ;
    /**
     * Represents the Title of the Post.
     * */
    public int userId;
    public String title ;
    /**
     * Represents the body text in the Post.
     */
    public String content ;
    /**
     * Represents the name of the author, used for display purposes;
     */
    public String authorUserName ;
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
    public ArrayList<Comment> comments ;
    public ArrayList<PostReaction> postReactions;
    public String postImageSrc;

}
