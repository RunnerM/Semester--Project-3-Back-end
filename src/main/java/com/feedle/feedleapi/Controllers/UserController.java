package com.feedle.feedleapi.Controllers;

import com.feedle.feedleapi.Models.*;
import com.feedle.feedleapi.Services.UserService;
import com.feedle.feedleapi.Services.UserServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;


import javax.servlet.http.HttpServletResponse;
import java.net.URI;
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
    public User authorizeUser(@RequestParam String username, @RequestParam String password) {
        return userService.authorizeUser(username, password);
    }

    @GetMapping("/user/userinfo")
    public UserInformation getUserInformation(@RequestParam int id) {
        return userService.getUserInformationById(id);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public void registerUser(@RequestBody User user) {
        userService.registerUser(user);
    }

    @DeleteMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public void DeleteUser(@RequestParam int Id) {
        userService.deleteUser(Id);

    }

    @PatchMapping("/user")
    public void UpdateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<UserConversation> saveMessage(@RequestBody Message message) {
        UserConversation userConversation = userService.sendMessage(message);
        return ResponseEntity.ok(userConversation);
    }

    @GetMapping("/getMessages")
    public ResponseEntity<List<UserConversation>> getMessage(@RequestParam int lastMessageId, int userId) throws InterruptedException {
       if (userService.checkIfTheLastMessageIdIsEqualsToGivenId(userId,lastMessageId))
       {
           return ResponseEntity.ok(userService.getUserConversationsByUserId(userId));
       }
       return keepPolling(lastMessageId,userId);
    }

    private ResponseEntity<List<UserConversation>> keepPolling(@RequestParam int lastMessageId, int userId) throws InterruptedException {
        Thread.sleep(5000);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/getMessages?lastMessageId=" + lastMessageId +"&userId=" + userId));
        return new ResponseEntity<>(headers,HttpStatus.TEMPORARY_REDIRECT);
    }


}
