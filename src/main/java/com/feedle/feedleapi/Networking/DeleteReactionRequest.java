package com.feedle.feedleapi.Networking;

public class DeleteReactionRequest extends Request {
    private int postReactionId;

    public DeleteReactionRequest(int postReactionId) {
        super(RequestType.DeleteReactionRequest);
        this.postReactionId = postReactionId;
    }

    public int getPostReactionId() {
        return postReactionId;
    }
}
