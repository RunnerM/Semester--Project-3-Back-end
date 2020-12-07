package com.feedle.feedleapi.Services;

import com.feedle.feedleapi.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceManager implements UserService{

    private NetworkService networkService;
    private List<User> users;

    @Autowired
    public UserServiceManager (NetworkService networkService)
    {
        this.networkService = networkService;
        users = networkService.getAllUser();
    }

    @Override
    public void registerUser(User user) {
        User newUser = networkService.addUser(user);
        if (newUser != null)
        {
            this.users.add(newUser);
        }
        else System.out.println("BadUser");
    }

    @Override
    public User authorizeUser(String username, String password) {
        for(int i = 0; i<users.size(); i++)
        {
            if (users.get(i).username.equals(username))
            {
                if (users.get(i).password.equals(password))
                    return users.get(i);
                else
                    return null;
            }
        }
        return null;
    }


    @Override
    public void deleteUser(int id) {
        int acceptId = networkService.deleteUser(id);
        if (acceptId != -1 )
        {
            for (int i=0; i<users.size();i++)
            {
                if (users.get(i).id == acceptId) {
                    users.remove(i);
                    break;
                }
            }
        }
        else System.out.println("UserToDeleteIsNotFound");
    }

    @Override
    public List<User> getUser() {
        return this.users;
    }

    @Override
    public void updateUser(User user) {
       User updatedUser = networkService.updateUser(user);
       if (updatedUser != null) {
           for (int i = 0; i < users.size(); i++) {
               if (users.get(i).id == updatedUser.id)
               {
                   users.set(i,updatedUser);
                   break;
               }
           }
       }
    }
}
