package com.feedle.feedleapi.Services;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feedle.feedleapi.Models.Post;
import com.feedle.feedleapi.Models.User;
import com.feedle.feedleapi.Networking.*;
import com.feedle.feedleapi.Services.NetworkService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;
import java.util.List;

@Service
public class NetworkServiceManager implements NetworkService {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private ObjectMapper mapper = new ObjectMapper();

    private static int PORT = 5000;
    private static String HOST = "localHost";

    public NetworkServiceManager() throws Exception {
        this.socket = new Socket(HOST, PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public Post addPost(Post post) {
        try {
            AddPostRequest addPostRequest = new AddPostRequest(post);
            String requestAsJson = mapper.writeValueAsString(addPostRequest);
            out.write(requestAsJson.getBytes());
            System.out.println("AddPostRequestSent");
            byte[] response = in.readAllBytes();
            String responseAsJson = new String(response);
            System.out.println(responseAsJson);
            AddPostRequest addPostResponse = mapper.convertValue(requestAsJson, AddPostRequest.class);
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
            String requestAsJson = mapper.writeValueAsString(postUserRequest);
            out.write(requestAsJson.getBytes());
            System.out.println("AddUserRequestSent");
            byte[] response = in.readAllBytes();
            String responseAsJson = new String(response);
            PostUserRequest postUserResponse = mapper.convertValue(requestAsJson, PostUserRequest.class);
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
            String requestAsJson = mapper.writeValueAsString(getUsersRequest);
            byte[] messageInBytes = requestAsJson.getBytes();
            out.write(messageInBytes);
            System.out.println("GetUsersRequestSent");
            byte[] response = in.readAllBytes();
            String responseAsJson = new String(response);
            GetUsersResponse getUsersResponse = mapper.convertValue(responseAsJson, GetUsersResponse.class);
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
            String requestAsJson = mapper.writeValueAsString(getPostsRequest);
            byte[] messageInBytes = requestAsJson.getBytes();
            out.write(messageInBytes);
            System.out.println("GetPostsRequestSent");
            byte[] response = in.readAllBytes();
            String responseAsJson = new String(response);
            GetPostsResponse getPostsResponse = mapper.convertValue(responseAsJson, GetPostsResponse.class);
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
            String requestAsJson = mapper.writeValueAsString(updatePostRequest);
            byte[] messageInBytes = requestAsJson.getBytes();
            out.write(messageInBytes);
            System.out.println("UpdatePostRequestSent");
            byte[] response = in.readAllBytes();
            String responseAsJson = new String(response);
            UpdatePostRequest updatePostResponse = mapper.convertValue(responseAsJson, UpdatePostRequest.class);
            return updatePostRequest.getPost();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User updateUser(User user) {
        try {
            UpdateUserRequest updateUserRequest = new UpdateUserRequest(user);
            String requestAsJson = mapper.writeValueAsString(updateUserRequest);
            byte[] messageInBytes = requestAsJson.getBytes();
            out.write(messageInBytes);
            System.out.println("UpdateUserRequestSent");
            byte[] response = in.readAllBytes();
            String responseAsJson = new String(response);
            UpdateUserRequest updateUserResponse = mapper.convertValue(responseAsJson, UpdateUserRequest.class);
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
            String requestAsJson = mapper.writeValueAsString(deleteUserRequest);
            byte[] messageInBytes = requestAsJson.getBytes();
            out.write(messageInBytes);
            System.out.println("DeleteUserRequest");
            byte[] response = in.readAllBytes();
            String responseAsJson = new String(response);
            DeleteUserRequest deleteUserResponse = mapper.convertValue(responseAsJson, DeleteUserRequest.class);
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
            String requestAsJson = mapper.writeValueAsString(deletePostRequest);
            byte[] messageInBytes = requestAsJson.getBytes();
            out.write(messageInBytes);
            System.out.println("DeletePostRequest");
            byte[] response = in.readAllBytes();
            String responseAsJson = new String(response);
            DeletePostRequest deletePostResponse = mapper.convertValue(responseAsJson, DeletePostRequest.class);
            return deletePostResponse.getPostId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
