package com.bhavik.controller;


import com.bhavik.models.Post;
import com.bhavik.repository.PostRepository;
import com.bhavik.response.ApiResponse;
import com.bhavik.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findALLPosts(){
     List<Post> post = postService.findAllByPost();
        return new ResponseEntity<List<Post>>(post,HttpStatus.OK);
    }

    @PostMapping("/posts/user/{userid}")
    public ResponseEntity<Post> createPost(@RequestBody Post post,@PathVariable Integer userid) throws Exception{
        Post createdPost = postService.createNewPost(post,userid);
        return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/post/{postid}/user/{userid}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postid, @PathVariable Integer userid) throws Exception {
        String message = postService.deletePost(postid, userid);
        ApiResponse apiResponse = new ApiResponse(message,true);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.ACCEPTED);
    }

    @GetMapping("/posts/{postid}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postid) throws Exception {
        Post post = postService.findPostById(postid);
        return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
    }

    @GetMapping("/posts/user/{userid}")
    public ResponseEntity<List<Post>>findUsersPost(@PathVariable Integer userid){
        List<Post> posts = postService.findPostByUserId(userid);
        return new ResponseEntity<List<Post>>(posts,HttpStatus.ACCEPTED);
    }

    @PutMapping("posts/{postid}/users/{userid}")
    public ResponseEntity<Post>savedPostHandler(@PathVariable Integer postid ,@PathVariable Integer userid ) throws Exception {
        Post post = postService.savedPost(postid,userid);
        return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
    }

    @PutMapping("posts/likes/{postid}/users/{userid}")
    public ResponseEntity<Post>likedPostHandler(@PathVariable Integer postid ,@PathVariable Integer userid ) throws Exception {
        Post post = postService.likePost(postid,userid);
        return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
    }






}

