package com.feedle.feedleapi.Networking;

import com.feedle.feedleapi.Models.Conversation;

public class AddConversationRequest extends Request {
    private Conversation conversation;
    private int creatorId;
    private int withWhomId;

    public AddConversationRequest(Conversation conversation, int creatorId, int withWhomId) {
        super(RequestType.AddConversation);
        this.creatorId = creatorId;
        this.conversation = conversation;
        this.withWhomId = withWhomId;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public int getWithWhomId() {
        return withWhomId;
    }
}
