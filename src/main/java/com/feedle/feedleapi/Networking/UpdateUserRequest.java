package com.feedle.feedleapi.Networking;

import com.feedle.feedleapi.Models.User;

public class UpdateUserRequest extends Request{
    private User user;
    public UpdateUserRequest(User user)
    {
        super(RequestType.UpdateUser);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
