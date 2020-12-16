package com.feedle.feedleapi.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
/** Represents a conversation.
 * Getters/Setters, no argument constructor, equals(), canEqual(),
 * hashCode(), ToString() are available and generated at runtime.
 * @author Marton Pentek
 * @version 1.0
 * @since 12/2020
 */
@Data
public class Conversation {
    /**
     * Represents the Id of the conversation generally used for identifying the Conversation.
     */
    public int id;
    /**
     * Represents the Title of the comment.
     * */
    public String title ;
    /**
     * Stores the Messages in the Conversation.
     * */
    public ArrayList<Message> messages ;



}
