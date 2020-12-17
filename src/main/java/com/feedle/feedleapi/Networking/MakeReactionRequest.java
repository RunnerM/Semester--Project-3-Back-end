package com.feedle.feedleapi.Networking;

import com.feedle.feedleapi.Models.PostReaction;

public class MakeReactionRequest extends Request {
    private PostReaction postReaction;
    public MakeReactionRequest(PostReaction postReaction)
    {
        super(RequestType.MakeReactionRequest);
        this.postReaction = postReaction;
    }

    public PostReaction getPostReaction() {
        return postReaction;
    }
}
