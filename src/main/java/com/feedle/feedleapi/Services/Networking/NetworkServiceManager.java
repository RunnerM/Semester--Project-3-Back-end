package com.feedle.feedleapi.Services.Networking;

import com.feedle.feedleapi.Models.Post;
import com.feedle.feedleapi.Models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NetworkServiceManager implements NetworkService{


    @Override
    public void addPost(Post post) {

    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public ArrayList<User> getAllUser() {
        return null;
    }

    @Override
    public ArrayList<Post> getAllPost() {
        return null;
    }

    @Override
    public void updatePost(Post post) {

    }

    @Override
    public void updateUser() {

    }

    @Override
    public void deleteUser(int UserId) {

    }

    @Override
    public void deletePost(int PostId) {

    }
}
