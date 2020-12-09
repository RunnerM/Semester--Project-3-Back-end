package com.feedle.feedleapi.Services;

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
import java.nio.ByteBuffer;
import java.util.List;

@Service
public class NetworkServiceManager implements NetworkService {

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
            AddPostRequest addPostRequest = new AddPostRequest(post);
            String requestAsJson = gson.toJson(addPostRequest);
            send(out, requestAsJson);
            System.out.println("AddPostRequestSent");
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
            System.out.println("DeletePostRequest");
            String response = read(in);
            DeletePostRequest deletePostResponse = gson.fromJson(parseJson(response), DeletePostRequest.class);
            return deletePostResponse.getPostId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
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
