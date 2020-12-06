package com.feedle.feedleapi.Services;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feedle.feedleapi.Models.Post;
import com.feedle.feedleapi.Models.User;
import com.feedle.feedleapi.Services.NetworkService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

@Service
public class NetworkServiceManager implements NetworkService {

    private Socket socket;
    private BufferedReader in;
    private DataOutputStream out;
    private ObjectMapper mapper = new ObjectMapper();

    private static int PORT = 5000;
    private static String HOST = "localHost";

    public NetworkServiceManager() throws Exception {
        this.socket = new Socket(HOST, PORT);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void addPost(Post post) {

    }

    @Override
    public void addUser(User user) {
        try {
            //out.write(mapper.writeValueAsString(user));
            in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<User> getAllUser() {
        try {
            String message = "hello";
            byte[] messageInBytes = message.getBytes();
            out.write(messageInBytes);
            System.out.println("here");
            String s = in.readLine();
            System.out.println(s);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Post> getAllPost() {
        return null;
    }

    @Override
    public void updatePost(Post post) {

    }

    @Override
    public void updateUser() {

    }

    @Override
    public void deleteUser(int UserId) {

    }

    @Override
    public void deletePost(int PostId) {

    }
}
