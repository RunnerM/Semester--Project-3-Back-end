package com.feedle.feedleapi.Services;

import com.feedle.feedleapi.Models.*;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
    void registerUser(User user);

    User authorizeUser(String username, String password);

    void deleteUser(int id);

    List<User> getUsers();

    User updateUser(User user);

    UserInformation getUserInformationById(int id);

    UserConversation sendMessage(Message message);

    List<UserConversation> getUserConversationsByUserId (int id);

    Boolean checkIfTheLastMessageIdIsEqualsToGivenId(int userId, int givenId);

    Boolean checkIfTheLastNotificationIdIsEqualsToGivenId(int userId, int notificationId);

    Boolean subscribeToUser(UserSubscription userSubscription);

    Boolean unsubscribeFromUser(int subscriptionId, int userId);

    List<FriendRequestNotification> getFriendNotificationsForUser(int userId);

    Boolean makeFriendRequest(FriendRequestNotification friendRequestNotification);

    UserConversation addConversation (Conversation conversation, int creatorId, int withWhomId);

    Boolean responseToFriendRequest(FriendRequestNotification friendRequestNotification, boolean status);

    void addPostForUser(int userId, Post post);



}
