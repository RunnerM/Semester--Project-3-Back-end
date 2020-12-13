package com.feedle.feedleapi.Networking;

import com.feedle.feedleapi.Models.UserFriend;

import java.util.ArrayList;

public class RespondToFriendResponse extends Request {
    private ArrayList<UserFriend> userFriends;
    public RespondToFriendResponse(ArrayList<UserFriend> userFriends)
    {
        super(RequestType.ResponseToFriendResponse);
        this.userFriends = userFriends;
    }

    public ArrayList<UserFriend> getUserFriends() {
        return userFriends;
    }
}
