package com.feedle.feedleapi.Controllers;

import com.feedle.feedleapi.Models.Comment;
import com.feedle.feedleapi.Models.Post;
import com.feedle.feedleapi.Services.NewsService;
import com.feedle.feedleapi.Services.NewsServiceManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class NewsController {
    NewsService service = new NewsServiceManager();

    @GetMapping("/posts")
    ArrayList<Post> GetPosts() {
        return service.getAllNews();
    }

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    void CreatePost(@RequestBody Post post) {
        service.addNews(post);
    }

    @DeleteMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    void DeletePostById(@RequestParam int Id) {
        service.removeNews(Id);
    }

    @PostMapping("/posts/comment")
    void AddComment(@RequestParam int Id, @RequestBody Comment comment) {
        service.addCommentToPost(Id, comment);
    }
//    @PostMapping("/posts/rate")
//    void AddRate(@RequestParam int Id, @RequestParam String rating){
//
//    }
}
