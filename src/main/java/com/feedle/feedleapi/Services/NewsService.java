package com.feedle.feedleapi.Services;

import com.feedle.feedleapi.Models.Comment;
import com.feedle.feedleapi.Models.Post;

import java.util.ArrayList;
import java.util.List;

public interface NewsService {
    List<Post> getAllPost();

    void addPost(Post post);

    void removePost(int Id);

    void updatePost(Post post);

    void addCommentToPost(int PostId, Comment comment);


}
