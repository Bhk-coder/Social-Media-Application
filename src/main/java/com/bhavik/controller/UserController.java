package com.bhavik.controller;


import com.bhavik.models.User;
import com.bhavik.repository.UserRepository;
import com.bhavik.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @GetMapping("/api/users")
    public List<User> getUsers() {

        List<User> users = userRepository.findAll();


        return users;
    }

    @GetMapping("/api/users/{userId}")
    public User getUserById(@PathVariable("userId") Integer id) throws Exception {

        return userservice.findUserById(id);
    }



    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userservice;

    @PostMapping("/users")
    public User createuser(@RequestBody User user) {
        User saveduser = userservice.registerUser(user);
        return saveduser;
    }

    @PutMapping("/api/users/{userid}")
    public User updateUser(@RequestBody User user, @PathVariable Integer userid) throws Exception {
        return userservice.updateUser(user, userid);
    }

    @PutMapping("/api/users/{userid1}/{userid2}")
    public User followUserHandler(@PathVariable Integer userid1, @PathVariable Integer userid2) throws Exception {
        User user = userservice.followUser(userid1, userid2);
        return user;

    }

    @GetMapping("/api/users/search")
    public List<User> searchUser(@RequestParam("query") String query) {
        return userservice.searchUser(query);
    }



    @DeleteMapping("/api/users/{userid}")
    public String deleteUser(@PathVariable Integer userid) throws Exception {
        userservice.deleteUser(userid);
        return userservice.deleteUser(userid);

    }
}






