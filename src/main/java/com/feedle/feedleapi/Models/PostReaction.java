package com.feedle.feedleapi.Models;

import lombok.Data;

@Data
public class PostReaction {
    public int postReactionId;
    public int status;
    public int userId;
    public int postId;
}
