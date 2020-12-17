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
    public boolean registerUser(@RequestBody User user) {
        boolean Result = userService.registerUser(user);
        if (Result)
          return true;
        else
            return false;
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
    public ResponseEntity<Boolean> saveMessage(@RequestBody Message message) {
        Boolean toChangeUserConversation = userService.sendMessage(message);
        return ResponseEntity.ok(toChangeUserConversation);
    }

    @GetMapping("/getMessages")
    public ResponseEntity<List<UserConversation>> getMessage(@RequestParam int lastMessageId, @RequestParam int userId) throws InterruptedException {
        if (userService.checkIfTheLastMessageIdIsEqualsToGivenId(lastMessageId, userId))
            return ResponseEntity.ok(userService.getUserConversationsByUserId(userId));
        return keepPollingForMessages(lastMessageId, userId);
    }

    @PatchMapping("/user/subscribe")
    public boolean subscribeToUser(@RequestBody UserSubscription userSubscription)
    {
        return this.userService.subscribeToUser(userSubscription);
    }

    @PatchMapping("/user/respondToFriendNotification")
    public boolean respondToFriendNotification (@RequestParam boolean status, @RequestBody FriendRequestNotification friendRequestNotification )
    {
       return this.userService.responseToFriendRequest(friendRequestNotification,status);
    }

    @PostMapping("/user/makeFriendRequest")
    public boolean makeFriendRequestNotification(@RequestBody FriendRequestNotification friendRequestNotification)
    {
        return userService.makeFriendRequest(friendRequestNotification);
    }

    @DeleteMapping("user/unsubscribe")
    public boolean unsubscribeFromUser(@RequestParam int userId, @RequestParam int subscriptionId)
    {
        return this.userService.unsubscribeFromUser(subscriptionId,userId);
    }

    @PostMapping("user/conversation")
    public Boolean addConversation(@RequestParam int creatorId, @RequestParam int withWhomId, @RequestBody Conversation conversation)
    {
        return this.userService.addConversation(conversation,creatorId, withWhomId);
    }

    @GetMapping("/getFriendNotifications")
    public ResponseEntity<List<FriendRequestNotification>> getFriendNotifications(@RequestParam int lastNotificationId, @RequestParam int userId) throws InterruptedException {
        if (userService.checkIfTheLastNotificationIdIsEqualsToGivenId(userId,lastNotificationId))
            return ResponseEntity.ok(userService.getFriendNotificationsForUser(userId));
        return keepPollingForNotifications(lastNotificationId,userId);
    }

    @DeleteMapping("/deleteFriend")
    public Boolean deleteFriend(@RequestParam int userFriendId)
    {
        return userService.deleteFriend(userFriendId);
    }


    private ResponseEntity<List<UserConversation>> keepPollingForMessages(@RequestParam int lastMessageId, @RequestParam int userId) throws InterruptedException {
        Thread.sleep(5000);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/getMessages?lastMessageId=" + lastMessageId + "&userId=" + userId));
        return new ResponseEntity<>(headers, HttpStatus.TEMPORARY_REDIRECT);
    }

    private ResponseEntity<List<FriendRequestNotification>> keepPollingForNotifications(@RequestParam int lastNotificationId, @RequestParam int userId) throws InterruptedException {
        Thread.sleep(5000);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/getFriendNotifications?lastNotificationId=" +lastNotificationId+"&userId="+userId));
        return new ResponseEntity<>(headers,HttpStatus.TEMPORARY_REDIRECT);

    }


}
