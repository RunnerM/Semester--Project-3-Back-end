package com.feedle.feedleapi.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    public int userId;
    public int conversationId;
    public Conversation conversation;
    public int withWhomUserId;


    @JsonIgnore
    public int getLastMessageId()
    {
        int max = -1;
        for (int i = 0; i < conversation.messages.size(); i++) {
            if (conversation.messages.get(i).id > max)
            {
                max = conversation.messages.get(i).id;
            }
        }
        return max;
    }
}
