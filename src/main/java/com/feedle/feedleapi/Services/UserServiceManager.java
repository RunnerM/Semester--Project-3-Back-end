package com.feedle.feedleapi.Services;

import com.feedle.feedleapi.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;

@Service
public class UserServiceManager implements UserService{

    private NetworkService networkService;

    @Autowired
    public UserServiceManager (NetworkService networkService)
    {
        this.networkService = networkService;
    }

    @Override
    public void registerUser(User user) {

    }

    @Override
    public User authorizeUser(String username, String password) {
        return null;
    }


    @Override
    public void deleteUser(int Id) {

    }

    @Override
    public ArrayList<User> getUser() {
        return this.networkService.getAllUser();
    }

    @Override
    public void updateUser(int Id, User user) {

    }
}
