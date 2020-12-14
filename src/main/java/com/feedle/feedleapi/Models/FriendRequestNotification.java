package com.feedle.feedleapi.Models;

import lombok.Data;

@Data
public class FriendRequestNotification {
    public int friendRequestId;
    public int userId;
    public int creatorId;
    public int potentialFriendUserId;
    public String content;
    public String potentialFriendUserName;
    public String creatorUserName;
}
