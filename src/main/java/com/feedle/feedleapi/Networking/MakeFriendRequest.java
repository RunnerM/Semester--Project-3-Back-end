package com.feedle.feedleapi.Networking;

import com.feedle.feedleapi.Models.FriendRequestNotification;

public class MakeFriendRequest extends  Request {
    private FriendRequestNotification friendRequestNotification;
    public MakeFriendRequest(FriendRequestNotification friendRequestNotification)
    {
        super(RequestType.MakeFriendRequest);
        this.friendRequestNotification = friendRequestNotification;
    }

    public FriendRequestNotification getFriendRequestNotification() {
        return friendRequestNotification;
    }
}
