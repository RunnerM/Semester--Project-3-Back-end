package com.feedle.feedleapi.Services.Networking;

import com.feedle.feedleapi.Models.Post;
import com.feedle.feedleapi.Models.User;

import java.util.ArrayList;

public interface NetworkService {
    void addPost(Post post);
    void addUser(User user);

    ArrayList<User> getAllUser();
    ArrayList<Post> getAllPost();

    void updatePost(Post post);
    void updateUser();

    void deleteUser(int UserId);
    void deletePost(int PostId);
}
