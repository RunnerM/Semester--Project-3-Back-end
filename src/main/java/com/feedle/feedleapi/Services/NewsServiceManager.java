package com.feedle.feedleapi.Services;

import com.feedle.feedleapi.Models.Comment;
import com.feedle.feedleapi.Models.Post;
import com.feedle.feedleapi.Models.PostReaction;
import com.feedle.feedleapi.Models.UserInformation;
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
                    for (int j = 0; j< userService.getUsers().size(); j++)
                    {
                        if (userService.getUsers().get(j).id == posts.get(i).userId)
                        {
                            userService.getUsers().get(j).userPosts.remove(posts.get(i));
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
                    for (int j =0; j<userService.getUsers().size(); j++)
                        if (userService.getUsers().get(j).id == posts.get(i).userId)
                            for (int k = 0; k < userService.getUsers().get(j).userPosts.size(); k++) {
                                if (posts.get(i).id == userService.getUsers().get(j).userPosts.get(k).id)
                                {
                                    userService.getUsers().get(j).userPosts.set(k,post);
                                }
                            }
                    break;
                }
            }
        }
        else System.out.println("BadPostForUpdateGiven");
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

    @Override
    public boolean updatePostReaction(PostReaction postReaction) {
        PostReaction postReactionToUpdate = networkService.updatePostReaction(postReaction);
        for (int i = 0; i < posts.size(); i++) {
            for (int j = 0; j < posts.get(i).postReactions.size(); j++) {
                if (posts.get(i).postReactions.get(j).postReactionId == postReactionToUpdate.postReactionId) {
                    posts.get(i).postReactions.set(j, postReactionToUpdate);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Post getPostById(int postId) {
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).id == postId)
                return  posts.get(i);
        }
        return null;
    }

    @Override
    public boolean makePostReaction(PostReaction postReaction) {
        PostReaction postReactionToMake = networkService.makePostReaction(postReaction);
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).id == postReactionToMake.postId) {
                posts.get(i).postReactions.add(postReactionToMake);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletePostReaction(int postReactionId) {
        int postReactionIdToDelete = networkService.deletePostReaction(postReactionId);
        for (int i = 0; i < posts.size(); i++)
            for (int j = 0; j < posts.get(i).postReactions.size(); j++) {
                if (posts.get(i).postReactions.get(j).postReactionId == postReactionIdToDelete) {
                    PostReaction toRemove = posts.get(i).postReactions.get(j);
                    posts.get(i).postReactions.remove(toRemove);
                    return true;
                }
        }
        return false;
    }

    @Override
    public List<Post> getPostForUser(int userId) {
        List<Post> postsResult = new ArrayList<>();
        UserInformation userInfo = userService.getUserInformationById(userId);
        for (int i = 0; i < posts.size(); i++) {
                for (int k = 0; k < userInfo.userSubscriptions.size(); k++) {
                    if (userInfo.userSubscriptions.get(k).subscriptionId == posts.get(i).userId) {
                        if (!postsResult.contains(posts.get(i)) && postsResult.size() < 25)
                            postsResult.add(posts.get(i));
                        break;
                    }
                }
            }
        return postsResult;
    }
}
