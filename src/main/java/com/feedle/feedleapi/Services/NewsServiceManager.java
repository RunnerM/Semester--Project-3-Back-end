package com.feedle.feedleapi.Services;

import com.feedle.feedleapi.Models.Comment;
import com.feedle.feedleapi.Models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceManager implements NewsService {

    private NetworkService networkService;
    private UserService userService;
    private List<Post> posts;


    @Autowired
    public NewsServiceManager(NetworkService networkService, UserService userService) {
        this.networkService = networkService;
        this.posts = networkService.getAllPost();
        this.userService = userService;
    }

    @Override
    public List<Post> getAllPost() {
        return this.posts;
    }

    @Override
    public void addPost(Post post) {
        Post acceptedPost = networkService.addPost(post);
        if (acceptedPost != null) {
            posts.add(acceptedPost);
            userService.addPostForUser(post.userId,acceptedPost);
        }
    }

    @Override
    public void removePost(int id) {
        int newId = networkService.deletePost(id);
        if (newId != -1) {
            for (int i = 0; i < posts.size(); i++) {
                if (posts.get(i).id == newId) {
                    for (int j = 0; j< userService.getUser().size(); j++)
                    {
                        if (userService.getUser().get(j).id == posts.get(i).userId)
                        {
                            userService.getUser().get(j).userPosts.remove(posts.get(i));
                        }
                    }
                    posts.remove(i);
                    break;
                }
            }
        } else System.out.println("PostForRemoveNotFound");
    }

    @Override
    public void updatePost(Post post) {
        Post postForUpdate = networkService.updatePost(post);
        if (postForUpdate != null) {
            for (int i = 0; i < posts.size(); i++) {
                if (posts.get(i).id == postForUpdate.id) {
                    posts.set(i, postForUpdate);
                    break;
                }
            }
        } else System.out.println("BadPostForUpdateGiven");
    }

    @Override
    public void addCommentToPost(int PostId, Comment comment) {
        Comment commentResponse = networkService.postComment(comment);
        if (commentResponse != null) {
            for (int i = 0; i < posts.size(); i++) {
                if (posts.get(i).id == PostId) {
                    posts.get(i).comments.add(commentResponse);
                    break;
                }
            }
        }
    }

    @Override
    public Boolean deleteComment(int CommentId, int PostId) {

        Post postToBeUpdated = null;
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).id == PostId) {
                postToBeUpdated = posts.get(i);
                break;
            }
        }
        if (postToBeUpdated != null) {
            int idOfDeletion = networkService.deleteComment(CommentId);
            if (idOfDeletion != -1) {
                for (int i = 0; i < postToBeUpdated.comments.size(); i++) {
                    if (postToBeUpdated.comments.get(i).id == CommentId) {
                        postToBeUpdated.comments.remove(postToBeUpdated.comments.get(i));
                        return true;
                    }
                }
            }
        }
        return false;

    }
}
