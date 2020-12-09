package com.feedle.feedleapi.Services;

import com.feedle.feedleapi.Models.User;
import com.feedle.feedleapi.Models.UserInformation;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
    void registerUser(User user);

    User authorizeUser(String username, String password);

    void deleteUser(int id);

    List<User> getUser();

    User updateUser(User user);

    UserInformation getUserInformationById(int id);

}
