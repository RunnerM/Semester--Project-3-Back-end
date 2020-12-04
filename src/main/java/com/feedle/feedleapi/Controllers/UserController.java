package com.feedle.feedleapi.Controllers;

import com.feedle.feedleapi.Models.User;
import com.feedle.feedleapi.Services.UserService;
import com.feedle.feedleapi.Services.UserServiceManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedle")// localhost:port/feedle
public class UserController {
    UserService service = new UserServiceManager();

    @GetMapping("/user")
    User authorizeUser(@RequestParam String username, @RequestParam String password) {
        return service.authorizeUser(username, password);
    }

    @PostMapping("/user")
    void registerUser(@RequestBody User user) {
        service.registerUser(user);

    }

    @DeleteMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    void DeleteUser(@RequestParam int Id) {
        service.deleteUser(Id);

    }

    @PatchMapping("/user")
    void UpdateUser(@RequestParam int Id, @RequestBody User user) {
        service.updateUser(Id, user);

    }


}
