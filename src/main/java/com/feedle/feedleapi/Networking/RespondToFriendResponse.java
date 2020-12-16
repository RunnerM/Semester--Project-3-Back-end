package com.feedle.feedleapi.Networking;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.feedle.feedleapi.Models.UserFriend;

import java.util.ArrayList;

public class RespondToFriendResponse extends Request {
    private ArrayList<UserFriend> userFriends;
    public RespondToFriendResponse(ArrayList<UserFriend> userFriends)
    {
        super(RequestType.ResponseToFriendResponse);
        this.userFriends = userFriends;
    }

    @JsonIgnore
    public ArrayList<UserFriend> getUserFriends() {
        return userFriends;
    }
}
