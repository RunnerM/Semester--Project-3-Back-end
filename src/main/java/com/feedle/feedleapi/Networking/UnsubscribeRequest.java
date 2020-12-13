package com.feedle.feedleapi.Networking;

import com.feedle.feedleapi.Models.Conversation;

public class UnsubscribeRequest extends Request {
    private int subscriptionId;

    public UnsubscribeRequest(int subscriptionId) {
        super(RequestType.UnsubscribeRequest);
        this.subscriptionId = subscriptionId;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }
}
