package com.feedle.feedleapi.Networking;

import com.feedle.feedleapi.Models.User;

import java.util.List;

public class GetUsersResponse extends Request {
    private List<User> users;
    public GetUsersResponse(List<User> users)
    {
        super(RequestType.GetUsers);
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }
}
