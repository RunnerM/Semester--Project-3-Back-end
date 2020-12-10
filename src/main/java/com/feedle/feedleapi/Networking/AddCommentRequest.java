package com.feedle.feedleapi.Networking;

import com.feedle.feedleapi.Models.Comment;

public class AddCommentRequest extends Request {
    private Comment comment;
    public AddCommentRequest(Comment comment)
    {
        super(RequestType.AddComment);
        this.comment = comment;
    }

    public Comment getComment() {
        return comment;
    }
}
