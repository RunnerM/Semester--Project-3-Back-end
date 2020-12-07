package com.feedle.feedleapi.Services;

import com.feedle.feedleapi.Models.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
    void registerUser(User user);

    User authorizeUser(String username, String password);

    void deleteUser(int id);

    List<User> getUser();

    void updateUser(User user);

}
