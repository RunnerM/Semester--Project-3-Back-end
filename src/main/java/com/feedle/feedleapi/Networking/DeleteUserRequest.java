package com.feedle.feedleapi.Networking;

public class DeleteUserRequest extends Request {
    private int userId;

    public DeleteUserRequest(int userId) {
        super(RequestType.DeleteUser);
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}
