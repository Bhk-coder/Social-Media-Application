package com.bhavik.services;

import com.bhavik.models.Post;
import com.bhavik.models.User;
import com.bhavik.repository.PostRepository;
import com.bhavik.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Override
    public Post createNewPost(Post post, Integer userid) throws Exception {
        Post newpost = new Post();
        User user = userService.findUserById(userid);
        newpost.setCaption(post.getCaption());
        newpost.setVideo(post.getVideo());
        newpost.setImage(post.getImage());
        newpost.setTimeAtCreation(LocalDateTime.now());
        newpost.setUser(user);
       postRepository.save(newpost);
        return newpost;


    }

    @Override
    @Transactional
    public String deletePost(Integer postid, Integer userid) throws Exception {
        Post post = findPostById(postid);
        User user = userService.findUserById(userid);

        // 1️⃣ Authorization check
        if (!post.getUser().getId().equals(user.getId())) {
            throw new Exception("You cannot delete another user's post");
        }

        // 2️⃣ Remove the post from all users' saved posts to avoid FK violation
        List<User> usersWithThisPost = userRepository.findAllBySavedPostsContaining(post);
        for (User u : usersWithThisPost) {
            u.getSavedPosts().remove(post);
        }
        userRepository.saveAll(usersWithThisPost);

        // 3️⃣ Delete the post itself
        postRepository.delete(post);

        return "The post has been deleted with id " + postid;
    }


    @Override
    public List<Post> findPostByUserId(Integer userid) {
        return postRepository.findPostByUserId(userid);
    }

    @Override
    public Post findPostById(Integer postid) throws Exception {
        Optional<Post> post = postRepository.findById(postid);
        if (post.isEmpty())
            throw new Exception("Post not Found with id" + postid);
        return post.get();
    }

    @Override
    public List<Post> findAllByPost() {
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Integer postid,Integer userid) throws Exception {
        Post post = findPostById(postid);
        User user = userService.findUserById(userid);
        if(user.getSavedPosts().contains(post))
            user.getSavedPosts().remove(post);
        else
            user.getSavedPosts().add(post);
        userRepository.save(user);
        return post;
    }

    @Override
    public Post likePost(Integer postid, Integer userid) throws Exception {
        Post post = findPostById(postid);
        User user = userService.findUserById(userid);
        if(post.getLiked().contains(user))
            post.getLiked().remove(user);
        else
        post.getLiked().add(user); // only if 1 user can like 1 post
        return (Post) postRepository.save(post);


    }



}
