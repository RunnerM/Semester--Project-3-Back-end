package com.feedle.feedleapi.Models;

import lombok.Data;

@Data
public class UserConversation {
    public int UserId;
    public int ConversationId;
    public User User;
    public Conversation Conversation;
}
