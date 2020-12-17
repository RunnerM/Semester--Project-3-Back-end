package com.feedle.feedleapi.Networking;

import com.feedle.feedleapi.Models.PostReaction;

public class UpdateReactionRequest extends Request {
    private PostReaction postReaction;
    public UpdateReactionRequest(PostReaction postReaction)
    {
        super(RequestType.UpdateReactionRequest);
        this.postReaction = postReaction;
    }

    public PostReaction getPostReaction() {
        return postReaction;
    }
}
