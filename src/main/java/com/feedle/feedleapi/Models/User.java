package com.feedle.feedleapi.Models;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
@Data
public class User implements Serializable {
    public int id;
    public String username;
    public String password;
    public String displayedUsername;
    public ArrayList<Post> userPosts;
    public ArrayList<UserConversation> userConversations;
    public int securityLevel;
}
