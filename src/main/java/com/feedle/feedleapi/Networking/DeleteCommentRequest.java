package com.feedle.feedleapi.Networking;

public class DeleteCommentRequest extends Request {
    private int commentId;

    public DeleteCommentRequest(int commentId) {
        super(RequestType.DeleteComment);
        this.commentId = commentId;
    }

    public int getCommentId() {
        return commentId;
    }
}
