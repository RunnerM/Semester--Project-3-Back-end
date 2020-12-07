package com.feedle.feedleapi.Services;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feedle.feedleapi.Models.Post;
import com.feedle.feedleapi.Models.User;
import com.feedle.feedleapi.Networking.*;
import com.feedle.feedleapi.Services.NetworkService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
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
    private Gson gson = new Gson();

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
            //String requestAsJson = mapper.writeValueAsString(addPostRequest);
            String requestAsJson = gson.toJson(addPostRequest);
            out.write(requestAsJson.getBytes());
            System.out.println("AddPostRequestSent");
            byte[] response = in.readAllBytes();
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
            // String requestAsJson = mapper.writeValueAsString(postUserRequest);
            String requestAsJson = gson.toJson(postUserRequest);
            out.write(requestAsJson.getBytes());
            System.out.println("AddUserRequestSent");
            byte[] response = in.readAllBytes();
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
            //String requestAsJson = mapper.writeValueAsString(getUsersRequest);
            String requestAsJson = gson.toJson(getUsersRequest);
            byte[] messageInBytes = requestAsJson.getBytes();
            out.write(messageInBytes);
            System.out.println("GetUsersRequestSent");
            byte[] response = new byte[1024];
            in.read(response,0,response.length);
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
            byte[] messageInBytes = requestAsJson.getBytes();
            out.write(messageInBytes);
            System.out.println("GetPostsRequestSent");
            byte[] response = new byte[1024];
            in.read(response,0,response.length);
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
           // String requestAsJson = mapper.writeValueAsString(updatePostRequest);
            String requestAsJson = gson.toJson(updatePostRequest);
            byte[] messageInBytes = requestAsJson.getBytes();
            out.write(messageInBytes);
            System.out.println("UpdatePostRequestSent");
            byte[] response = in.readAllBytes();
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
           // String requestAsJson = mapper.writeValueAsString(updateUserRequest);
            String requestAsJson = gson.toJson(updateUserRequest);
            byte[] messageInBytes = requestAsJson.getBytes();
            out.write(messageInBytes);
            System.out.println("UpdateUserRequestSent");
            byte[] response = in.readAllBytes();
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
            //String requestAsJson = mapper.writeValueAsString(deleteUserRequest);
            String requestAsJson = gson.toJson(deleteUserRequest);
            byte[] messageInBytes = requestAsJson.getBytes();
            out.write(messageInBytes);
            System.out.println("DeleteUserRequest");
            byte[] response = in.readAllBytes();
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
            //String requestAsJson = mapper.writeValueAsString(deletePostRequest);
            String requestAsJson = gson.toJson(deletePostRequest);
            byte[] messageInBytes = requestAsJson.getBytes();
            out.write(messageInBytes);
            System.out.println("DeletePostRequest");
            byte[] response = in.readAllBytes();
            DeletePostRequest deletePostResponse = gson.fromJson(parseJson(response), DeletePostRequest.class);
            return deletePostResponse.getPostId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    private JsonReader parseJson(byte[] bytes)
    {
        JsonReader reader = new JsonReader(new StringReader(new String(bytes)));
        reader.setLenient(true);
        return reader;
    }
}
