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
    private List<Post> posts;


    @Autowired
    public NewsServiceManager(NetworkService networkService)
    {
        this.networkService = networkService;
        this.posts = networkService.getAllPost();
    }
    @Override
    public List<Post> getAllPost() {
        return this.posts;
    }

    @Override
    public void addPost(Post post) {
        Post acceptedPost = networkService.addPost(post);
        if (acceptedPost != null)
        {
            posts.add(post);
        }
    }

    @Override
    public void removePost(int id) {
        int newId = networkService.deletePost(id);
        if (newId != -1)
        {
            for (int i =0;i<posts.size();i++)
            {
                if (posts.get(i).id == newId) {
                    posts.remove(i);
                    break;
                }
            }
        }
        else System.out.println("PostForRemoveNotFound");
    }

    @Override
    public void updatePost(Post post) {
        Post postForUpdate = networkService.updatePost(post);
        if (postForUpdate!=null){
            for (int i = 0; i<posts.size(); i++)
            {
                if (posts.get(i).id == postForUpdate.id)
                {
                    posts.set(i,postForUpdate);
                    break;
                }
            }
        }
        else System.out.println("BadPostForUpdateGiven");
    }

    @Override
    public void addCommentToPost(int PostId, Comment comment) {
        for (int i = 0; i<posts.size(); i++)
        {
            if (posts.get(i).id == PostId)
            {
                posts.get(i).comments.add(comment);
                Post newPost = networkService.updatePost(posts.get(i));
                posts.set(i,newPost);
                break;
            }
        }
    }
}
