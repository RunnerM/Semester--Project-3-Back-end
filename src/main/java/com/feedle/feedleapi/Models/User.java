package com.feedle.feedleapi.Models;

import lombok.Data;

import java.util.ArrayList;
@Data
public class User {
    public int Id;
    public String UserName;
    public String Password;
    public String DisplayedUserName;
    public ArrayList<Post> UserPosts;
    public ArrayList<UserConversation> UserConversations;
}
