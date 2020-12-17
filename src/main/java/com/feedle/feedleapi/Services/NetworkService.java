package com.feedle.feedleapi.Services;

import com.feedle.feedleapi.Models.*;

import java.util.ArrayList;
import java.util.List;

public interface NetworkService {
    Post addPost(Post post);

    User addUser(User user);

    List<User> getAllUser();

    List<Post> getAllPost();

    Post updatePost(Post post);

    User updateUser(User user);

    int deleteUser(int userId);

    int deletePost(int postId);

    Comment postComment(Comment comment);

    Message sendMessage(Message message);

    int deleteComment(int commentId);

    FriendRequestNotification makeFriendRequest(FriendRequestNotification friendRequestNotification);

    List<UserFriend> respondToFriendRequest(FriendRequestNotification friendRequestNotification,boolean status);

    UserSubscription subscribeToUser(UserSubscription userSubscription);

    int unsubscribeFromUser(int subscriptionId);

    List<UserConversation> addConversation(Conversation conversation,int creatorId, int withWhomId);

    int deleteFriend (int friendUserId);

    int deletePostReaction(int postReactionId);

    PostReaction makePostReaction(PostReaction postReaction);

    PostReaction updatePostReaction(PostReaction postReaction);

}
