package com.feedle.feedleapi.Controllers;

import com.feedle.feedleapi.Models.Comment;
import com.feedle.feedleapi.Models.Post;
import com.feedle.feedleapi.Services.NewsService;
import com.feedle.feedleapi.Services.NewsServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/feedle")// localhost:port/feedle
public class NewsController {
    private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/posts")
    List<Post> GetPosts() {
        return newsService.getAllPost();
    }

    @GetMapping("/posts/authorized")
    List<Post> GetPostsForUser(@RequestParam int id) {return  newsService.getAllPost();}


    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    void CreatePost(@RequestBody Post post) {
        newsService.addPost(post);
    }

    @DeleteMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    void DeletePostById(@RequestParam int Id) {
        newsService.removePost(Id);
    }

    @PostMapping("/posts/comment")
    void AddComment(@RequestParam int Id, @RequestBody Comment comment) {
        newsService.addCommentToPost(Id, comment);
    }
//    @PostMapping("/posts/rate")
//    void AddRate(@RequestParam int Id, @RequestParam String rating){
//
//    }
}
