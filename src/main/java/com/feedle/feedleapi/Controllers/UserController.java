package com.feedle.feedleapi.Controllers;

import com.feedle.feedleapi.Models.Post;
import com.feedle.feedleapi.Models.User;
import com.feedle.feedleapi.Models.UserConversation;
import com.feedle.feedleapi.Services.UserService;
import com.feedle.feedleapi.Services.UserServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/feedle")// localhost:port/feedle
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    User authorizeUser() {
        List<User> users = userService.getUser();
        return new User();
    }

    @PostMapping("/user")
    void registerUser(@RequestBody User user) {
        userService.registerUser(user);
    }

    @DeleteMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    void DeleteUser(@RequestParam int Id) {
        userService.deleteUser(Id);

    }

    @PatchMapping("/user")
    void UpdateUser(@RequestParam int Id, @RequestBody User user) {
        userService.updateUser(user);

    }
    /*
    private User seedAuthorazition(String username, String password) {
        ArrayList<Post> posts = new ArrayList<>();
        ArrayList<UserConversation> conv = new ArrayList<>();
        User user1 = new User();

        user1.setUsername("Nicolai");
        user1.setPassword("123abc");
        user1.setSecurityLevel("admin");
        user1.setDisplayedUsername("Nicolai");
        user1.setUserConversations(conv);
        user1.setUserPosts(posts);
        User user2 = new User();
        user2.setUsername("Marton");
        user2.setPassword("123abc");
        user2.setSecurityLevel("user");
        user1.setDisplayedUsername("Nicolai");
        user1.setUserConversations(conv);
        user1.setUserPosts(posts);
        User Default = new User();
        Default.setUsername("Username or Password is wrong");

        if(username.equals(user1.getUsername())&& password.equals(user1.getPassword())){
            return user1;
        }
        if(username.equals(user2.getUsername())&& password.equals(user2.getPassword())){
            return user2;
        }
        return Default;

     */


//        users.add(user1);
//        users.add(user2);
//        User u2= new User();
//        u2.setUserName("default");
//        for (User u:users) {
//            if(u.getUserName().equals(username) && u.getPassword().equals(password)){
//                return u;
//            }else if(u.getUserName().equals(username)){
//                u2.setUserName("User not found");
//                return u2;
//            }else if(u.getPassword().equals(password)){
//                u2.setUserName("Incorrect password");
//            }
//        }
//        return u2;
    //return null;

}
