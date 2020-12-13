package com.feedle.feedleapi.Networking;

import com.feedle.feedleapi.Models.UserSubscription;

public class SubscribeToUserRequest extends Request {
    private UserSubscription userSubscription;

    public SubscribeToUserRequest(UserSubscription userSubscription) {
        super(RequestType.SubscribeToUser);
        this.userSubscription = userSubscription;
    }

    public UserSubscription getUserSubscription() {
        return userSubscription;
    }
}
