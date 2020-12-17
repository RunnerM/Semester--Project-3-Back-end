package com.feedle.feedleapi.Services;

import com.feedle.feedleapi.Models.Comment;
import com.feedle.feedleapi.Models.Post;
import com.feedle.feedleapi.Models.PostReaction;

import java.util.ArrayList;
import java.util.List;

public interface NewsService {
    List<Post> getAllPost();

    void addPost(Post post);

    void removePost(int Id);

    void updatePost(Post post);

    void addCommentToPost(int PostId, Comment comment);

    Boolean deleteComment(int CommentId, int postId);

    boolean deletePostReaction(int postReactionId);

    boolean updatePostReaction(PostReaction postReaction);

    boolean makePostReaction(PostReaction postReaction);

    Post getPostById(int postId);

    List<Post> getPostForUser(int userId);



}
