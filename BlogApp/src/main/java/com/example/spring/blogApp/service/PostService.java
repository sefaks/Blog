package com.example.spring.blogApp.service;

import com.example.spring.blogApp.payload.PostDto;
import com.example.spring.blogApp.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto,long id);

    void deletePostById(long id);



}
