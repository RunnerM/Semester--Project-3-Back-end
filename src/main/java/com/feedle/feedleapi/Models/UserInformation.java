package com.feedle.feedleapi.Models;

import java.util.List;

public class UserInformation {
    public int id;
    public String userName;
    public List<UserFriend> userFriends;
    public List<UserSubscription> userSubscriptions;
    public String userImageSrc;
}
