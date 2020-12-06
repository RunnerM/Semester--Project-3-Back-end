package com.feedle.feedleapi.Networking;

import com.feedle.feedleapi.Models.Post;

import java.util.List;

public class GetPostsResponse extends Request {
    private List<Post> posts;

    public GetPostsResponse(List<Post> posts) {
        super(RequestType.GetPosts);
        this.posts = posts;
    }

    public List<Post> getPostList() {
        return posts;
    }
}
