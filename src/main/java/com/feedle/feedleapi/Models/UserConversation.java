package com.feedle.feedleapi.Models;

import lombok.Data;
/** Represents a connection Between users and conversations.
 * Getters/Setters, no argument constructor, equals(), canEqual(),
 * hashCode(), ToString() are available and generated at runtime.
 *
 * This class makes many to many relation possible.
 *
 * @author Marton Pentek
 * @version 1.0
 * @since 12/2020
 */

@Data
public class UserConversation {
    public int userid;
    public int conversationId;
    public User user;
    public Conversation conversation;
}
