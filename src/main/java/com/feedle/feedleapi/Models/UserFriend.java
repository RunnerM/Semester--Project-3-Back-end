package com.feedle.feedleapi.Models;

import lombok.Data;

@Data
public class UserFriend {
    public int userFriendId;
    public int userId;
    public int friendId;
    public String userName;
}
