package com.feedle.feedleapi.Models;

import lombok.Data;

import java.util.ArrayList;
@Data
public class Conversation {
    public int id;
    public String title ;
    public ArrayList<Message> messages ;
    public ArrayList<UserConversation> userConversations ;
}
