package com.feedle.feedleapi.Services;

import com.feedle.feedleapi.Models.User;

import java.util.ArrayList;

public interface UserService {
    void registerUser(User user);

    User authorizeUser(String username, String password);

    void deleteUser(int Id);

    ArrayList<User> getUser();

    void updateUser(int Id, User user);

}
