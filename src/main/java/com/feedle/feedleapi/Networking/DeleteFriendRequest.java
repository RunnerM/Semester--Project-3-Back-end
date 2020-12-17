package com.feedle.feedleapi.Networking;

public class DeleteFriendRequest extends Request {
    private int userFriendId;
    public DeleteFriendRequest(int userFriendId)
    {
        super(RequestType.DeleteFriendRequest);
        this.userFriendId = userFriendId;
    }

    public int getUserFriendId() {
        return userFriendId;
    }
}
