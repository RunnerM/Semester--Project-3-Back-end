package com.feedle.feedleapi.Services;

import com.feedle.feedleapi.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

@Service
public class UserServiceManager implements UserService {

    private NetworkService networkService;
    private List<User> users;

    @Autowired
    public UserServiceManager(NetworkService networkService) {
        this.networkService = networkService;
        users = networkService.getAllUser();
    }

    @Override
    public Boolean registerUser(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).username.equals(user.username))
                return false;
        }
        User newUser = networkService.addUser(user);
        if (newUser != null) {
            this.users.add(newUser);
            return true;
        } else return false;
    }

    @Override
    public User authorizeUser(String username, String password) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).username.equals(username)) {
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
        if (acceptId != -1) {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).id == acceptId) {
                    users.remove(i);
                    break;
                }
            }
        } else System.out.println("UserToDeleteIsNotFound");
    }

    @Override
    public List<User> getUsers() {
        return this.users;
    }

    @Override
    public User updateUser(User user) {
        User updatedUser = networkService.updateUser(user);
        if (updatedUser != null) {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).id == updatedUser.id) {
                    users.set(i, updatedUser);
                    break;
                }
            }
        }
        return updatedUser;
    }

    @Override
    public UserInformation getUserInformationById(int id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).id == id) {
                UserInformation userInformation = new UserInformation();
                userInformation.id = users.get(i).id;
                userInformation.userName = users.get(i).username;
                userInformation.userFriends = users.get(i).userFriends;
                userInformation.userSubscriptions = users.get(i).userSubscriptions;
                userInformation.userImageSrc = users.get(i).userImageSrc;
                return userInformation;
            }
        }
        return null;
    }

    @Override
    public Boolean sendMessage(Message message) {
        Message messageResponse = networkService.sendMessage(message);
        for (int i = 0; i < users.size(); i++)
            for (int j = 0; j < users.get(i).userConversations.size(); j++) {
                if (messageResponse.conversationId == users.get(i).userConversations.get(j).conversationId) {
                    users.get(i).userConversations.get(j).conversation.messages.add(message);
                }
            }
        return true;
    }


    @Override
    public List<UserConversation> getUserConversationsByUserId(int id) {
        User user = getUserById(id);
        if (user != null) {
            return user.userConversations;
        }
        return null;
    }

    private User getUserById(int userId) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).id == userId)
                return users.get(i);
        }
        return null;
    }

    @Override
    public Boolean checkIfTheLastMessageIdIsEqualsToGivenId(int givenId, int userId) {
        List<UserConversation> userConversations = this.getUserConversationsByUserId(userId);
        int max = -1;
        for (int i = 0; i < userConversations.size(); i++) {
            int currentMax = userConversations.get(i).getLastMessageId();
            if (currentMax > max)
                max = currentMax;
        }
        if (max == -1 || max == givenId || max > givenId) {
            return false;
        } else
            return true;
    }

    @Override
    public Boolean makeFriendRequest(FriendRequestNotification friendRequestNotification) {
        FriendRequestNotification echoFriendRequest = new FriendRequestNotification();
        echoFriendRequest.userId = friendRequestNotification.potentialFriendUserId;
        echoFriendRequest.creatorId = friendRequestNotification.creatorId;
        echoFriendRequest.creatorUserName = friendRequestNotification.creatorUserName;
        echoFriendRequest.content = friendRequestNotification.content;
        echoFriendRequest.potentialFriendUserId = friendRequestNotification.userId;
        echoFriendRequest.potentialFriendUserName = friendRequestNotification.potentialFriendUserName;

        FriendRequestNotification friendRequestNotificationResponse = networkService.makeFriendRequest(friendRequestNotification);
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).id == friendRequestNotificationResponse.userId) {
                users.get(i).friendRequestNotifications.add(friendRequestNotificationResponse);
                break;
            }
        }
        FriendRequestNotification echoRequestNotificationResponse = networkService.makeFriendRequest(echoFriendRequest);
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).id == echoRequestNotificationResponse.userId) {
                users.get(i).friendRequestNotifications.add(echoRequestNotificationResponse);
                break;
            }
        }
        return true;
    }

    @Override
    public Boolean addConversation(Conversation conversation, int creatorId, int withWhomId) {
        List<UserConversation> userConversations = networkService.addConversation(conversation, creatorId, withWhomId);
        User user = null;
        User otherUser = null;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).id == userConversations.get(0).userId) {
                user = users.get(i);
                break;
            }
        }
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).id == userConversations.get(1).userId) {
                otherUser = users.get(i);
                break;
            }
        }
        if (user != null && otherUser != null) {
            user.userConversations.add(userConversations.get(0));
            otherUser.userConversations.add(userConversations.get(1));
            return true;
        }
        return false;
    }

    @Override
    public Boolean responseToFriendRequest(FriendRequestNotification friendRequestNotification, boolean status) {
        List<UserFriend> userFriends = networkService.respondToFriendRequest(friendRequestNotification, status);
        if (userFriends != null) {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).id == friendRequestNotification.potentialFriendUserId) {
                    for (int j = 0; j < users.get(j).friendRequestNotifications.size(); j++) {
                        if (users.get(i).friendRequestNotifications.get(j).creatorId == friendRequestNotification.creatorId && users.get(i).friendRequestNotifications.get(j).userId == friendRequestNotification.potentialFriendUserId)
                            users.get(i).friendRequestNotifications.remove(j);
                    }
                    users.get(i).userFriends.add(userFriends.get(1));
                    break;
                }
            }
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).id == friendRequestNotification.userId) {
                    users.get(i).friendRequestNotifications.remove(friendRequestNotification);
                    users.get(i).userFriends.add(userFriends.get(0));
                    break;
                }
            }
            return true;

        }
        return false;
    }

    @Override
    public Boolean subscribeToUser(UserSubscription userSubscription) {
        UserSubscription userSubscriptionResponse = networkService.subscribeToUser(userSubscription);
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).id == userSubscriptionResponse.userId) {
                users.get(i).userSubscriptions.add(userSubscriptionResponse);
                return true;
            }
        }
        return false;

    }

    @Override
    public Boolean unsubscribeFromUser(int subscriptionId, int userId) {
        int toDelete = networkService.unsubscribeFromUser(subscriptionId);
        System.out.println(toDelete);
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).id == userId) {
                for (int j = 0; j < users.get(i).userSubscriptions.size(); j++) {
                    if (users.get(i).userSubscriptions.get(j).subscriptionId == toDelete) {
                        users.get(i).userSubscriptions.remove(j);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Boolean checkIfTheLastNotificationIdIsEqualsToGivenId(int userId, int notificationId) {
        List<FriendRequestNotification> friendRequestNotificationsOfUser = getUserById(userId).friendRequestNotifications;
        int max = -1;
        for (int i = 0; i < friendRequestNotificationsOfUser.size(); i++) {
            if (friendRequestNotificationsOfUser.get(i).friendRequestId > max)
                max = friendRequestNotificationsOfUser.get(i).friendRequestId;
        }
        return max > notificationId;
    }

    @Override
    public List<FriendRequestNotification> getFriendNotificationsForUser(int userId) {
        List<FriendRequestNotification> friendRequestNotifications = getUserById(userId).friendRequestNotifications;
        return friendRequestNotifications;
    }

    @Override
    public void addPostForUser(int userId, Post post) {
        User user = getUserById(userId);
        if (user.userPosts == null) {
            user.userPosts = new ArrayList<>();
        }
        user.userPosts.add(post);
    }

    @Override
    public Boolean deleteFriend(int userFriendId) {
        int userFriendIdToDelete = networkService.deleteFriend(userFriendId);
        int friendIdToRemove = -1;
        int userIdToRemove = -1;
        for (int i = 0; i < this.users.size(); i++) {
            for (int j = 0; j < users.get(i).userFriends.size(); j++) {
                if (users.get(i).userFriends.get(j).userFriendId == userFriendIdToDelete) {
                    UserFriend toRemove = users.get(i).userFriends.get(j);
                    friendIdToRemove = toRemove.friendId;
                    userIdToRemove = toRemove.userId;
                    users.get(i).userFriends.remove(toRemove);
                    break;
                }
            }
        }
        User user = this.getUserById(friendIdToRemove);
        for (int i = 0; i < user.userFriends.size(); i++) {
            if (user.userFriends.get(i).friendId == userIdToRemove) {
                UserFriend toRemove = user.userFriends.get(i);
                user.userFriends.remove(toRemove);
                return true;
            }
        }
        return false;
    }
}
