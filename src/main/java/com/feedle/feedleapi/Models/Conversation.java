package com.feedle.feedleapi.Models;

import lombok.Data;

import java.util.ArrayList;
@Data
public class Conversation {
    public int Id;
    public String Title ;
    public ArrayList<Message> Messages ;
    public ArrayList<UserConversation> UserConversations ;
}
