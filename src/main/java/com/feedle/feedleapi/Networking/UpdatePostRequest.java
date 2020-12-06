package com.feedle.feedleapi.Networking;

import com.feedle.feedleapi.Models.Post;

public class UpdatePostRequest extends Request{
    private Post post;
    public UpdatePostRequest(Post post)
    {
        super(RequestType.UpdatePost);
        this.post = post;
    }

    public Post getPost() {
        return post;
    }
}
