package com.feedle.feedleapi.Controllers;

import com.feedle.feedleapi.Models.Post;
import com.feedle.feedleapi.Models.User;
import com.feedle.feedleapi.Models.UserConversation;
import com.feedle.feedleapi.Models.UserInformation;
import com.feedle.feedleapi.Services.UserService;
import com.feedle.feedleapi.Services.UserServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;


import javax.servlet.http.HttpServletResponse;
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
    User authorizeUser(@RequestParam String username, @RequestParam String password) {
        return userService.authorizeUser(username,password);
    }

    @GetMapping("/user/userinfo")
    UserInformation getUserInformation(@RequestParam int id){
        return userService.getUserInformationById(id);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    void registerUser(@RequestBody User user) {
        userService.registerUser(user);
    }

    @DeleteMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    void DeleteUser(@RequestParam int Id) {
        userService.deleteUser(Id);

    }

    @PatchMapping("/user")
    void UpdateUser(@RequestBody User user) {
         userService.updateUser(user);

    }
}
