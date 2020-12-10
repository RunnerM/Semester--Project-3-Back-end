package com.feedle.feedleapi.Networking;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.feedle.feedleapi.Models.Comment;
import com.feedle.feedleapi.Models.Message;

public class SendMessageRequest extends Request {
    private Message message;
    public SendMessageRequest(Message message)
    {
        super(RequestType.SendMessage);
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }
}
