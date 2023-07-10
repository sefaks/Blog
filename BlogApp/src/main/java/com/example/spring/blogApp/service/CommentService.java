package com.example.spring.blogApp.service;

import com.example.spring.blogApp.payload.CommentDto;

public interface CommentService {


    CommentDto createComment(long postId,CommentDto commentDto);



}
