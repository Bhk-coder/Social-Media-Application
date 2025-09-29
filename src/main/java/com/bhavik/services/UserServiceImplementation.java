package com.bhavik.services;

import com.bhavik.models.User;
import com.bhavik.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        User newuser = new User();
        newuser.setEmail(user.getEmail());
        newuser.setFirstName(user.getFirstName());
        newuser.setLastName(user.getLastName());
        newuser.setPassword(user.getPassword());
        newuser.setId(user.getId());
        User saveduser = userRepository.save(newuser);
        return saveduser;
    }

    @Override
    public User findUserById(Integer userid) throws Exception {

        Optional<User> user = userRepository.findById(userid);

        if (user.isPresent()){
            return user.get();
        }
        throw new Exception("user does not exist with user-id"+ userid);

    }

    @Override
    public User findUserByEmail(String email) {
       User user = userRepository.findByEmail(email);
       return user;

    }

    @Override
    public User updateUser(User user,Integer userid) throws Exception {
        Optional<User> user1 = userRepository.findById(userid);
        if(user1.isEmpty())
            throw new Exception("Such a user does not exist" + userid);
        User olduser = user1.get();

        if(user.getFirstName()!=null)
            olduser.setFirstName(user.getFirstName());
        if(user.getLastName()!=null)
            olduser.setLastName(user.getLastName());
        if(user.getEmail()!=null)
            olduser.setEmail(user.getEmail());
        User updateduser = userRepository.save(olduser);
        return updateduser;
    }

    @Override
    public User followUser(Integer userid1, Integer userid2) throws Exception {
        // userid1 = the user who wants to follow
        // userid2 = the user to be followed

        User user1 = findUserById(userid1);  // follower
        User user2 = findUserById(userid2);  // followee

        // Ensure lists are initialized to avoid NullPointerException
        if (user2.getFollowers() == null) user2.setFollowers(new ArrayList<>());
        if (user1.getFollowings() == null) user1.setFollowings(new ArrayList<>());

        // Update followers/followings
        user2.getFollowers().add(user1.getId());
        user1.getFollowings().add(user2.getId());

        // Save both users
        userRepository.save(user1);
        userRepository.save(user2);

        return user1;  // returning the follower
    }


    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);

    }

    @Override
    public String deleteUser(Integer id) throws Exception {
        Optional<User> user1 = userRepository.findById(id);
        if (user1.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + id);
        User olduser = user1.get();
        userRepository.delete(olduser);
      return "the user has been deleted" + id;
    }
}
