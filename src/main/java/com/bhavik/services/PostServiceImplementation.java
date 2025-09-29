package com.bhavik.services;

import com.bhavik.models.Post;
import com.bhavik.models.User;
import com.bhavik.repository.PostRepository;
import com.bhavik.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        newpost.setCaption(post.getCaption());
        newpost.setVideo(post.getVideo());
        newpost.setImage(post.getImage());
        newpost.setTimeAtCreation(post.getTimeAtCreation());
        newpost.setUser(post.getUser());
        Post savedPost = newpost;
        return savedPost;

    }

    @Override
    public String deletePost(Integer postid, Integer userid) throws Exception {
      Post post = findPostById(postid);
      User user = userService.findUserById(userid);
       if(post.getUser().getId()!=user.getId())
       throw new Exception("u cannot delete another users post");
       postRepository.delete(post);
       return "the post has been deleted with id"+postid;
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
