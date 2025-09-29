package com.bhavik.controller;


import com.bhavik.models.User;
import com.bhavik.repository.UserRepository;
import com.bhavik.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @GetMapping("/users")
    public List<User> getUsers() {

        List<User> users = userRepository.findAll();


        return users;
    }

    @GetMapping("/users/{userId}")
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

    @PutMapping("/users/{userid}")
    public User updateUser(@RequestBody User user, @PathVariable Integer userid) throws Exception {
        return userservice.updateUser(user, userid);
    }

    @PutMapping("/users/{userid1}/{userid2}")
    public User followUserHandler(@PathVariable Integer userid1, @PathVariable Integer userid2) throws Exception {
        User user = userservice.followUser(userid1, userid2);
        return user;

    }

    @GetMapping("/users/search")
    public List<User> searchUser(@RequestParam("query") String query) {
        return userservice.searchUser(query);
    }



    @DeleteMapping("/users/{userid}")
    public String deleteUser(@PathVariable Integer userid) throws Exception {
        userservice.deleteUser(userid);
        return userservice.deleteUser(userid);

    }
}






