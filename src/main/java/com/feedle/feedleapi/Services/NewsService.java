package com.feedle.feedleapi.Services;

import com.feedle.feedleapi.Models.Comment;
import com.feedle.feedleapi.Models.Post;

import java.util.ArrayList;

public interface NewsService {
    ArrayList<Post> getAllNews();

    void addNews(Post post);

    void removeNews(int Id);

    void updatePost(Post post);

    void addCommentToPost(int PostId, Comment comment);

}
