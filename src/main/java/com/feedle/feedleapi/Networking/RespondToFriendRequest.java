package com.feedle.feedleapi.Networking;

import com.feedle.feedleapi.Models.FriendRequestNotification;

public class RespondToFriendRequest extends Request {
    private boolean respondStatus;
    private FriendRequestNotification friendRequestNotification;

    public RespondToFriendRequest(boolean respondStatus, FriendRequestNotification friendRequestNotification) {
        super(RequestType.RespondToFriendRequest);
        this.respondStatus = respondStatus;
        this.friendRequestNotification = friendRequestNotification;
    }

    public FriendRequestNotification getFriendRequestNotification() {
        return friendRequestNotification;
    }
}
