package com.feedle.feedleapi.Networking;

import com.feedle.feedleapi.Models.Post;

public class AddPostRequest extends Request{
    private Post post;

    public AddPostRequest(Post post)
    {
        super(RequestType.AddPost);
        this.post = post;
    }

    public Post getPost() {
        return post;
    }
}
