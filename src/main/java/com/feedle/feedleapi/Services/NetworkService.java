package com.feedle.feedleapi.Services;

import com.feedle.feedleapi.Models.Comment;
import com.feedle.feedleapi.Models.Message;
import com.feedle.feedleapi.Models.Post;
import com.feedle.feedleapi.Models.User;

import java.util.ArrayList;
import java.util.List;

public interface NetworkService {
    Post addPost(Post post);

    User addUser(User user);

    List<User> getAllUser();

    List<Post> getAllPost();

    Post updatePost(Post post);

    User updateUser(User user);

    int deleteUser(int userId);

    int deletePost(int postId);

    Comment postComment(Comment comment);

    Message sendMessage(Message message);
}
