package com.bhavik.services;

import com.bhavik.models.User;

import java.util.List;

public interface UserService {
    public User registerUser(User user);

    public User findUserById(   Integer userid) throws Exception;

    public User findUserByEmail(String email);

    public User updateUser(User user,Integer userid) throws Exception;

    public User followUser(Integer userid1,Integer userid2) throws Exception;

    public List<User> searchUser(String query);

    public String deleteUser(Integer id) throws Exception;
}
