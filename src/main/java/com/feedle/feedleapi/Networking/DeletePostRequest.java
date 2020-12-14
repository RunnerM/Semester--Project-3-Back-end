package com.feedle.feedleapi.Networking;

public class DeletePostRequest extends Request{
    private int postId;
    public DeletePostRequest(int postId)
    {
        super(RequestType.DeletePost);
        this.postId = postId;
    }

    public int getPostId() {
        return postId;
    }
}
