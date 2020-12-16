package com.feedle.feedleapi.Models;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** Represents a user.
 * Getters/Setters, no argument constructor, equals(), canEqual(),
 * hashCode(), ToString() are available and generated at runtime.
 * @author Marton Pentek
 * @version 1.0
 * @since 12/2020
 */


@Data
public class User implements Serializable {
    /**
     * Represents the Id of the User generally used for identifying the user.
     */
    public int id;
    /**
     * Represents the Username.
     */

    public String username;
    /**
     * Represents the Password.
     **/
    public String password;
    /**
     * Represents displayable Username .
     **/
    public String displayedUsername;
    /**
     * Represents the posts of the user.
     **/
    public ArrayList<Post> userPosts;
    /**
     * Represents the posts of the user.
     **/
    public ArrayList<UserConversation> userConversations;
    public ArrayList<UserSubscription> userSubscriptions;
    public ArrayList<UserFriend> userFriends;
    public ArrayList<FriendRequestNotification> friendRequestNotifications;
    /**
     * Represents the security level of the user either 1 or 2
     * depending on the user privileges.
     **/
    public int securityLevel;
    public ArrayList<UserInformation> subscriptionUsersInformation;
    public String userImageSrc;
}
