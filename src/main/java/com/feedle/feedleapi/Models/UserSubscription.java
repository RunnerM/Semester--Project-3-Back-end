package com.feedle.feedleapi.Models;

import lombok.Data;

@Data
public class UserSubscription {
    public int userSubscriptionId;
    public int userId;
    public int subscriptionId;
    public String userName;
}
