package com.bhavik.services;

import com.bhavik.models.Post;

import java.util.List;

public interface PostService {

    Post createNewPost(Post post,Integer userid) throws Exception;

    String deletePost(Integer postid,Integer userid) throws Exception;

    List<Post> findPostByUserId(Integer userid);

    Post findPostById(Integer postid) throws Exception;

    List<Post> findAllByPost();

    Post savedPost(Integer postid,Integer userid) throws Exception;

    Post likePost(Integer postid,Integer userid) throws Exception;
}
