package com.feedle.feedleapi.Services;

import com.feedle.feedleapi.Models.*;
import com.feedle.feedleapi.Networking.*;
import com.feedle.feedleapi.Services.NetworkService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.List;

@Service
public class    NetworkServiceManager implements NetworkService {

    private Socket socket;
    private InputStream in;
    private OutputStream out;
    private Gson gson = new Gson();

    private static int PORT = 5000;
    private static String HOST = "localHost";

    public NetworkServiceManager() throws Exception {
        this.socket = new Socket(HOST, PORT);
        in = socket.getInputStream();
        out = socket.getOutputStream();
    }

    @Override
    public Post addPost(Post post) {
        try {
            System.out.println(post.authorUserName);
            AddPostRequest addPostRequest = new AddPostRequest(post);
            String requestAsJson = gson.toJson(addPostRequest);
            send(out, requestAsJson);
            String response = read(in);
            AddPostRequest addPostResponse = gson.fromJson(parseJson(response), AddPostRequest.class);
            return addPostResponse.getPost();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User addUser(User user) {
        try {
            PostUserRequest postUserRequest = new PostUserRequest(user);
            String requestAsJson = gson.toJson(postUserRequest);
            send(out, requestAsJson);
            System.out.println("AddUserRequestSent");
            String response = read(in);
            PostUserRequest postUserResponse = gson.fromJson(parseJson(response), PostUserRequest.class);
            return postUserResponse.getUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAllUser() {
        try {
            GetUsersRequest getUsersRequest = new GetUsersRequest();
            String requestAsJson = gson.toJson(getUsersRequest);
            send(out, requestAsJson);
            System.out.println("GetUsersRequestSent");
            String response = read(in);
            System.out.println(response);
            GetUsersResponse getUsersResponse = gson.fromJson(parseJson(response), GetUsersResponse.class);
            return getUsersResponse.getUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Post> getAllPost() {
        try {
            GetPostsRequest getPostsRequest = new GetPostsRequest();
            String requestAsJson = gson.toJson(getPostsRequest);
            send(out, requestAsJson);
            System.out.println("GetPostsRequestSent");
            String response = read(in);
            GetPostsResponse getPostsResponse = gson.fromJson(parseJson(response), GetPostsResponse.class);
            return getPostsResponse.getPostList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Post updatePost(Post post) {
        try {
            UpdatePostRequest updatePostRequest = new UpdatePostRequest(post);
            String requestAsJson = gson.toJson(updatePostRequest);
            send(out, requestAsJson);
            System.out.println("UpdatePostRequestSent");
            String response = read(in);
            UpdatePostRequest updatePostResponse = gson.fromJson(parseJson(response), UpdatePostRequest.class);
            return updatePostResponse.getPost();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User updateUser(User user) {
        try {
            UpdateUserRequest updateUserRequest = new UpdateUserRequest(user);
            String requestAsJson = gson.toJson(updateUserRequest);
            send(out, requestAsJson);
            System.out.println("UpdateUserRequestSent");
            String response = read(in);
            UpdateUserRequest updateUserResponse = gson.fromJson(parseJson(response), UpdateUserRequest.class);
            return updateUserResponse.getUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int deleteUser(int userId) {
        try {
            DeleteUserRequest deleteUserRequest = new DeleteUserRequest(userId);
            String requestAsJson = gson.toJson(deleteUserRequest);
            send(out, requestAsJson);
            System.out.println("DeleteUserRequest");
            String response = read(in);
            DeleteUserRequest deleteUserResponse = gson.fromJson(parseJson(response), DeleteUserRequest.class);
            return deleteUserResponse.getUserId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;

    }

    @Override
    public int deletePost(int postId) {
        try {
            DeletePostRequest deletePostRequest = new DeletePostRequest(postId);
            String requestAsJson = gson.toJson(deletePostRequest);
            send(out, requestAsJson);
            System.out.println("DeletePostRequest " + requestAsJson);
            String response = read(in);
            DeletePostRequest deletePostResponse = gson.fromJson(parseJson(response), DeletePostRequest.class);
            return deletePostResponse.getPostId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Comment postComment(Comment comment) {
        try {
            AddCommentRequest addCommentRequest = new AddCommentRequest(comment);
            String requestAsJson = gson.toJson(addCommentRequest);
            send(out,requestAsJson);
            System.out.println("PostCommentRequest");
            String response = read(in);
            AddCommentRequest addCommentResponse = gson.fromJson(parseJson(response),AddCommentRequest.class);
            return addCommentResponse.getComment();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Message sendMessage(Message message) {
        try {
            SendMessageRequest sendMessageRequest = new SendMessageRequest(message);
            String requestAsJson = gson.toJson(sendMessageRequest);
            send(out,requestAsJson);
            System.out.println("SendMessageRequest");
            String response = read(in);
            SendMessageRequest sendMessageResponse = gson.fromJson(parseJson(response),SendMessageRequest.class);
            return sendMessageResponse.getMessage();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int deleteComment(int commentId) {
        try {
            DeleteCommentRequest deleteCommentRequest = new DeleteCommentRequest(commentId);
            String requestAsJson = gson.toJson(deleteCommentRequest);
            send(out,requestAsJson);
            System.out.println("SendDeleteComment");
            String response = read(in);
            DeleteCommentRequest deleteCommentResponse = gson.fromJson(parseJson(response),DeleteCommentRequest.class);
            return deleteCommentRequest.getCommentId();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<UserConversation>  addConversation(Conversation conversation, int creatorId, int withWhomId) {
        try {
            AddConversationRequest addConversationRequest = new AddConversationRequest(conversation,creatorId,withWhomId);
            String requestAsJson = gson.toJson(addConversationRequest);
            send(out,requestAsJson);
            System.out.println("AddConversation");
            String response = read(in);
            System.out.println(response);
            AddConversationResponse addConversationResponse = gson.fromJson(parseJson(response),AddConversationResponse.class);
            System.out.println(addConversationResponse.getUserConversations().get(0).conversation.messages.size());
            return addConversationResponse.getUserConversations();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public FriendRequestNotification makeFriendRequest(FriendRequestNotification friendRequestNotification) {
        try {
            MakeFriendRequest makeFriendRequest = new MakeFriendRequest(friendRequestNotification);
            String requestAsJson = gson.toJson(makeFriendRequest);
            send(out,requestAsJson);
            System.out.println("MakeFriendRequestCalled");
            String response = read(in);
            MakeFriendRequest makeFriendResponse = gson.fromJson(parseJson(response),MakeFriendRequest.class);
            return makeFriendResponse.getFriendRequestNotification();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<UserFriend> respondToFriendRequest(FriendRequestNotification friendRequestNotification,boolean status) {
        try{
            RespondToFriendRequest respondToFriendRequest = new RespondToFriendRequest(status,friendRequestNotification);
            String requestAsJson = gson.toJson(respondToFriendRequest);
            send(out,requestAsJson);
            System.out.println("RespondToFriendCalled");
            String response = read(in);
            System.out.println(response);
            RespondToFriendResponse respondToFriendResponse = gson.fromJson(parseJson(response),RespondToFriendResponse.class);
            return respondToFriendResponse.getUserFriends();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserSubscription subscribeToUser(UserSubscription userSubscription) {
        try {
            SubscribeToUserRequest subscribeToUserRequest = new SubscribeToUserRequest(userSubscription);
            String requestAsJson = gson.toJson(subscribeToUserRequest);
            send(out,requestAsJson);
            System.out.println("SubscribeToUserCalled");
            String response = read(in);
            SubscribeToUserRequest subscribeToUserResponse = gson.fromJson(parseJson(response),SubscribeToUserRequest.class);
            return subscribeToUserRequest.getUserSubscription();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int unsubscribeFromUser(int subscriptionId) {
        try {
            UnsubscribeRequest unsubscribeRequest = new UnsubscribeRequest(subscriptionId);
            String requestAsJson = gson.toJson(unsubscribeRequest);
            send(out,requestAsJson);
            System.out.println("UnsubscribeFromUserCalled");
            String response = read(in);
            UnsubscribeRequest unsubscribeResponse = gson.fromJson(parseJson(response),UnsubscribeRequest.class);
            return unsubscribeResponse.getSubscriptionId();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int deleteFriend(int friendUserId) {
        try {
            DeleteFriendRequest deleteFriendRequest = new DeleteFriendRequest(friendUserId);
            String requestAsJson = gson.toJson(deleteFriendRequest);
            send(out,requestAsJson);
            System.out.println("DeleteFriend");
            String response = read(in);
            DeleteFriendRequest deleteFriendResponse = gson.fromJson(parseJson(response),DeleteFriendRequest.class);
            return deleteFriendResponse.getUserFriendId();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int deletePostReaction(int postReactionId) {
        try {
            DeleteReactionRequest
                    deleteReactionRequest = new DeleteReactionRequest(postReactionId);
            String requestAsJson = gson.toJson(deleteReactionRequest);
            send(out,requestAsJson);
            System.out.println("DeletePostReaction");
            String response = read(in);
            DeleteReactionRequest deleteReactionResponse = gson.fromJson(parseJson(response),DeleteReactionRequest.class);
            return deleteReactionResponse.getPostReactionId();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public PostReaction makePostReaction(PostReaction postReaction) {
        try {
            MakeReactionRequest
                    makeReactionRequest = new MakeReactionRequest(postReaction);
            String requestAsJson = gson.toJson(makeReactionRequest);
            send(out,requestAsJson);
            System.out.println("MakePostReaction");
            String response = read(in);
            MakeReactionRequest makeReactionResponse = gson.fromJson(parseJson(response),MakeReactionRequest.class);
            return makeReactionResponse.getPostReaction();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PostReaction updatePostReaction(PostReaction postReaction) {
        try {
            UpdateReactionRequest
                    updateReactionRequest = new UpdateReactionRequest(postReaction);
            String requestAsJson = gson.toJson(updateReactionRequest);
            send(out,requestAsJson);
            System.out.println("UpdatePostReaction");
            String response = read(in);
            UpdateReactionRequest updateReactionResponse = gson.fromJson(parseJson(response),UpdateReactionRequest.class);
            return updateReactionResponse.getPostReaction();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private JsonReader parseJson(String json) {
        JsonReader reader = new JsonReader(new StringReader(json));
        reader.setLenient(true);
        return reader;
    }

    private String read(InputStream inputStream) throws IOException {
        byte[] lenBytes = new byte[4];
        inputStream.read(lenBytes, 0, 4);
        int len = (((lenBytes[3] & 0xff) << 24) | ((lenBytes[2] & 0xff) << 16) |
                ((lenBytes[1] & 0xff) << 8) | (lenBytes[0] & 0xff));
        byte[] receivedBytes = new byte[len];
        inputStream.read(receivedBytes, 0, len);
        String receivedFromClient = new String(receivedBytes, 0, len);
        return receivedFromClient;
    }

    private void send(OutputStream outputStream, String toSend) throws IOException {
        byte[] toSendBytes = toSend.getBytes();
        int toSendLen = toSendBytes.length;
        byte[] toSendLenBytes = new byte[4];
        toSendLenBytes[0] = (byte) (toSendLen & 0xff);
        toSendLenBytes[1] = (byte) ((toSendLen >> 8) & 0xff);
        toSendLenBytes[2] = (byte) ((toSendLen >> 16) & 0xff);
        toSendLenBytes[3] = (byte) ((toSendLen >> 24) & 0xff);
        outputStream.write(toSendLenBytes);
        outputStream.write(toSendBytes);
    }


}
