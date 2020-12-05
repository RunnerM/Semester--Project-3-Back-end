package com.feedle.feedleapi.Networking;

public class PostUserRequest extends Request {

    private String userAsJson;


    public PostUserRequest(String userAsJson) {
        super(RequestType.PostUser);
        this.userAsJson = userAsJson;
    }
}
