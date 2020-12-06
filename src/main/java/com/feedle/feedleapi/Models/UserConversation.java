package com.feedle.feedleapi.Models;

import lombok.Data;

@Data
public class UserConversation {
    public int userid;
    public int conversationId;
    public User user;
    public Conversation conversation;
}
