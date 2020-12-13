package com.feedle.feedleapi.Networking;

import com.feedle.feedleapi.Models.UserConversation;

import java.util.ArrayList;

public class AddConversationResponse extends Request {

    private ArrayList<UserConversation> userConversations;

    public AddConversationResponse(ArrayList<UserConversation> userConversations) {
        super(RequestType.AddConversationResponse);
        this.userConversations = userConversations;
    }

    public ArrayList<UserConversation> getUserConversations() {
        return userConversations;
    }
}
