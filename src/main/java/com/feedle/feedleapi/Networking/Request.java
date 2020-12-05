package com.feedle.feedleapi.Networking;

public class Request {
    public RequestType type;

    public Request(RequestType type) {
        this.type = type;
    }

    public RequestType getType() {
        return type;
    }
}
