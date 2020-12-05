package com.feedle.feedleapi.Services;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.feedle.feedleapi.Models.Post;
import com.feedle.feedleapi.Models.User;
import com.feedle.feedleapi.Services.NetworkService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

@Service
public class NetworkServiceManager implements NetworkService {

    private Socket socket;
    private int port = 0;
    private String host = "";
    private BufferedReader in;
    private PrintWriter out;
    private ObjectMapper mapper = new ObjectMapper();

    private static int PORT = 5000;
    private static String HOST = "localHost";

    public NetworkServiceManager() throws Exception {
        this.socket = new Socket(HOST, PORT);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void setUpSocketConnection(int port, String host) throws Exception {
        this.port = port;
        this.host = host;
        this.socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

    }

    @Override
    public void addPost(Post post) {

    }

    @Override
    public void addUser(User user) {
        try {
            out.write(mapper.writeValueAsString(user));
            in.readLine();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<User> getAllUser() {
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
