package com.feedle.feedleapi.Services;

import com.feedle.feedleapi.Models.Comment;
import com.feedle.feedleapi.Models.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NewsServiceManager implements NewsService {


    @Override
    public ArrayList<Post> getAllPost() {
        return null;
    }

    @Override
    public void addPost(Post post) {

    }

    @Override
    public void removePost(int Id) {

    }

    @Override
    public void updatePost(Post post) {

    }

    @Override
    public void addCommentToPost(int PostId, Comment comment) {

    }
}
